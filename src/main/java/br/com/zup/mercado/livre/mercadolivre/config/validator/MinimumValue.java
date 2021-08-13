package br.com.zup.mercado.livre.mercadolivre.config.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {MinimumValueValidator.class})
@Target({ FIELD })
@Retention(RUNTIME)
public @interface MinimumValue {

    String message() default "Valor minimo: 0.01";

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String fieldName();
    Class<?> domainClass();
}
