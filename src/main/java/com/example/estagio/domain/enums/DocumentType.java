package com.example.estagio.domain.enums;

public enum DocumentType {
    PDF(1),
    JPEG(2);

    private int code;

    private DocumentType(int code) {
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static DocumentType valueOf(int code){
        for(DocumentType value : DocumentType.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Codigo invalido!");
    }
}
