package com.example.coffee.product.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class TypeProductDTO implements Validator {
    private  Integer id;
    @NotBlank(message = "cannot be left blank")
    @Pattern(regexp = "^\\p{Lu}\\p{Ll}*(\\s\\p{Lu}\\p{Ll}*)*$", message = "Name must be in the correct format.Must be letters not numbers" )
    private String typeName;
    private boolean status;


    public TypeProductDTO() {
    }

    public TypeProductDTO(Integer id, String typeName, boolean status) {
        this.id = id;
        this.typeName = typeName;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
