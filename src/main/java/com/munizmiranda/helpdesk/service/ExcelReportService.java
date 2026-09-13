package com.munizmiranda.helpdesk.service;

import com.munizmiranda.helpdesk.model.Ticket;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExcelReportService {

    public byte[] gerarRelatorioTickets(List<Ticket> tickets) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Tickets");

            String[] colunas = {
                "ID", "Título", "Descrição", "Categoria", "Prioridade",
                "Status", "Sentimento", "Data Abertura", "Data Fechamento",
                "Usuário", "Atendente"
            };

            Row cabecalho = sheet.createRow(0);
            for (int i = 0; i < colunas.length; i++) {
                cabecalho.createCell(i).setCellValue(colunas[i]);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            int linhaAtual = 1;

            for (Ticket ticket : tickets) {
                Row linha = sheet.createRow(linhaAtual++);

                linha.createCell(0).setCellValue(ticket.getId());
                linha.createCell(1).setCellValue(ticket.getTitulo());
                linha.createCell(2).setCellValue(ticket.getDescricao());
                linha.createCell(3).setCellValue(ticket.getCategoria().name());
                linha.createCell(4).setCellValue(ticket.getPrioridade().name());
                linha.createCell(5).setCellValue(ticket.getStatus().name());
                linha.createCell(6).setCellValue(ticket.getSentimento() != null ? ticket.getSentimento().name() : "");
                linha.createCell(7).setCellValue(ticket.getDataAbertura().format(formatter));
                linha.createCell(8).setCellValue(ticket.getDataFechamento() != null ? ticket.getDataFechamento().format(formatter) : "");
                linha.createCell(9).setCellValue(ticket.getUsuario().getNome());
                linha.createCell(10).setCellValue(ticket.getAtendente() != null ? ticket.getAtendente().getNome() : "");
            }

            for (int i = 0; i < colunas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream saida = new ByteArrayOutputStream();
            workbook.write(saida);
            return saida.toByteArray();
        }
    }
}