package com.example.sberbankapi.unit;

import com.example.sberbankapi.dto.AccountDto;
import com.example.sberbankapi.exception.ExceptionApi;
import com.example.sberbankapi.dao.AccountDaoImplementation;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoAccountTest {

private final AccountDaoImplementation accountDao = new AccountDaoImplementation();

    @Test
    void getShouldTrue() throws ExceptionApi, SQLException {
        assertEquals(accountDao.get(1).get(),
                new AccountDto(1,"1232141", 601 )
        );
    }

    @Test
    void getAllShouldTrue() throws ExceptionApi, SQLException {
        assertEquals(accountDao.getAll(),
                Stream.of(
                        new AccountDto(
                        1,"1232141", 601 ))
                        .collect(Collectors.toList())
        );
    }

    @Test
    void getBalanceShouldTrue() throws ExceptionApi, SQLException {
        assertEquals(Double.parseDouble(accountDao.getBalance(1).get()),
                accountDao.get(1).get().getBalance()
        );
    }

    @Test
    void addCashShouldTrue() throws ExceptionApi, SQLException {
        final double sum = 150.0;
        assertEquals(
                String.valueOf(
                Double.parseDouble(accountDao.getBalance(1).get()) + 150.0),
                accountDao.addCash(1, 150.0)
        );
    }

}
