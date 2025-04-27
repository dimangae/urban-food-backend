package com.example.Urbanfood.service;

import com.example.Urbanfood.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Product getProductById(Long id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("GETPRODUCTBYID", Product.class);

        query.registerStoredProcedureParameter("pa_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("cur", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("pa_id", id);
        query.execute();

        @SuppressWarnings("unchecked")
        List<Product> result = query.getResultList();
        return result.size() > 0 ? result.get(0) : null;
    }

    @Transactional
    public Long insertProduct(Long supplierId, String name, String description,
                              String category, BigDecimal price, Integer stockQuantity, String imageUrl) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("INSERT_PRODUCT");

        query.registerStoredProcedureParameter("pa_supplier_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_description", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_category", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_price", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_stock_quantity", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_image_url", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("ou_prod_id", Long.class, ParameterMode.OUT);

        query.setParameter("pa_supplier_id", supplierId);
        query.setParameter("pa_name", name);
        query.setParameter("pa_description", description);
        query.setParameter("pa_category", category);
        query.setParameter("pa_price", price);
        query.setParameter("pa_stock_quantity", stockQuantity);
        query.setParameter("pa_image_url", imageUrl);

        query.execute();
        return (Long) query.getOutputParameterValue("ou_prod_id");
    }

    @Transactional
    public void updateProduct(Long id, Long supplierId, String name, String description,
                              String category, BigDecimal price, Integer stockQuantity, String imageUrl) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("UPDATE_PRODUCT");

        query.registerStoredProcedureParameter("pa_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_supplier_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_description", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_category", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_price", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_stock_quantity", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pa_image_url", String.class, ParameterMode.IN);

        query.setParameter("pa_id", id);
        query.setParameter("pa_supplier_id", supplierId);
        query.setParameter("pa_name", name);
        query.setParameter("pa_description", description);
        query.setParameter("pa_category", category);
        query.setParameter("pa_price", price);
        query.setParameter("pa_stock_quantity", stockQuantity);
        query.setParameter("pa_image_url", imageUrl);

        query.execute();
    }

    @Transactional
    public void deleteProduct(Long id) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("DELETE_PRODUCT");

        query.registerStoredProcedureParameter("pa_id", Long.class, ParameterMode.IN);
        query.setParameter("pa_id", id);
        query.execute();
    }

    @Transactional
    public List<Product> getAllProducts() {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("GETALLPRODUCTS", Product.class);

        query.registerStoredProcedureParameter("cur", void.class, ParameterMode.REF_CURSOR);

        query.execute();

        @SuppressWarnings("unchecked")
        List<Product> result = query.getResultList();
        return result;
    }
}
