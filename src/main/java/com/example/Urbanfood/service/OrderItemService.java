package com.example.Urbanfood.service;

import com.example.Urbanfood.entity.OrderItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderItemService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public OrderItem getOrderItem(Long orderId, Long productId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_ORDER_ITEM", OrderItem.class);
        query.registerStoredProcedureParameter("pa_order_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_product_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("cur", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("pa_order_id", orderId);
        query.setParameter("pa_product_id", productId);
        query.execute();

        @SuppressWarnings("unchecked")
        List<OrderItem> result = query.getResultList();
        return result.size() > 0 ? result.get(0) : null;
    }

    @Transactional
    public void insertOrderItem(Long orderId, Long productId, Integer quantity, BigDecimal unitPrice) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("INSERT_ORDER_ITEM");
        query.registerStoredProcedureParameter("pa_order_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_product_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_quantity", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_unit_price", BigDecimal.class, ParameterMode.IN);

        query.setParameter("pa_order_id", orderId);
        query.setParameter("pa_product_id", productId);
        query.setParameter("pa_quantity", quantity);
        query.setParameter("pa_unit_price", unitPrice);
        query.execute();
    }

    @Transactional
    public void updateOrderItem(Long orderId, Long productId, Integer quantity, BigDecimal unitPrice) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("UPDATE_ORDER_ITEM");
        query.registerStoredProcedureParameter("pa_order_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_product_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_quantity", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_unit_price", BigDecimal.class, ParameterMode.IN);

        query.setParameter("pa_order_id", orderId);
        query.setParameter("pa_product_id", productId);
        query.setParameter("pa_quantity", quantity);
        query.setParameter("pa_unit_price", unitPrice);
        query.execute();
    }

    @Transactional
    public void deleteOrderItem(Long orderId, Long productId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("DELETE_ORDER_ITEM");
        query.registerStoredProcedureParameter("pa_order_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_product_id", Long.class, ParameterMode.IN);

        query.setParameter("pa_order_id", orderId);
        query.setParameter("pa_product_id", productId);
        query.execute();
    }
}
