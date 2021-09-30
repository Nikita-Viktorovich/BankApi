package com.example.sberbankapi.integration;

import com.example.sberbankapi.controller.UserController;
import com.example.sberbankapi.dto.CardDto;
import com.example.sberbankapi.service.CardDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
    private final CardDao cardDao = new CardDao();
    @Autowired
    private MockMvc mvc;

    @Test
    void testGetCardDB() {
        when(cardDao.get(10)).thenReturn(new CardDto(10, "5469220062175638"));

        CardDto cardFromDB = cardDao.get(10);
        Assertions.assertEquals(cardFromDB.getId(), 10, 0);
        verify(cardDao).get(10);
    }

    @Test
    void testGetListCardsDB() {
        List<CardDto> cards = new ArrayList<>();
        cards.add(new CardDto(10, "5469220062175638"));

       when(cardDao.getAll()).thenReturn(cards);

        Assertions.assertEquals(cards, cardDao.getAll());

        verify(cardDao).getAll();
    }


}
