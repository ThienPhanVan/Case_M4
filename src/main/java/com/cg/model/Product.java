package com.cg.model;

import java.math.BigDecimal;
import com.cg.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.persistence.*;
import javax.validation.constraints.Digits;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@Accessors(chain = true)
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url_Image", nullable = false)
    private String urlImage;

    // hết hàng
    @Column(name = "out_of_stock",columnDefinition = "boolean default false")
    private boolean outOfStock;
    //ngừng bán
    @Column(name = "stop_selling",columnDefinition = "boolean default false")
    private boolean stopSelling;

    @Column(nullable = false, updatable = false)
    private String title;

    @Column(nullable = false)
    private String color;


    @Digits(integer = 12, fraction = 0)
    public BigDecimal price;

    private int quantity;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public ProductDTO toProductDTO() {
        return new ProductDTO()
                .setId(String.valueOf(id))
                .setTitle(title)
                .setColor(color)
                .setPrice(String.valueOf(price))
                .setQuantity(String.valueOf(quantity))
                .setDescription(description)
                .setUrlImage(urlImage)
                .setCategory(category.toCategoryDTO());
    }

}
