package com.munizmiranda.helpdesk.service;

import com.munizmiranda.helpdesk.client.SentimentoClient;
import com.munizmiranda.helpdesk.dto.TicketRequestDTO;
import com.munizmiranda.helpdesk.dto.TicketResponseDTO;
import com.munizmiranda.helpdesk.exception.AcessoNegadoException;
import com.munizmiranda.helpdesk.exception.CredenciaisInvalidasException;
import com.munizmiranda.helpdesk.exception.TicketNotFoundException;
import com.munizmiranda.helpdesk.mapper.TicketMapper;
import com.munizmiranda.helpdesk.model.Atendente;
import com.munizmiranda.helpdesk.model.Sentimento;
import com.munizmiranda.helpdesk.model.Status;
import com.munizmiranda.helpdesk.model.Ticket;
import com.munizmiranda.helpdesk.model.Usuario;
import com.munizmiranda.helpdesk.repository.AtendenteRepository;
import com.munizmiranda.helpdesk.repository.TicketRepository;
import com.munizmiranda.helpdesk.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final UsuarioRepository usuarioRepository;
    private final AtendenteRepository atendenteRepository;
    private final SentimentoClient sentimentoClient;
    private final ExcelReportService excelReportService;

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper,
                          UsuarioRepository usuarioRepository, AtendenteRepository atendenteRepository,
                          SentimentoClient sentimentoClient, ExcelReportService excelReportService) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.usuarioRepository = usuarioRepository;
        this.atendenteRepository = atendenteRepository;
        this.sentimentoClient = sentimentoClient;
        this.excelReportService = excelReportService;
    }

    public TicketResponseDTO criarTicket(TicketRequestDTO dto, String emailUsuario) {
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(CredenciaisInvalidasException::new);

        Ticket ticket = ticketMapper.toEntity(dto);
        ticket.setStatus(Status.ABERTO);
        ticket.setUsuario(usuario);

        try {
            Sentimento sentimento = sentimentoClient.classificar(dto.descricao());
            ticket.setSentimento(sentimento);
        } catch (Exception e) {
            ticket.setSentimento(null);
        }

        Ticket salvo = ticketRepository.save(ticket);
        return ticketMapper.toResponseDTO(salvo);
    }

    public List<TicketResponseDTO> listarTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(ticketMapper::toResponseDTO)
                .toList();
    }

    public TicketResponseDTO buscarPorId(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
        return ticketMapper.toResponseDTO(ticket);
    }

    public TicketResponseDTO fecharTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));

        ticket.setStatus(Status.FECHADO);
        ticket.setDataFechamento(LocalDateTime.now());

        Ticket salvo = ticketRepository.save(ticket);
        return ticketMapper.toResponseDTO(salvo);
    }

    public TicketResponseDTO assumirTicket(Long id, String emailAtendente) {
        Atendente atendente = atendenteRepository.findByEmail(emailAtendente)
                .orElseThrow(() -> new AcessoNegadoException("Apenas atendentes podem assumir tickets"));

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));

        ticket.setAtendente(atendente);
        ticket.setStatus(Status.EM_ANDAMENTO);

        Ticket salvo = ticketRepository.save(ticket);
        return ticketMapper.toResponseDTO(salvo);
    }

    public byte[] gerarRelatorio(String emailAtendente) throws IOException {
        atendenteRepository.findByEmail(emailAtendente)
                .orElseThrow(() -> new AcessoNegadoException("Apenas atendentes podem gerar relatórios"));

        List<Ticket> tickets = ticketRepository.findAll();
        return excelReportService.gerarRelatorioTickets(tickets);
    }

    public void deletarTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new TicketNotFoundException(id);
        }
        ticketRepository.deleteById(id);
    }
}