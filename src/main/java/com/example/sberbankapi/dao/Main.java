package com.example.sberbankapi.dao;

import com.example.sberbankapi.exception.ExceptionApi;
import com.example.sberbankapi.model.Account;
import com.example.sberbankapi.model.Rights;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ExceptionApi, SQLException {
        PersonDaoImplementation personDaoImplementation = new PersonDaoImplementation();
        System.out.println(personDaoImplementation.getAll());
        System.out.println(personDaoImplementation.get(1));

        CardDaoImplementation cardDaoImplementation = new CardDaoImplementation();
        System.out.println(cardDaoImplementation.create(new Account(1, "12332141", 751, new Date(2021, 9, 1), true, 1, Rights.ADMIN)));
        System.out.println(cardDaoImplementation.getAll(1));
        System.out.println(cardDaoImplementation.get(10, 1));

        AccountDaoImplementation accountDao = new AccountDaoImplementation();
        System.out.println(accountDao.getAll());
        System.out.println(accountDao.get(1));
        System.out.println(accountDao.getBalance(1));

        System.out.println(accountDao.addCash(1, 100));
    }
}
