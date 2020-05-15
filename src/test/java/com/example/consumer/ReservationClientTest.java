package com.example.consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@SpringBootTest
@AutoConfigureStubRunner(stubsMode = LOCAL, ids = "com.example:producer:+:8080")
public class ReservationClientTest
{
  @Autowired
  private ReservationClient client;

  @Test
  @DisplayName("Should find all reservations")
  void should_find_all_reservations()
  {
    //given stubs
    //when
    Flux<Reservation> result = client.findAllReservations();
    //then
    StepVerifier.create(result)
                .assertNext(r -> {
                  assertThat(r.getId()).isEqualTo("1");
                  assertThat(r.getName()).isEqualTo("Joris");
                })
                .verifyComplete();
  }
}
