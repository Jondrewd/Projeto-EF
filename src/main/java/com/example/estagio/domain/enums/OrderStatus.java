package com.example.estagio.domain.enums;

public enum OrderStatus {
    PENDING(1),
    APPROVED(2),
    REJECTED(3);

    private int code;

    private OrderStatus(int code) {
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static OrderStatus valueOf(int code){
        for(OrderStatus value : OrderStatus.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Codigo invalido!");
    }
}
