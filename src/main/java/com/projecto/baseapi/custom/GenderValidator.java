package com.projecto.baseapi.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class GenderValidator  implements ConstraintValidator<GenderValidation,String> {

    @Override
    public void initialize(GenderValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext constraintValidatorContext) {
        List list = Arrays.asList(new String[] {"MALE","FEMALE","male","female","OTHERS","others"});
        return list.contains(gender);
    }
}
