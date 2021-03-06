package ro.ubb.catalog.core.repository;

import ro.ubb.catalog.core.exceptions.*;
import ro.ubb.catalog.core.model.*;

import java.sql.SQLException;
import java.util.Optional;

public interface Repository<ID, T extends BaseEntity<ID>> {
    /**
     * Find the entity with the given {@code id}.
     *
     * @param id
     *            must be not null.
     * @return an {@code Optional} encapsulating the entity with the given id.
     * @throws IllegalArgumentException
     *             if the given id is null.
     */
    Optional<T> findOne(ID id) throws SQLException;

    /**
     *
     * @return all entities.
     */
    Iterable<T> findAll() throws SQLException;

    /**
     * Saves the given entity.
     *
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was saved otherwise (e.g. id already exists) returns the entity.
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidationException
     *             if the entity is not valid.
     */
    Optional<T> save(T entity) throws ValidationException, SQLException;

    /**
     * Removes the entity with the given id.
     *
     * @param id
     *            must not be null.
     * @return an {@code Optional} - null if there is no entity with the given id, otherwise the removed entity.
     * @throws IllegalArgumentException
     *             if the given id is null.
     */
    Optional<T> delete(ID id) throws SQLException;

    /**
     * Updates the given entity.
     *
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was updated otherwise (e.g. id does not exist) returns the
     *         entity.
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidationException
     *             if the entity is not valid.
     */
    Optional<T> update(T entity) throws ValidationException, SQLException;
}
