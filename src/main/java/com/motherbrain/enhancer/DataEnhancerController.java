package com.motherbrain.enhancer;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataEnhancerController {
  @Autowired DataEnhancerService dataEnhancerService;

  @PostMapping("/pipeline/start")
  public String start() {
    CompletableFuture.runAsync(
        () -> {
          try {
            dataEnhancerService.runPipeline();
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
    return "Pipeline started\n";
  }
}
