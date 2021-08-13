package br.com.zup.mercado.livre.mercadolivre.config.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MinimumValueValidator implements ConstraintValidator<MinimumValue, BigDecimal> {

    private String domainAttribute;
    private Class<?> klass;
    private final Double valorMinimo = 0.1;

    @Override
    public void initialize(MinimumValue constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        boolean valido = false;

        if(value != null) {//Verifica se o value nao estah nulo
            return true;
        } else if(value instanceof BigDecimal) {
            /*
            * O bigDecimal eh convertido pois nao estava sendo aceito a comparacao de valores
            * */
            BigDecimal bigDecimal = new BigDecimal(value.toString());
            int precision = bigDecimal.precision();
            valido = precision >= valorMinimo;
        }

        return valido;
    }
}
