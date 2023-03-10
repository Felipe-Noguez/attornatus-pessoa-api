package com.attornatus.pessoaapi.enums;

import java.util.Arrays;

public enum TipoEndereco {
    PRINCIPAL(0),
    SECUNDARIO(1),
    OUTRO(2);

    private Integer tipo;

    TipoEndereco(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public static TipoEndereco ofTipo(Integer tipo){
        return Arrays.stream(TipoEndereco.values())
                .filter(tp -> tp.getTipo().equals(tipo))
                .findFirst()
                .get();
    }
}
