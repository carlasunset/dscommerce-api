package com.carla.dscommerce.controllers;

import com.carla.dscommerce.dto.ProductDTO;
import com.carla.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable){
        return productService.findAll(pageable);
    }

    @PostMapping
    public ProductDTO insert(@RequestBody ProductDTO entityDTO){
        return productService.insert(entityDTO);
    }

}
