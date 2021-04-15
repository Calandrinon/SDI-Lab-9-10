package ro.ubb.catalog.core.model;

import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@ToString(callSuper = true)
public class BaseEntity<ID> implements Serializable {
    @Id
    private ID id;

    /**
     *
     * @return the id of a BaseEntity
     */
    public ID getId() {
        return id;
    }

    /**
     *
     * @param id - the new id for a BaseEntity
     */
    public void setId(ID id) {
        this.id = id;
    }
}
