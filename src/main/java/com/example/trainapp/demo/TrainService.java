package com.example.trainapp.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TrainService {

        private final RestClient restClient;

        @Value("${marta.api-key:}")
        private String apiKey;

        public TrainService(RestClient.Builder builder) {
                this.restClient = builder
                                .baseUrl(
                                                "https://developerservices.itsmarta.com:18096")
                                .build();
        }

        public List<TrainResponse> getAllTrains() {

                if (apiKey.isBlank()) {
                        throw new IllegalStateException(
                                        "MARTA_API_KEY is required");
                }

                return restClient.get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/itsmarta/railrealtimearrivals/"
                                                                + "developerservices/traindata")
                                                .queryParam("apiKey", apiKey)
                                                .build())
                                .retrieve()
                                .body(
                                                new ParameterizedTypeReference<List<TrainResponse>>() {
                                                });
        }

        public List<TrainResponse> getFilteredTrains(String line, String direction) {
                return getAllTrains().stream()
                                .filter(train -> (direction == null || direction.isBlank()
                                                || direction.trim().equalsIgnoreCase(train.direction()))
                                                && (line == null || line.isBlank()
                                                                || line.trim().equalsIgnoreCase(train.line())))
                                .toList();
        }
}
