package com.example.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ReservationClient
{
  private final WebClient webClient;


  public Flux<Reservation> findAllReservations()
  {
    return webClient.get()
        .uri("/reservations")
        .retrieve()
        .bodyToFlux(Reservation.class);
  }
}
