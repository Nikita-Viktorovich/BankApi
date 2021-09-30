package com.example.sberbankapi.unit;

import com.example.sberbankapi.dto.CardDto;
import com.example.sberbankapi.model.Account;
import com.example.sberbankapi.service.CardDao;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoCardTest {
    private final CardDao cardDao = new CardDao();

    @Test
    void getShouldTrue(){
        assertEquals(cardDao.get(10),
                new CardDto(10,"5469220062175638" ));
    }
    @Test
    void getAllShouldTrue(){
        assertEquals(cardDao.getAll(),
                Stream.of(new CardDto(10,"5469220062175638" )).collect(Collectors.toList()));
    }

    @Test
    void createCardShouldTrue(){
        Account account = new Account(1,"1232141", 601, new Date( 2021,9 ,1), true, 1);
        assertEquals(cardDao.create(account), cardDao.get(10));
    }
}
