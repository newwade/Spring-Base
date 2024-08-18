package com.projecto.baseapi.custom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = GenderValidator.class)
public @interface GenderValidation {
    public String message() default "Invalid value : please enter a valid gender";
    public Class<?>[] groups() default{};
    public Class<? extends Payload>[] payload() default {};

}
