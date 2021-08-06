package br.com.zup.mercado.livre.mercadolivre.validatorhandler;

public class FieldErrorOutputDTO {

    private String field;
    private String message;

    FieldErrorOutputDTO() {
    }

    public FieldErrorOutputDTO(String field, String message) {
        this.message = message;
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }

}
