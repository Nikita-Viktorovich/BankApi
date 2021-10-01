package com.example.sberbankapi.model;

import java.sql.Date;
import java.util.Objects;

public class Card {
    private int id;
    private String number;
    private int cvc;
    private boolean active;
    private Date dateCreated;
    private int id_account;

    public Card(int id, String number, int cvc, boolean active, Date date, int id_account) {
        this.id = id;
        this.number = number;
        this.cvc = cvc;
        this.active = active;
        this.id_account = id_account;
        this.dateCreated = date;
    }

    public Card(String number, int cvc, boolean active, int id_account) {
        this.id = id;
        this.number = number;
        this.cvc = cvc;
        this.active = active;
        this.id_account = id_account;
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

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public Date getDate() {
        return dateCreated;
    }

    public void setDate(Date date) {
        this.dateCreated = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && cvc == card.cvc && active == card.active && id_account == card.id_account && Objects.equals(number, card.number)&& Objects.equals(dateCreated, card.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, cvc, active, dateCreated,id_account);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", cvc=" + cvc +
                ", active=" + active +
                ", id_account=" + id_account +
                ",date=" + dateCreated +
                '}';
    }
}
