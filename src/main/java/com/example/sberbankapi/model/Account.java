package com.example.sberbankapi.model;

import java.sql.Date;
import java.util.Objects;

public class Account {
    private int id;
    private Rights rights;
    private String number;
    private double balance;
    private Date dateCreated;
    private boolean active;
    private int id_person;

    public Account(int id, String number, double balance, Date dateCreated, boolean active, int id_person, Rights rights) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.dateCreated = dateCreated;
        this.active = active;
        this.id_person = id_person;
        this.rights = rights;
    }

    public Account(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public Rights getRights() {
        return rights;
    }

    public void setRights(Rights rights) {
        this.rights = rights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Double.compare(account.balance, balance) == 0 && active == account.active && id_person == account.id_person && Objects.equals(number, account.number) && Objects.equals(dateCreated, account.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, balance, dateCreated, active, id_person);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", dateCreated=" + dateCreated +
                ", active=" + active +
                ", id_person=" + id_person +
                '}';
    }
}
