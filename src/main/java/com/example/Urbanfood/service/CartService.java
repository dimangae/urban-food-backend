package com.example.Urbanfood.service;

import com.example.Urbanfood.entity.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Cart getCartById(Long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_CART_BY_ID", Cart.class);
        query.registerStoredProcedureParameter("pa_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("cur", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("pa_id", id);
        query.execute();

        @SuppressWarnings("unchecked")
        List<Cart> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Transactional
    public Long insertCart(Long customerId, Integer totalItem, BigDecimal totalPrice) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("INSERT_CART");
        query.registerStoredProcedureParameter("pa_customer_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_total_item", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_total_price", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("ou_cart_id", Long.class, ParameterMode.OUT);

        query.setParameter("pa_customer_id", customerId);
        query.setParameter("pa_total_item", totalItem);
        query.setParameter("pa_total_price", totalPrice);
        query.execute();

        return (Long) query.getOutputParameterValue("ou_cart_id");
    }

    @Transactional
    public void updateCart(Long cartId, Long customerId, Integer totalItem, BigDecimal totalPrice) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("UPDATE_CART");
        query.registerStoredProcedureParameter("pa_cart_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_customer_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_total_item", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_total_price", BigDecimal.class, ParameterMode.IN);

        query.setParameter("pa_cart_id", cartId);
        query.setParameter("pa_customer_id", customerId);
        query.setParameter("pa_total_item", totalItem);
        query.setParameter("pa_total_price", totalPrice);
        query.execute();
    }

    @Transactional
    public void deleteCart(Long cartId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("DELETE_CART");
        query.registerStoredProcedureParameter("pa_cart_id", Long.class, ParameterMode.IN);
        query.setParameter("pa_cart_id", cartId);
        query.execute();
    }
}
