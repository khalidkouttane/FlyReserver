package com.example.paymentservice;

import com.example.paymentservice.controller.PaymentController;
import com.example.paymentservice.model.Payment;
import com.example.paymentservice.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @MockBean
    private com.example.paymentservice.repository.PaymentRepository paymentRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testPay() throws Exception {
        Payment request = new Payment();
        request.setBookingReference("BOOK123");
        request.setEmail("john.doe@example.com");

        Payment response = new Payment();
        response.setId(1L);
        response.setBookingReference("BOOK123");
        response.setStatus("PAID");
        response.setEmail("john.doe@example.com");

        Mockito.when(paymentService.processPayment(any(Payment.class))).thenReturn(response);

        mockMvc.perform(post("/payments/pay")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("PAID")))
                .andExpect(jsonPath("$.bookingReference", is("BOOK123")))
                .andExpect(jsonPath("$.email", is("john.doe@example.com")));
    }

    @Test
    void testGetPaymentByBookingRef() throws Exception {
        Payment payment = new Payment();
        payment.setBookingReference("BOOK456");
        payment.setStatus("PAID");
        payment.setEmail("jane.doe@example.com");

        Mockito.when(paymentService.getPaymentByBookingReference("BOOK456")).thenReturn(payment);

        mockMvc.perform(get("/payments/BOOK456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingReference", is("BOOK456")))
                .andExpect(jsonPath("$.status", is("PAID")))
                .andExpect(jsonPath("$.email", is("jane.doe@example.com")));
    }
}
