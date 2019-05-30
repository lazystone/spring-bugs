package se.lazustone.spring.bugs.api;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import se.lazustone.spring.bugs.AbstractSpringBootTest;

class StoreArtifactsTests extends AbstractSpringBootTest {

  @Test
  void optionalParameterPresent() {
    var bodyBuilder = new MultipartBodyBuilder();
    bodyBuilder.part("fieldOne", "fieldOne");

    webClient
        .post()
        .uri("/api/v1/storage/{callId}", UUID.randomUUID().toString())
        .header(CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
        .syncBody(bodyBuilder.build())
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  void optionalParameterAbsent() {
    var bodyBuilder = new MultipartBodyBuilder();

    webClient
        .post()
        .uri("/api/v1/storage/{callId}", UUID.randomUUID().toString())
        .header(CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
        .syncBody(bodyBuilder.build())
        .exchange()
        .expectStatus()
        .isOk();
  }
}
