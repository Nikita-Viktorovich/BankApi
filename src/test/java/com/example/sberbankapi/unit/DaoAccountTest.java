package com.example.sberbankapi.unit;

import com.example.sberbankapi.dto.AccountDto;
import com.example.sberbankapi.service.AccountDao;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoAccountTest {

private final AccountDao accountDao = new AccountDao();

    @Test
    void getShouldTrue(){
        assertEquals(accountDao.get(1),
                new AccountDto(1,"1232141", 601 )
        );
    }

    @Test
    void getAllShouldTrue(){
        assertEquals(accountDao.getAll(),
                Stream.of(
                        new AccountDto(
                        1,"1232141", 601 ))
                        .collect(Collectors.toList())
        );
    }

    @Test
    void getBalanceShouldTrue(){
        assertEquals(Double.parseDouble(accountDao.getBalance(1)),
                accountDao.get(1).getBalance()
        );
    }

    @Test
    void addCashShouldTrue(){
        final double sum = 150.0;
        assertEquals(
                String.valueOf(
                Double.parseDouble(accountDao.getBalance(1)) + 150.0),
                accountDao.addCash(1, 150.0)
        );
    }

}
