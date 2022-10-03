package com.cg.controller.rest;

import com.cg.exception.DataInputException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.utils.AppUtils;
import com.cg.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProductList() {

        List<ProductDTO> productDTOS = productService.findAllProductDTO();
        if (productDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreateProduct(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Product product = productDTO.toProduct();
        product.setId(0L);
        Product newProduct = productService.save(product);

        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> doUpdateProduct(@RequestBody ProductDTO productDTO) {
        Optional<Product> productOptional = productService.findById(productDTO.toProduct().getId());

        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product updateProduct = productService.save(productDTO.toProduct());
        return new ResponseEntity<>(updateProduct.toProductDTO(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = null;
        try {

          productOptional =  productService.findById(id);

        }catch (NumberFormatException n){
            throw new ResourceNotFoundException("ID Không Hợp Lệ!");
        }

        if (!productOptional.isPresent()) {
            throw new ResourceNotFoundException("Không Tìm Thấy Sản Phẩm!");
        }

        Product product = productOptional.get();

        ProductDTO productDTO = product.toProductDTO();

        return new ResponseEntity<>(productDTO, HttpStatus.OK);

    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            product.setDeleted(true);
            productService.save(product);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> doSearch(@PathVariable String keyword){
        String Strkeyword = "%"+keyword+"%";

        List<ProductDTO> productDTOList = productService.findProductDTOByTitleAndCategory(Strkeyword);
        if (productDTOList.isEmpty()) {
            throw new DataInputException("Không Tìm Thấy Từ Khóa bạn vui lòng nhập lại!");
        }

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> findProductByCategories(@PathVariable Long id) {
        List<Product> products = productService.findByCategory(id);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product:products){
            productDTOS.add(product.toProductDTO());
        }
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }
}
