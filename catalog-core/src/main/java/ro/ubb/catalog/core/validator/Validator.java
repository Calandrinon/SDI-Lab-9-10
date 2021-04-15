package ro.ubb.catalog.core.validator;

import ro.ubb.catalog.core.exceptions.*;
import ro.ubb.catalog.core.model.*;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}
