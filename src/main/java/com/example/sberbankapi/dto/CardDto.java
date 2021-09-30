package com.example.sberbankapi.dto;

import java.util.Objects;

public class CardDto {
    private int id;
    private String number;

    public CardDto(){}

    public CardDto(int id, String number) {
        this.id = id;
        this.number = number;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDto cardDto = (CardDto) o;
        return id == cardDto.id && Objects.equals(number, cardDto.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }

    @Override
    public String toString() {
        return "CardDto{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
