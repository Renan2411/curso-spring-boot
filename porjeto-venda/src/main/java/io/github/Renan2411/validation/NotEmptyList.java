package io.github.Renan2411.validation;

import io.github.Renan2411.validation.constraintvalidation.NotEmptyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)//Indica que é usada em um campo
@Constraint(validatedBy = NotEmptyListValidator.class)//Indica a classe que realizará a validação
public @interface NotEmptyList {

    String message() default "A lista não pode ser vazia";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
