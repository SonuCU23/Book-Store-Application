package com.book.store.db.converter;

import com.book.store.enums.TransactionStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TransactionStatusConverter implements AttributeConverter<TransactionStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TransactionStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public TransactionStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return TransactionStatus.fromValue(dbData);
    }
}
