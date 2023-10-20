package com.wd.education.core.model.converter;

import com.wd.education.core.exception.EnumTypeConverterException;
import jakarta.persistence.AttributeConverter;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
public class BaseTypeConverter<T extends Enum<T>, Y> implements AttributeConverter<T, Y> {

  private final Map<Y, T> toEnumType;
  private final Function<T, Y> toDataBaseColumn;

  @Override
  public Y convertToDatabaseColumn(final T value) {
    return toDataBaseColumn.apply(value);
  }

  @Override
  public T convertToEntityAttribute(final Y value) {
    if (value == null) {
      return null;
    }
    final var enumKey = toEnumType.get(value);

    if (enumKey == null) {
      throw new EnumTypeConverterException(
          "Enum type with value = %s was not found".formatted(value));
    }
    return enumKey;
  }

  protected static <T extends Enum<T>, Y> Map<Y, T> buildMap
      (final Class<T> enumType, final Function<T, Y> toEnumKey) {
    return Arrays.stream(enumType.getEnumConstants())
        .collect(toMap(toEnumKey, Function.identity()));
  }
}
