package org.example.valueobject;

import org.example.ValidateionException;

import java.util.Objects;

public abstract class ValueObject<T> {
    public static String TEXT_ERROR =";lk";
    private final T value;
    
    public ValueObject(T value) throws ValidateionException {
        if (!isValidate(value)) {
            throw new ValidateionException(String.format("%s %s", TEXT_ERROR, value));
        }
        
        this.value = value;
    }
    
    protected abstract boolean isValidate(T value) throws ValidateionException;
    
    public T getValue() {
        return value;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ValueObject valueObject = (ValueObject) o;
        return Objects.equals(valueObject.getValue(), this.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
