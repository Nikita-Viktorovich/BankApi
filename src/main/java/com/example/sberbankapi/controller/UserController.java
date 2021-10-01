package com.example.sberbankapi.controller;

import com.example.sberbankapi.dto.CardDto;
import com.example.sberbankapi.exception.ExceptionApi;
import com.example.sberbankapi.model.Account;
import com.example.sberbankapi.dao.AccountDaoImplementation;
import com.example.sberbankapi.dao.CardDaoImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/user-api")
public class UserController {

    private final CardDaoImplementation cardDaoImplementation = new CardDaoImplementation();
    private final AccountDaoImplementation accountDao = new AccountDaoImplementation();

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/get-cards/{id}")
    public ResponseEntity<Optional<List<CardDto>>> getCards(@PathVariable int id) throws ExceptionApi, SQLException {
        LOGGER.info("invoke {}" , id);
        Optional<List<CardDto>> cards = cardDaoImplementation.getAll(1);
        if (cards.isPresent()) return new ResponseEntity<>(cards, new HttpHeaders(), HttpStatus.OK);
        else return new ResponseEntity<>(Optional.empty(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/get-balance/")
    public ResponseEntity<Optional<String>> getBalance(@RequestParam int id) throws ExceptionApi, SQLException {
        LOGGER.info("invoke {}", id);
        Optional<String> balance = accountDao.getBalance(id);
       if (balance.isPresent())  return new ResponseEntity<>(balance, new HttpHeaders(), HttpStatus.OK);
       else return new ResponseEntity<>(Optional.empty(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/add-cash/{id}/{sum}")
    public ResponseEntity<Optional<String>> addCash(@PathVariable int id, @PathVariable double sum) throws ExceptionApi, SQLException {
        LOGGER.info("invoke {}" , id);
        Optional<String> balance = accountDao.addCash(id, sum);
        if (balance.isPresent())  return new ResponseEntity<>(balance, new HttpHeaders(), HttpStatus.OK);
        else return new ResponseEntity<>(Optional.empty(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add-card")
    public ResponseEntity<Optional<String>> addCard(@RequestBody Account account) throws ExceptionApi, SQLException {
        LOGGER.info("invoke {}" , account);
        Optional<String> card = cardDaoImplementation.create(account);
        if (card.isPresent())  return new ResponseEntity<>(card, new HttpHeaders(), HttpStatus.OK);
        else return new ResponseEntity<>(Optional.empty(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }






}
