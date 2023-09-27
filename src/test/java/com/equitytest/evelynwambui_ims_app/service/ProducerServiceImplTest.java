/**
 * @apiNote class
 * @author Eveloyn Wambiui
 * @version 1.0.0
 * @created 27/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.service;
import com.equitytest.evelynwambui_ims_app.service_impl.ProducerServiceImpl;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProducerServiceImplTest {

  @Mock
  private KafkaTemplate<String, String> kafkaTemplate;

  private ProducerServiceImpl producerService;

  @Captor
  private ArgumentCaptor<ProducerRecord<String, String>> captor;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    producerService = new ProducerServiceImpl(kafkaTemplate);
  }

  @Test
  public void testSendMessage() {
    String topic = "testTopic";
    String message = "Test message";

    producerService.sendMessage(topic, message);

    // Verify that kafkaTemplate.send was called with the correct topic and message
    verify(kafkaTemplate, times(1)).send(captor.capture());

    // Get the captured ProducerRecord
    ProducerRecord<String, String> capturedRecord = captor.getValue();

    // Assert the values of the captured ProducerRecord
    assertEquals(topic, capturedRecord.topic());
    assertEquals(message, capturedRecord.value());
  }
}
