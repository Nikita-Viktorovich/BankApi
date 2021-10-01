package com.example.sberbankapi.dao.interfaces;

import com.example.sberbankapi.dto.AccountDto;
import com.example.sberbankapi.exception.ExceptionApi;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AccountDao {

    Optional<List<AccountDto>> getAll() throws ExceptionApi, SQLException;

    Optional<AccountDto> get(int id) throws ExceptionApi, SQLException;

    Optional<String> getBalance(int idAccount) throws ExceptionApi, SQLException;

    Optional<String> addCash(int idAccount, double sum) throws ExceptionApi, SQLException;

}
