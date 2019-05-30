package se.lazustone.spring.bugs.api;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import se.lazustone.spring.bugs.AbstractSpringBootTest;

class StoreArtifactsTests extends AbstractSpringBootTest {

  @Test
  void deserializationIssue() {
    var metadata = ImmutableMap.of("ke1", "value1", "key2", "value2");
    var fieldOne = List.of("str1", "str2", "str3");
    var fieldTwo = List.of(0.0, 2.0, 4.0, 7.0);

    var bodyBuilder = new MultipartBodyBuilder();
    bodyBuilder.part("metadata", metadata, MediaType.APPLICATION_JSON);
    bodyBuilder.part("fieldOne", fieldOne);
    bodyBuilder.part("fieldTwo", fieldTwo);

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
