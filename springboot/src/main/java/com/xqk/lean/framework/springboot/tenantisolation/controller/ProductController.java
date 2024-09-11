package com.xqk.lean.framework.springboot.tenantisolation.controller;

import com.xqk.lean.framework.springboot.tenantisolation.entity.Product;
import com.xqk.lean.framework.springboot.tenantisolation.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 熊乾坤
 * @since 2020-03-30 10:03
 */
@RestController
@RequestMapping("/product")
@Transactional(rollbackFor = Exception.class)
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
