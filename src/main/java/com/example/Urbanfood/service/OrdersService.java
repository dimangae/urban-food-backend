package com.example.Urbanfood.service;

import com.example.Urbanfood.entity.Orders;
import com.example.Urbanfood.repository.OrdersRepository;
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
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @PersistenceContext
    private EntityManager entityManager1;

    @Transactional
    public void deductStockOnOrder(Long productId, Integer quantity) {
        StoredProcedureQuery query = entityManager1.createStoredProcedureQuery("DEDUCT_STOCK_ON_ORDER");
        query.registerStoredProcedureParameter("p_product_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_quantity", Integer.class, ParameterMode.IN);

        query.setParameter("p_product_id", productId);
        query.setParameter("p_quantity", quantity);

        query.execute();
    }
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public BigDecimal calculateOrderTotal(Long orderId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("CALCULATE_ORDER_TOTAL");
        query.registerStoredProcedureParameter("p_order_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_final_total", BigDecimal.class, ParameterMode.OUT);

        query.setParameter("p_order_id", orderId);
        query.execute();

        return (BigDecimal) query.getOutputParameterValue("p_final_total");
    }
    @Transactional
    public Long insertOrder(Long customerId, String shippingAddress) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("INSERT_ORDER");
        query.registerStoredProcedureParameter("pa_customer_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_shipping_addr", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("ou_order_id", Long.class, ParameterMode.OUT);

        query.setParameter("pa_customer_id", customerId);
        query.setParameter("pa_shipping_addr", shippingAddress);

        query.execute();
        return (Long) query.getOutputParameterValue("ou_order_id");
    }

    @Transactional
    public Orders getOrderById(Long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GETORDERBYID", Orders.class);
        query.registerStoredProcedureParameter("pa_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("cur", void.class, ParameterMode.REF_CURSOR);
        query.setParameter("pa_id", id);
        query.execute();

        @SuppressWarnings("unchecked")
        List<Orders> result = query.getResultList();
        return result.size() > 0 ? result.get(0) : null;
    }

    @Transactional
    public Long insertOrder(Long customerId, String shippingAddress, BigDecimal totalAmount) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("INSERT_ORDER");
        query.registerStoredProcedureParameter("pa_customer_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_shipping_addr", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_total_amount", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("ou_order_id", Long.class, ParameterMode.OUT);

        query.setParameter("pa_customer_id", customerId);
        query.setParameter("pa_shipping_addr", shippingAddress);
        query.setParameter("pa_total_amount", totalAmount);

        query.execute();
        return (Long) query.getOutputParameterValue("ou_order_id");
    }

    @Transactional
    public void updateOrder(Long orderId, Long customerId, Date orderDate, String shippingAddress, BigDecimal totalAmount) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("UPDATE_ORDER");
        query.registerStoredProcedureParameter("pa_order_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_customer_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_order_date", Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_shipping_addr", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_total_amount", BigDecimal.class, ParameterMode.IN);

        query.setParameter("pa_order_id", orderId);
        query.setParameter("pa_customer_id", customerId);
        query.setParameter("pa_order_date", orderDate);
        query.setParameter("pa_shipping_addr", shippingAddress);
        query.setParameter("pa_total_amount", totalAmount);

        query.execute();
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("DELETE_ORDER");
        query.registerStoredProcedureParameter("pa_order_id", Long.class, ParameterMode.IN);
        query.setParameter("pa_order_id", orderId);
        query.execute();
    }

    @Transactional
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }
}
