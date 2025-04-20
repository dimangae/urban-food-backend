package com.example.Urbanfood.service;

import com.example.Urbanfood.entity.CartItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CartItemService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public CartItem getCartItem(Long cartId, Long productId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_CART_ITEM", CartItem.class);
        query.registerStoredProcedureParameter("pa_cart_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_product_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("cur", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("pa_cart_id", cartId);
        query.setParameter("pa_product_id", productId);
        query.execute();

        @SuppressWarnings("unchecked")
        List<CartItem> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Transactional
    public void insertCartItem(Long cartId, Long productId, Integer quantity, Date dateAdded) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("INSERT_CART_ITEM");
        query.registerStoredProcedureParameter("pa_cart_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_product_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_quantity", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_date_added", Date.class, ParameterMode.IN);

        query.setParameter("pa_cart_id", cartId);
        query.setParameter("pa_product_id", productId);
        query.setParameter("pa_quantity", quantity);
        query.setParameter("pa_date_added", dateAdded);
        query.execute();
    }

    @Transactional
    public void updateCartItem(Long cartId, Long productId, Integer quantity, Date dateAdded) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("UPDATE_CART_ITEM");
        query.registerStoredProcedureParameter("pa_cart_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_product_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_quantity", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_date_added", Date.class, ParameterMode.IN);

        query.setParameter("pa_cart_id", cartId);
        query.setParameter("pa_product_id", productId);
        query.setParameter("pa_quantity", quantity);
        query.setParameter("pa_date_added", dateAdded);
        query.execute();
    }

    @Transactional
    public void deleteCartItem(Long cartId, Long productId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("DELETE_CART_ITEM");
        query.registerStoredProcedureParameter("pa_cart_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_product_id", Long.class, ParameterMode.IN);

        query.setParameter("pa_cart_id", cartId);
        query.setParameter("pa_product_id", productId);
        query.execute();
    }
}
