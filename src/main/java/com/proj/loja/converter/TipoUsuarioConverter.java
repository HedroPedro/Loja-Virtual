package com.proj.loja.converter;

import static com.proj.loja.model.TipoUsuario.fromId;

import com.proj.loja.model.TipoUsuario;

import jakarta.persistence.AttributeConverter;

public class TipoUsuarioConverter implements AttributeConverter<TipoUsuario, Integer>{

    @Override
    public Integer convertToDatabaseColumn(TipoUsuario attribute) {
        return attribute.getId();
    }

    @Override
    public TipoUsuario convertToEntityAttribute(Integer dbData) {
        return fromId(dbData);
    }
    
}
