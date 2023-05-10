package com.tutorial.apidemo.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
//    @Bean
//    CommandLineRunner initDatabase(ProductRepository productRepository) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                Product productA = Product.builder()
//                        .id(1L)
//                        .productName("Macbook Pro 16")
//                        .year(2023)
//                        .price(2400.0)
//                        .url("")
//                        .build();
//                Product productB = Product.builder()
//                        .id(2L)
//                        .productName("Ipad Air")
//                        .year(2022)
//                        .price(400.0)
//                        .url("")
//                        .build();
//                logger.info("insert data: "+productRepository.save(productA));
//                logger.info("insert data: "+productRepository.save(productB));
//            }
//        };
//    }
}
