package com.cg.model.dto;

import com.cg.model.Category;
import com.cg.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductDTO {

    private String id;

    @NotBlank(message = "Tên sản phẩm không được để trống!")
    @Size(max = 35, message = "Tên sản phẩm phải từ 5 đến 35 ký tự! ")
    private String title;

    @Pattern(regexp = "^[0-9]+$", message = "Vui lòng nhập số tiền sản phẩm!")

    @NotBlank(message = "Giá sản phẩm không được để trống!")
    @Pattern(regexp = "^\\d+$", message = "Price không được nhập số âm hoặc chữ, yêu cầu nhập lại")
    @Min(value = 200000, message = "Giá tối thiểu là 200 000đ")
    @Max(value = 50000000, message = "Giá lớn nhất là 50 triệu đồng")
    private String price;

    @NotBlank(message = "Màu sắc sản phẩm không được để trống!")
    @Size(max = 35, message = "Màu sắc sản phẩm phải từ 2 đến 15 ký tự! ")
    private String color;

    @Pattern(regexp = "^[0-9]+$", message = "Vui lòng nhập số lượng sản phẩm!")
    @Size(min=1, max=500, message = "Số lượng nhập vào phải lớn hơn 0 và nhỏ hơn 500!")
    private String quantity;

    @NotBlank(message = "Mô tả sản phẩm không được để trống!")
    private String description;

    private String urlImage;

    @Valid
    private CategoryDTO category;

    public ProductDTO(Long id, String title, BigDecimal price, String color, Integer quantity, String description,String urlImage, Category category) {
        this.id = id.toString();
        this.title = title;
        this.price = price.toString();
        this.color = color;
        this.quantity = quantity.toString();
        this.description=description;
        this.urlImage = urlImage;
        this.category = category.toCategoryDTO();
    }


    public Product toProduct() {
        return new Product()
                .setId(Long.parseLong(id))
                .setTitle(title)
                .setPrice(new BigDecimal(Long.parseLong(price)))
                .setColor(color)
                .setQuantity(Integer.parseInt(quantity))
                .setUrlImage(urlImage)
                .setDescription(description)
                .setCategory(category.toCategory());
    }

}
