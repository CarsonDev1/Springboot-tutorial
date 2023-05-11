package com.tutorial.apidemo.service.impl;

import com.tutorial.apidemo.entity.Product;
import com.tutorial.apidemo.models.ProductDto;
import com.tutorial.apidemo.reponsitory.ProductRepository;
import com.tutorial.apidemo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public ProductDto create(ProductDto product) {
        log.info("create product start");
        Product saveData = Product.builder()
                .productName(product.getProductName2())
                .price(product.getPrice2())
                .url(product.getUrl2())
                .year(product.getYear2())
                .build();
        repository.save(saveData);
        log.info("create product end");
        return product;
    }

    public List<Product> searchPage(ProductDto productDto, Integer page, Integer size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (productDto.getProductName2() != null && !productDto.getProductName2().isEmpty()) {
            predicates.add(cb.equal(root.get("productName"), productDto.getProductName2()));
        }

        if (productDto.getId() != null) {
            predicates.add(cb.equal(root.get("id"), productDto.getId()));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(root.get("id")));

        TypedQuery<Product> query = entityManager.createQuery(cq);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);

        return query.getResultList();
    }
}
