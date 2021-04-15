package ro.ubb.catalog.core.model;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@ToString(callSuper = true)
@Table(name = "Users")
public class User extends BaseEntity<Integer> implements Serializable {
    private String FirstName;
    private String LastName;
    private int numberOfTransactions;

    /**
     * @param FirstName - string
     * @param LastName - string
     * @param NumberOfTransactions - Integer
     */
    public User(String FirstName, String LastName, int NumberOfTransactions) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.numberOfTransactions = NumberOfTransactions;
    }

    public User(){}
    /**
     *
     * @param obj - object to be compared
     * @return -true - if the objects are the same or if their ids are the same
     *         -false - if the objects don't have the same class
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj)return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;

        return getId().equals(user.getId());
    }

    /**
     * @return the number of transactions for an User
     */
    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    /**
     *
     * @return the last name of an User
     */
    public String getLastName() {
        return LastName;
    }

    /**
     *
     * @return the first name of an User
     */
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setNumberOfTransactions(int numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }
}
