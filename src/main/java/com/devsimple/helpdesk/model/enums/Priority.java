package com.devsimple.helpdesk.model.enums;

public enum Priority {

    LOW(0, "LOW"),
    AVERAGE(1, "AVERAGE"),
    HIGH(2, "HIGH");

    private Integer code;
    private String description;

    Priority(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Priority toEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (Priority x : Priority.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade inválida");
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
