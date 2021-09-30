package com.example.sberbankapi.service;

public class Main {
    public static void main(String[] args) {
        PersonDao personDao = new PersonDao() ;
        System.out.println(personDao.getAll());
        System.out.println(personDao.get(1));

        CardDao cardDao = new CardDao();
       // System.out.println(cardDao.create(new Account(1, "12332141", 1, new Date(2021,9,1), true, 1)));
        System.out.println(cardDao.getAll());
        System.out.println(cardDao.get(10));

        AccountDao accountDao = new AccountDao();
        System.out.println(accountDao.getAll());
        System.out.println(accountDao.get(1));
        System.out.println(accountDao.getBalance(1));

        System.out.println(accountDao.addCash(1, 100));
    }
}
