package com.tutorial.apidemo.controllers;

import com.tutorial.apidemo.entity.Product;
import com.tutorial.apidemo.models.ProductDto;
import com.tutorial.apidemo.models.ResponseObject;
import com.tutorial.apidemo.reponsitory.ProductRepository;
import com.tutorial.apidemo.service.ExcelUploadService;
import com.tutorial.apidemo.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {


    @Autowired
    private ProductRepository repository;

    @Autowired
    private ExcelUploadService excelUploadService;



    @Autowired
    private ProductServiceImpl service;
    @GetMapping("")
    //this request is : http://localhost:8080/api/v1/Products
    List<Product> getAllProducts() {
       return repository.findAll();
    }

    @GetMapping("/sort/{field}")
    List<Product> findProductsWithSorting(@PathVariable String field) {
        return repository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    Page<Product> findProductsWithPagination(@PathVariable int offset,@PathVariable int pageSize) {
        Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize));
        return products;
    }
    @PostMapping("/search")
    List<Product> getAllProductsSorted(@RequestBody ProductDto productDto, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "50") Integer size) {
        return service.searchPage(productDto, page, size);
    }
//    @GetMapping("/sort")
//    List<Product> getAllProductsSorted(@RequestParam(required = false) String productName, @RequestParam(required = false) String id) {
//        List<Sort.Order> orders = new ArrayList<>();
//        if(!Objects.isNull(productName)) {
//            Sort.Order order = new Sort.Order(Sort.Direction.valueOf(productName.toUpperCase()), "productName");
//            orders.add(order);
//        }
//        if(!Objects.isNull(id)) {
//            Sort.Order order = new Sort.Order(Sort.Direction.valueOf(id.toUpperCase()), "id");
//            orders.add(order);
//        }
//        return repository.findAll(Sort.by(orders));
//    }
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ?
            ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query product successfully",foundProduct)
            ): ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed","Cannot find product with id = "+id, "")
        );
    }
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody ProductDto newProduct) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Product Successfully", service.create(newProduct))
        );
    }

    @DeleteMapping("")
        //this request is : http://localhost:8080/api/v1/Products
    Boolean delete(@RequestParam long id) {
        System.out.println(""+id);
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @PostMapping("/upload")
    List<Product> updateLoad(@RequestParam("file") MultipartFile file) {
        return excelUploadService.getProductsDataFromExcel2(file);
    }
}
