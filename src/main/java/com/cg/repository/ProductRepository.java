package com.cg.repository;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new com.cg.model.dto.ProductDTO (" +
            "p.id, " +
            "p.title, " +
            "p.price, " +
            "p.color, " +
            "p.quantity, " +
            "p.description, " +
            "p.urlImage, " +
            "p.category" +
            ") " +
            "FROM Product p " +
            "WHERE p.id = ?1 "
    )
    Optional<ProductDTO> findProductDTOById(Long id);

    @Query("SELECT new com.cg.model.dto.ProductDTO (" +
            "p.id, " +
            "p.title, " +
            "p.price, " +
            "p.color, " +
            "p.quantity, " +
            "p.description, " +
            "p.urlImage, " +
            "p.category" +
            ") " +
            "FROM Product AS p " +
            "WHERE p.deleted = false")
    List<ProductDTO> findAllProductDTO();

    @Query("SELECT new com.cg.model.dto.ProductDTO (" +
            "p.id, " +
            "p.title, " +
            "p.price, " +
            "p.color, " +
            "p.quantity, " +
            "p.description, " +
            "p.urlImage, " +
            "p.category" +
            ") " +
            "FROM Product AS p " +
            "WHERE p.deleted = false " +
            "AND (p.title LIKE %?1% " +
            "OR p.category.title LIKE %?1%) ")
    List<ProductDTO> findProductDTOByTitleAndCategory(String keyword);


    List<Product> findByCategoryIdAndDeletedIsFalse(Long id);

    List<Product> findByCategoryId(Long id);
}
