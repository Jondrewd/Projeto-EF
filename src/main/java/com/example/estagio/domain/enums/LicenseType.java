package com.example.estagio.domain.enums;

public enum LicenseType {
    FUNCIONAMENTO(1),
    OBRAS(2),
    AMBIENTAL(3),
    EVENTOS(4),
    PUBLICIDADE(5),
    SANITARIA(6),
    DEMOLICAO(7),
    TRANSPORTE(8);

    private int code;

    private LicenseType(int code) {
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static LicenseType valueOf(int code){
        for(LicenseType value : LicenseType.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Codigo invalido!");
    }
}
