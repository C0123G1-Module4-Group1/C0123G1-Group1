package com.example.coffee.product.dto;



import com.example.coffee.product.model.TypeProduct;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class ProductDTO implements Validator {
    private  Integer id;
    @NotBlank(message = "cannot be left blank")
    @Pattern(regexp = "^\\p{Lu}\\p{Ll}*(\\s\\p{Lu}\\p{Ll}*)*$", message = "Name must be in the correct format.Must be letters not numbers" )
    private  String name;
    @NotBlank(message = "cannot be left blank")
    private String image;
    private  String describes;
    private LocalDateTime createTime;
    private  LocalDateTime updateTime;
    private TypeProduct typeProduct;
    @NotNull(message = "cannot be left blank")
    private Float price;
    private String currency;
    private boolean status;


    public ProductDTO() {
    }

    public ProductDTO(Integer id, String name, String image, String describes, LocalDateTime createTime, LocalDateTime updateTime, TypeProduct typeProduct, Float price, String currency, boolean status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.describes = describes;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.typeProduct = typeProduct;

        this.price = price;
        this.currency = currency;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
