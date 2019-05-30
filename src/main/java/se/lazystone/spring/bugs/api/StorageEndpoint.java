package se.lazystone.spring.bugs.api;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/storage")
public class StorageEndpoint {
  private static final Logger LOGGER = LoggerFactory.getLogger(StorageEndpoint.class);

  // https://github.com/spring-projects/spring-framework/issues/22973
  @PostMapping(value = "/{callId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Mono<Void> store(
      @PathVariable final String callId,
      @RequestPart("metadata") final Map<String, String> metadata,
      @RequestPart("fieldOne") final List<String> fieldOne,
      @RequestPart("fieldTwo") final List<Double> fieldTwo) {

    LOGGER.debug("store() for {} called", callId);
    return Mono.empty();
  }
}
