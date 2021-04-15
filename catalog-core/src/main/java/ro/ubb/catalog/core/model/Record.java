package ro.ubb.catalog.core.model;

import lombok.ToString;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString(callSuper = true)
@Table(name = "Records")
public class Record extends BaseEntity<Integer> implements Serializable {

    @Check(constraints = "Price >= 0")
    private int price;
    private String AlbumName;
    @Check(constraints = "InStock >= 0")
    private int inStock;

    @Enumerated(EnumType.STRING)
    private RecordType TypeOfRecord;

    /**
     * @param price - integer
     * @param AlbumName - string
     * @param inStock - integer
     * @param typeOfRecord - RecordType
     */
    public Record(int price, String AlbumName, int inStock, RecordType typeOfRecord) {
        this.price = price;
        this.AlbumName = AlbumName;
        this.inStock = inStock;
        TypeOfRecord = typeOfRecord;
    }

    public Record(){}

    /**
     *
     * @param obj - an object to be compared
     * @return true - if the objects are the same or if their ids are the same
     *         false - if the objects don't have the same class
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj)return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Record record = (Record) obj;

        return getId().equals(record.getId());
    }

    /**
     *
     * @return the price of a Record
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @return the album name of a record
     */
    public String getAlbumName() {
        return AlbumName;
    }

    /**
     *
     * @return the amount of stock for a Record
     */
    public int getInStock() {
        return inStock;
    }

    /**
     *
     * @return the record type of a record
     */
    public RecordType getTypeOfRecord() {
        return TypeOfRecord;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAlbumName(String albumName) {
        AlbumName = albumName;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setTypeOfRecord(RecordType typeOfRecord) {
        TypeOfRecord = typeOfRecord;
    }
}
