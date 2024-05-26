package com.book.store.db.converter;

import com.book.store.enums.Genre;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GenreConverter implements AttributeConverter<Genre, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Genre attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public Genre convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Genre.fromValue(dbData);
    }
}
