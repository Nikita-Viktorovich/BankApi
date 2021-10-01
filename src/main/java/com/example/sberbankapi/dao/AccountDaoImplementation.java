package com.example.sberbankapi.dao;

import com.example.sberbankapi.dao.interfaces.AccountDao;
import com.example.sberbankapi.dao.interfaces.IConnection;
import com.example.sberbankapi.dto.AccountDto;
import com.example.sberbankapi.exception.ExceptionApi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDaoImplementation implements AccountDao {

    private static final String SELECT_ALL = "SELECT id, number, balance FROM account";
    private static final String SELECT_ROW_ACCOUNT = "SELECT number, balance FROM account WHERE ID = ?";
    private static final String SELECT_BALANCE = "SELECT balance FROM account WHERE ID = ?";
    private static final String UPDATE_BALANCE = "UPDATE account set balance  = ?  WHERE ID = ?";


    @Override
    public Optional<List<AccountDto>> getAll()
            throws ExceptionApi, SQLException {
        List<AccountDto> accounts;
        try (Connection connection = IConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet set = preparedStatement.executeQuery();
            if (!set.isBeforeFirst()) throw new ExceptionApi("Not Found", 404);
            accounts = new ArrayList<>();
            while (set.next()) {
                int id = set.getInt("id");
                String number = set.getString("number");
                double balance = set.getDouble("balance");
                accounts.add(new AccountDto(id, number, balance));
            }
            return Optional.of(accounts);
        }
    }

    @Override
    public Optional<AccountDto> get(int id) throws ExceptionApi, SQLException {
        AccountDto account;
        try (Connection connection = IConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROW_ACCOUNT);
            preparedStatement.setInt(1, id);
            ResultSet set = preparedStatement.executeQuery();
            if (!set.isBeforeFirst()) throw new ExceptionApi("Not Found", 404);
            set.next();
            String number = set.getString("number");
            double balance = set.getDouble("balance");
            account = new AccountDto(id, number, balance);
        }
        return Optional.of(account);
    }

    @Override
    public Optional<String> getBalance(int idAccount) throws ExceptionApi, SQLException {
        String balance;
        try (Connection connection = IConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BALANCE);
            preparedStatement.setInt(1, idAccount);
            ResultSet set = preparedStatement.executeQuery();
            if (!set.isBeforeFirst()) throw new ExceptionApi("Not Found", 404);
            set.next();
            balance = set.getString("balance");
        }
        return Optional.of(balance);
    }


    @Override
    public Optional<String> addCash(int idAccount, double sum) throws ExceptionApi, SQLException {
        String balance;
        double newBalance;
        try (Connection connection = IConnection.getConnection()) {
            balance = getBalance(idAccount).get();
            newBalance = Double.parseDouble(balance) + sum;
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BALANCE);
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setInt(2, idAccount);
            preparedStatement.execute();
        }
        return Optional.of(String.valueOf(newBalance));
    }
}