package br.com.zup.mercado.livre.mercadolivre.validatorhandler;

import java.util.ArrayList;
import java.util.List;

class ValidationErrorsOutputDTO {

    private List<String>  globalErrorMessages = new ArrayList<>();
    private List<FieldErrorOutputDTO> fieldErrors = new ArrayList<>();

    public void addError(String message) {
        globalErrorMessages.add(message);
    }

    public void addFieldError(String field, String message) {
        FieldErrorOutputDTO fieldError = new FieldErrorOutputDTO(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<FieldErrorOutputDTO> getErrors() {
        return fieldErrors;
    }

    public int getNumberOfErrors() {
        return  this.globalErrorMessages.size() + this.fieldErrors.size();
    }
}
