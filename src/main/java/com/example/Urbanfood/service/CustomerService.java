package com.example.Urbanfood.service;

import com.example.Urbanfood.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Customer getCustomerById(Long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GETCUSTOMERBYID", Customer.class);
        query.registerStoredProcedureParameter("pa_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("cur", void.class, ParameterMode.REF_CURSOR);
        query.setParameter("pa_id", id);

        query.execute();
        @SuppressWarnings("unchecked")
        List<Customer> result = query.getResultList();
        return result.size() > 0 ? result.get(0) : null;
    }

    @Transactional
    public Long insertCustomer(String name, String email, Long contact, String address) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("INSERT_CUSTOMER");
        query.registerStoredProcedureParameter("pa_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_email", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_contact", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_addr", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("ou_custId", Long.class, ParameterMode.OUT);

        query.setParameter("pa_name", name);
        query.setParameter("pa_email", email);
        query.setParameter("pa_contact", contact);
        query.setParameter("pa_addr", address);

        query.execute();
        Long newId = (Long) query.getOutputParameterValue("ou_custId");
        return newId;
    }

    @Transactional
    public Customer updateCustomer(Long id, String name, String email, Long contact, String address) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("UPDATE_CUSTOMER");
        query.registerStoredProcedureParameter("pa_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_email", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_contact", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_addr", String.class, ParameterMode.IN);

        query.setParameter("pa_id", id);
        query.setParameter("pa_name", name);
        query.setParameter("pa_email", email);
        query.setParameter("pa_contact", contact);
        query.setParameter("pa_addr", address);

        query.execute();
        return entityManager.find(Customer.class,id);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("DELETE_CUSTOMER");
        query.registerStoredProcedureParameter("pa_id", Long.class, ParameterMode.IN);
        query.setParameter("pa_id", id);
        query.execute();
    }
}
