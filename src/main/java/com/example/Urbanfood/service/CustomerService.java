package com.example.Urbanfood.service;

import com.example.Urbanfood.entity.Customer;
import com.example.Urbanfood.repository.CustomerRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

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
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Transactional
    public Long insertCustomer(String name, String email, Long contact, String address, String nic) {
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("INSERT_CUSTOMER");

            query.registerStoredProcedureParameter("pa_name", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pa_email", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pa_contact", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pa_address", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pa_nic", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("ou_custId", BigDecimal.class, ParameterMode.OUT);

            query.setParameter("pa_name", name);
            query.setParameter("pa_email", email);
            query.setParameter("pa_contact", BigDecimal.valueOf(contact));
            query.setParameter("pa_address", address);
            query.setParameter("pa_nic", nic);

            query.execute();
            BigDecimal newId = (BigDecimal ) query.getOutputParameterValue("ou_custId");
            return newId.longValue();
        } catch (PersistenceException e) {
            if (e.getCause() != null && e.getCause().getMessage().contains("ORA-20001")) {
                throw new RuntimeException("Duplicate NIC found.");
            } else {
                throw new RuntimeException("Error inserting customer: " + e.getMessage());
            }
        }
    }

    @Transactional
    public Customer updateCustomer(Long id, String name, String email, Long contact, String address, String nic ) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("UPDATE_CUSTOMER");
        query.registerStoredProcedureParameter("pa_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_email", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_contact", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_address", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_nic", String.class, ParameterMode.IN);

        query.setParameter("pa_id", id);
        query.setParameter("pa_name", name);
        query.setParameter("pa_email", email);
        query.setParameter("pa_contact", contact);
        query.setParameter("pa_address", address);
        query.setParameter("pa_nic", nic);

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

    @Transactional
    public Customer getCustomerIdByNic(String nic) {
        return customerRepository.findByNic(nic)
                .orElse(null); // Returns null if not found
    }
}
