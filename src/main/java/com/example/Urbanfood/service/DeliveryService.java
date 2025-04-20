package com.example.Urbanfood.service;

import com.example.Urbanfood.entity.Delivery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DeliveryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Delivery getDeliveryById(Long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_DELIVERY_BY_ID", Delivery.class);
        query.registerStoredProcedureParameter("pa_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("cur", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("pa_id", id);
        query.execute();

        @SuppressWarnings("unchecked")
        List<Delivery> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Transactional
    public Long insertDelivery(Long orderId, Date deliveryDate, String deliveryStatus) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("INSERT_DELIVERY");
        query.registerStoredProcedureParameter("pa_order_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_delivery_date", Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_delivery_status", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("ou_del_id", Long.class, ParameterMode.OUT);

        query.setParameter("pa_order_id", orderId);
        query.setParameter("pa_delivery_date", deliveryDate);
        query.setParameter("pa_delivery_status", deliveryStatus);

        query.execute();
        return (Long) query.getOutputParameterValue("ou_del_id");
    }

    @Transactional
    public void updateDelivery(Long deliveryId, Long orderId, Date deliveryDate, String deliveryStatus) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("UPDATE_DELIVERY");
        query.registerStoredProcedureParameter("pa_delivery_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_order_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_delivery_date", Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_delivery_status", String.class, ParameterMode.IN);

        query.setParameter("pa_delivery_id", deliveryId);
        query.setParameter("pa_order_id", orderId);
        query.setParameter("pa_delivery_date", deliveryDate);
        query.setParameter("pa_delivery_status", deliveryStatus);

        query.execute();
    }

    @Transactional
    public void deleteDelivery(Long deliveryId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("DELETE_DELIVERY");
        query.registerStoredProcedureParameter("pa_delivery_id", Long.class, ParameterMode.IN);
        query.setParameter("pa_delivery_id", deliveryId);
        query.execute();
    }
}
