package com.example.Urbanfood.service;

import com.example.Urbanfood.entity.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Payment getPaymentById(Long id) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("GETPAYMENTBYID", Payment.class);
        query.registerStoredProcedureParameter("pa_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("cur", void.class, ParameterMode.REF_CURSOR);
        query.setParameter("pa_id", id);
        query.execute();

        @SuppressWarnings("unchecked")
        List<Payment> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Transactional
    public Long insertPayment(Long orderId, String paymentMethod, BigDecimal amount) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("INSERT_PAYMENT");
        query.registerStoredProcedureParameter("pa_order_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_payment_method", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_amount", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("ou_pay_id", Long.class, ParameterMode.OUT);

        query.setParameter("pa_order_id", orderId);
        query.setParameter("pa_payment_method", paymentMethod);
        query.setParameter("pa_amount", amount);

        query.execute();
        return (Long) query.getOutputParameterValue("ou_pay_id");
    }

    @Transactional
    public void updatePayment(Long paymentId, Long orderId, Date paymentDate,
                              String paymentMethod, BigDecimal amount) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("UPDATE_PAYMENT");
        query.registerStoredProcedureParameter("pa_payment_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_order_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_payment_date", Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_payment_method", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_amount", BigDecimal.class, ParameterMode.IN);

        query.setParameter("pa_payment_id", paymentId);
        query.setParameter("pa_order_id", orderId);
        query.setParameter("pa_payment_date", paymentDate);
        query.setParameter("pa_payment_method", paymentMethod);
        query.setParameter("pa_amount", amount);

        query.execute();
    }

    @Transactional
    public void deletePayment(Long paymentId) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("DELETE_PAYMENT");
        query.registerStoredProcedureParameter("pa_payment_id", Long.class, ParameterMode.IN);
        query.setParameter("pa_payment_id", paymentId);
        query.execute();
    }
}
