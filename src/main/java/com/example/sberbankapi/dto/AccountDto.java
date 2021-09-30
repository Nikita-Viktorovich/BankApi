package com.example.sberbankapi.dto;

import java.util.Date;
import java.util.Objects;

public class AccountDto {
    private int id;
    private String number;
    private double balance;
    private Date dateCreated;

    public AccountDto(){}
    public AccountDto(int id, String number, double balance, Date dateCreated) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.dateCreated = dateCreated;
    }
    public AccountDto(int id, String number, double balance) {
        this.id = id;
        this.number = number;
        this.balance = balance;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDto that = (AccountDto) o;
        return id == that.id && Double.compare(that.balance, balance) == 0 && Objects.equals(number, that.number) && Objects.equals(dateCreated, that.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, balance, dateCreated);
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
