package com.example.sberbankapi.controller;

import com.example.sberbankapi.dto.CardDto;
import com.example.sberbankapi.model.Account;
import com.example.sberbankapi.service.AccountDao;
import com.example.sberbankapi.service.CardDao;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/user-api")
public class UserController {

    private final CardDao cardDao = new CardDao();
    private final AccountDao accountDao = new AccountDao();

    @GetMapping("/get-cards")
    public ResponseEntity<Optional<List<CardDto>>> getCards(){
        Optional<List<CardDto>> cards = Optional.ofNullable(cardDao.getAll());
        if (cards.isPresent()) return new ResponseEntity<>(cards, new HttpHeaders(), HttpStatus.OK);
        else return new ResponseEntity<>(Optional.empty(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/get-balance/{id}")
    public ResponseEntity<Optional<String>> getBalance(@PathVariable int id){
        Optional<String> balance = Optional.ofNullable(accountDao.getBalance(id));
       if (balance.isPresent())  return new ResponseEntity<>(balance, new HttpHeaders(), HttpStatus.OK);
       else return new ResponseEntity<>(balance, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/add-cash/{id}/{sum}")
    public ResponseEntity<Optional<String>> addCash(@PathVariable int id, @PathVariable double sum){
        Optional<String> balance = Optional.ofNullable(accountDao.addCash(id, sum));
        if (balance.isPresent())  return new ResponseEntity<>(balance, new HttpHeaders(), HttpStatus.OK);
        else return new ResponseEntity<>(Optional.empty(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add-card")
    public ResponseEntity<Optional<CardDto>> addCard(@RequestBody Account account){
       Optional<CardDto> card = Optional.ofNullable(cardDao.create(account));
        if (card.isPresent())  return new ResponseEntity<>(card, new HttpHeaders(), HttpStatus.OK);
        else return new ResponseEntity<>(Optional.empty(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }






}
