package com.example.sberbankapi.unit;

import com.example.sberbankapi.dto.CardDto;
import com.example.sberbankapi.exception.ExceptionApi;
import com.example.sberbankapi.model.Account;
import com.example.sberbankapi.model.Rights;
import com.example.sberbankapi.dao.CardDaoImplementation;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoCardTest {
    private final CardDaoImplementation cardDaoImplementation = new CardDaoImplementation();

    @Test
    void getShouldTrue() throws SQLException, ExceptionApi {
        assertEquals(cardDaoImplementation.get(10,1),
                new CardDto(10,"5469220062175638" ));
    }
    @Test
    void getAllShouldTrue() throws SQLException, ExceptionApi {
        assertEquals(cardDaoImplementation.getAll(1).get(),
                Stream.of(new CardDto(10,"5469220062175638" )).collect(Collectors.toList()));
    }

    @Test
    void createCardShouldTrue() throws SQLException, ExceptionApi {
        Account account = new Account(1,"1232141", 601, new Date( 2021,9 ,1), true, 1, Rights.ADMIN);
        assertEquals(cardDaoImplementation.create(account), cardDaoImplementation.get(10,1));
    }
}
