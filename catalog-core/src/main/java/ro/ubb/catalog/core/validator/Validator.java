package ro.ubb.catalog.core.validator;

import ro.ubb.catalog.core.exceptions.*;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}
