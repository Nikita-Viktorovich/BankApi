package com.example.sberbankapi.integration;

import com.example.sberbankapi.controller.UserController;
import com.example.sberbankapi.dto.CardDto;
import com.example.sberbankapi.exception.ExceptionApi;
import com.example.sberbankapi.dao.CardDaoImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
class SberBankApiApplicationTests {

    @Mock
    private final CardDaoImplementation cardDaoImplementation = new CardDaoImplementation();
    @Autowired
    private MockMvc mvc;

    @Test
    void testGetCardDB() throws SQLException, ExceptionApi {
        when(cardDaoImplementation.get(10,1)).thenReturn(java.util.Optional.of(new CardDto(10, "5469220062175638")));

        CardDto cardFromDB = cardDaoImplementation.get(10,1).get();
        Assertions.assertEquals(cardFromDB.getId(), 10, 0);
        verify(cardDaoImplementation).get(10,1);
    }

    @Test
    void testGetListCardsDB() throws SQLException, ExceptionApi {
        List<CardDto> cards = new ArrayList<>();
        cards.add(new CardDto(10, "5469220062175638"));

       when(cardDaoImplementation.getAll(1)).thenReturn(java.util.Optional.of(cards));

        Assertions.assertEquals(cards, cardDaoImplementation.getAll(1));

        verify(cardDaoImplementation).getAll(1);
    }


}
