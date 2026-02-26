package com.carla.dscommerce.services;

import com.carla.dscommerce.dto.ProductDTO;
import com.carla.dscommerce.entities.Product;
import com.carla.dscommerce.repositories.ProductRepository;
import com.carla.dscommerce.services.exceptions.DatabaseException;
import com.carla.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> products = repository.findAll(pageable);
        return products.map(x -> new ProductDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductDTO entityDTO){
        Product product = new Product();
        copyDtoToEntity(entityDTO, product);
        repository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO entityDTO){
        try{
            Product product = repository.getReferenceById(id);
            copyDtoToEntity(entityDTO, product);
            repository.save(product);
            return new ProductDTO(product);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found.");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            findById(id);
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure");
        }
    }

    private void copyDtoToEntity(ProductDTO entityDTO, Product product) {
        product.setName(entityDTO.getName());
        product.setDescription(entityDTO.getDescription());
        product.setPrice(entityDTO.getPrice());
        product.setImgUrl(entityDTO.getImgUrl());
    }
}
