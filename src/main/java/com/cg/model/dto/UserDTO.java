package com.cg.model.dto;

import com.cg.model.LocationRegion;
import com.cg.model.Role;
import com.cg.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO {

    private Long id;

    @NotBlank(message = "Username không được để trống!")
    @Email(message = "Vui lòng nhập đúng định dạng UserName VD(txr@gmail.com)")
    @Size(min = 6, message = "Username tối thiểu 6 kí tự")
    @Size(max = 30, message = "Username tối đa 13 đến 30 kí tự")
    private String username;

    @NotBlank(message = "Password không được để trống!")
    private String password;

    @NotBlank(message = "FullName không được để trống!")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "FullName phải là chữ , không có kí tự đặt biệt và số")
    private String fullName;


    @NotBlank(message = "Phone là bắt buộc")
    @Pattern(regexp = "^[0][1-9][0-9]{8,9}|[+84][1-9][0-9]{10,11}$", message = "Phone không bao gồm dấu cách,chữ,kí tự đặc biệt,Phone gồm 10 đến 11 số và bắt đầu là số 0 và +84")
    private String phone;

    private String urlImage;

    @Valid
    private LocationRegionDTO locationRegion;

    @Valid
    private RoleDTO role;

    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;

    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date updatedAt;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }


    public UserDTO(Long id, String username, String password, String fullName, String phone, String urlImage, Date createdAt, Date updatedAt, LocationRegion locationRegion, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.urlImage = urlImage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.locationRegion = locationRegion.toLocationRegionDTO();
        this.role = role.toRoleDTO();
    }



    public UserDTO(Long id, String username, String fullName, String phone, String urlImage, LocationRegion locationRegion, Role role) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.phone = phone;
        this.urlImage = urlImage;
        this.locationRegion = locationRegion.toLocationRegionDTO();
        this.role = role.toRoleDTO();

    }


    public User toUser() {
        return new User()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setFullName(fullName)
                .setUrlImage(urlImage)
                .setPhone(phone)
                .setLocationRegion(locationRegion.toLocationRegion())
                .setRole(role.toRole());
    }

}
