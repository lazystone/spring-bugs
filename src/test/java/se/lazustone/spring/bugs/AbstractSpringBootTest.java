package se.lazustone.spring.bugs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.lazystone.spring.bugs.Application;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = Application.class)
public abstract class AbstractSpringBootTest {

  protected WebTestClient webClient;

  @BeforeEach
  void setup(ApplicationContext context) {
    this.webClient =
        WebTestClient.bindToApplicationContext(context)
            .configureClient()
            .baseUrl("https://api.lazystone.test")
            .build();
  }
}
