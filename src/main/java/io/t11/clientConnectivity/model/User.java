package io.t11.clientConnectivity.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private String firstName;
    private String lastName;
    private String password;
    private String emailAddress;
    private String DOB;
    private double balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "portfolio_id", referencedColumnName = "id")
    private Portfolio portfolio;

    public User() {
        balance = 100;
    }

    public User(String firstName, String lastName, String password, String emailAddress, String DOB) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.DOB = DOB;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }


    public String getEmailAddress() {
        return emailAddress;
    }

    public String getDOB() {
        return DOB;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(password, user.password) && Objects.equals(emailAddress, user.emailAddress) && Objects.equals(DOB, user.DOB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, password, emailAddress, DOB);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", DOB='" + DOB + '\'' +
                ", portfolio=" + portfolio +
                '}';
    }
}
