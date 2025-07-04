package com.example.paymentservice.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPaymentProducer {

    private static final String TOPIC = "ticket-events";

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void sendTicketEvent(PaymentEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
