package co.uk.virginmoneyexercise.converter;

import co.uk.virginmoneyexercise.enums.TransactionType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {

    @Override
    public String convertToDatabaseColumn(final TransactionType type) {
        if (type == null) {
            return null;
        }
        return type.getDescription();
    }

    @Override
    public TransactionType convertToEntityAttribute(final String description) {
        if (description == null) {
            return null;
        }

        return Stream.of(TransactionType.values()).filter(t -> t.getDescription().equals(description))
                                                  .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}