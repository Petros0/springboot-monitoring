package dev.petros0.monitoring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Slf4j
public class MonitoringApplication {

  public static void main(String[] args) {
    SpringApplication.run(MonitoringApplication.class, args);
  }

  @RestController
  class DefaultController {

    @GetMapping("/sysout")
    public String sysout() {
      log.info("Hello World!!!");

      return "Hello World!";
    }
  }
}
