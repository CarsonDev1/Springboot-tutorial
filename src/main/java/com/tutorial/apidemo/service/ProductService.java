package com.tutorial.apidemo.service;

import com.tutorial.apidemo.entity.Product;
import com.tutorial.apidemo.models.ProductDto;
import com.tutorial.apidemo.reponsitories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductDto product);
}
