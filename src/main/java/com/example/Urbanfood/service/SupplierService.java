package com.example.Urbanfood.service;

import com.example.Urbanfood.entity.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Urbanfood.repository.SupplierRepository;
import java.util.*;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Supplier> getSupplierByName(String name) {
        // Create the stored procedure query and specify Supplier.class to map the results
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("getSupplierByName", Supplier.class);

        // Register the IN parameter (pa_name) and the REF_CURSOR (cur)
        query.registerStoredProcedureParameter("pa_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("cur", void.class, ParameterMode.REF_CURSOR);

        // Set the value for the input parameter
        query.setParameter("pa_name", name);

        // Execute the stored procedure
        query.execute();

        // Retrieve and return the list of Supplier entities
        @SuppressWarnings("unchecked")
        List<Supplier> suppliers = query.getResultList();
        return suppliers;
    }
    /**
     * Adds a new supplier using the stored procedure.
     *
     * @param name    the supplier name
     * @param phone   the supplier phone number
     * @param address the supplier address
     * @return the generated supplier ID
     */
    public int addSupplier(String name, Long phone, String address) {
        return supplierRepository.insertSupplier(name, phone, address);
    }
    // Method to retrieve all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
    public void deleteSupplier(Long id) {
        supplierRepository.deleteSupplier(id);
    }

    @PersistenceContext
    private EntityManager entityManager1;

    @Transactional
    public Supplier getSupplierById(Long id) {
        // Create a stored procedure query mapped to your Supplier entity.
        StoredProcedureQuery query = entityManager1.createStoredProcedureQuery("GETSUPPLIERBYID", Supplier.class);

        // Register the IN parameter (pa_id) and the REF_CURSOR parameter (cur)
        query.registerStoredProcedureParameter("pa_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("cur", void.class, ParameterMode.REF_CURSOR);

        // Set the input parameter value
        query.setParameter("pa_id", id);

        // Execute the stored procedure
        query.execute();

        // Retrieve and return the result.
        // Since we're expecting one supplier at most, take the first result if exists.
        @SuppressWarnings("unchecked")
        List<Supplier> result = query.getResultList();
        return result.size() > 0 ? result.get(0) : null;
    }

    @PersistenceContext
    private EntityManager entityManager2;

    @Transactional
    public void updateSupplier(Long id, String name, Long contact, String address) {
        // Create stored procedure query for UPDATE_SUPPLIER
        StoredProcedureQuery query = entityManager2.createStoredProcedureQuery("UPDATE_SUPPLIER");

        // Register input parameters
        query.registerStoredProcedureParameter("pa_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_contact", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_addr", String.class, ParameterMode.IN);

        // Set parameter values
        query.setParameter("pa_id", id);
        query.setParameter("pa_name", name);
        query.setParameter("pa_contact", contact);
        query.setParameter("pa_addr", address);

        // Execute the update procedure
        query.execute();
    }
}
