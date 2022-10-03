package com.cg.service.product;


import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<ProductDTO> findAllProductDTO() {
        return  productRepository.findAllProductDTO();
    }

    @Override
    public List<ProductDTO> findProductDTOByTitleAndCategory(String keyword) {
        return productRepository.findProductDTOByTitleAndCategory(keyword);
    }

    @Override
    public List<Product> findByCategory(Long id) {
        return productRepository.findByCategoryIdAndDeletedIsFalse(id);
    }

    @Override
    public Iterable<Product> findAllByDeletedIsFalse(Long id) {
        return productRepository.findByCategoryIdAndDeletedIsFalse(id);
    }
}
