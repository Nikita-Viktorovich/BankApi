package com.example.sberbankapi.dao.interfaces;

import com.example.sberbankapi.dto.CardDto;
import com.example.sberbankapi.exception.ExceptionApi;
import com.example.sberbankapi.model.Account;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CardDao {

    Optional<List<CardDto>> getAll(int idAccount) throws SQLException, ExceptionApi;

    Optional<CardDto> get(int idAccount, int id) throws SQLException, ExceptionApi;

    Optional<String> create(Account account) throws SQLException, ExceptionApi;


}
