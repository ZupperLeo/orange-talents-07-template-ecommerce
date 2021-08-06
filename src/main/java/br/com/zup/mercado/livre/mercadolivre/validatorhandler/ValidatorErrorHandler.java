package br.com.zup.mercado.livre.mercadolivre.validatorhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidatorErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorsOutputDTO handleValidationError(MethodArgumentNotValidException exception) {

        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(globalErrors, fieldErrors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ValidationErrorsOutputDTO handleValidationError(BindException exception) {

        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(globalErrors, fieldErrors);
    }

    private ValidationErrorsOutputDTO buildValidationErrors(List<ObjectError> globalErrors,
                List<FieldError> fieldErrors) {
        ValidationErrorsOutputDTO  validationErrors = new ValidationErrorsOutputDTO();

        globalErrors.forEach(error -> validationErrors.addError(getErrorsMessage(error)));

        fieldErrors.forEach(error -> {
            String errorMessage =  getErrorsMessage(error);
            validationErrors.addFieldError(error.getField(), errorMessage);
        });
        return validationErrors;
    }

    private String getErrorsMessage(ObjectError error) {
        return  messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
}
