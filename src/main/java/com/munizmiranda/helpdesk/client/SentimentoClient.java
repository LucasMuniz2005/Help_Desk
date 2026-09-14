package com.munizmiranda.helpdesk.client;

import com.munizmiranda.helpdesk.dto.SentimentoResponseDTO;
import com.munizmiranda.helpdesk.model.Sentimento;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class SentimentoClient {

    private final RestClient restClient;

    public SentimentoClient(@Value("${ml.api.url:http://localhost:5000}") String mlApiUrl) {
        this.restClient = RestClient.create(mlApiUrl);
    }

    public Sentimento classificar(String texto) {
        SentimentoResponseDTO resposta = restClient.post()
                .uri("/sentimento")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("texto", texto))
                .retrieve()
                .body(SentimentoResponseDTO.class);

        return Sentimento.valueOf(resposta.sentimento());
    }
}