package com.motherbrain.enhancer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class DataEnhancerControllerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean DataEnhancerService dataEnhancerService;

  @Test
  void testEchoMessage() throws Exception {
    String message = "Testing the echo message";
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/pipeline/start")
                .contentType(MediaType.TEXT_PLAIN)
                .content(message))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("Pipeline started\n"));
  }
}
