package ro.ubb.catalog.core.model;

import lombok.ToString;
import org.hibernate.annotations.Check;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;

@Entity
@Transactional
@ToString(callSuper = true)
@Table(name = "Transactions")
public class Transaction extends BaseEntity<Integer> implements Serializable {
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "RecordID")
    private Record record;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Check(constraints = "quantity >= 0")
    private int quantity;

    /**
     * @param user - User
     * @param record - Record
     * @param date - Date
     * @param quantity - Integer
     */
    public Transaction(User user, Record record, Date date, int quantity) {
        this.user = user;
        this.record = record;
        this.date = date;
        this.quantity = quantity;
    }

    public Transaction(){}


    /**
     * @param obj - and object
     * @return -true - if the objects are the same or if their ids are the same
     *         -false - if the objects don't have the same class
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj)return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Transaction transaction = (Transaction) obj;

        return getId().equals(transaction.getId());
    }

    /**
     * @return the Date for a Transaction
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the quantity for a Transaction
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return the ID of the User that did the Transaction
     */
    public User getUser() {
        return user;
    }

    /**
     * @return the ID of the Record that was bought in the Transaction
     */
    public Record getRecord() {
        return record;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setRecord(Record record){
        this.record = record;
    }
}
