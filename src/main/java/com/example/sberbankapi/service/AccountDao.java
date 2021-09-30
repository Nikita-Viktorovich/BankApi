package com.example.sberbankapi.service;

import com.example.sberbankapi.dto.AccountDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    public List<AccountDto> getAll() {
        List<AccountDto> accounts = null;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SberDB", "a19558994", "1234")) {
            Class.forName("org.postgresql.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, number, balance FROM account");
            ResultSet set = preparedStatement.executeQuery();
            accounts = new ArrayList<>();
            while (set.next()) {
                int id = set.getInt("id");
                String number = set.getString("number");
                double balance = set.getDouble("balance");
                accounts.add(new AccountDto(id, number, balance));
            }
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
        return accounts;
    }

    public AccountDto get(int id) {
        AccountDto account = null;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SberDB", "a19558994", "1234")) {
            Class.forName("org.postgresql.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT number, balance FROM account WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet set = preparedStatement.executeQuery();
            set.next();
            String number = set.getString("number");
            double balance = set.getDouble("balance");
            account = new AccountDto(id, number, balance);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
        return account;
    }

    public String getBalance(int id) {
        String balance = "";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SberDB", "a19558994", "1234")) {
            Class.forName("org.postgresql.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT balance FROM account WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet set = preparedStatement.executeQuery();
            set.next();
            balance = set.getString("balance");

        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
        return balance;
    }


    public String addCash(int idAccount, double sum) {
        String balance = "";
        double newBalance = 0.0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SberDB", "a19558994", "1234")) {
            Class.forName("org.postgresql.Driver");
            PreparedStatement ps = connection.prepareStatement("Select balance from account where id = ?");
            ps.setInt(1, idAccount);
            ResultSet set = ps.executeQuery();
            set.next();
            balance = set.getString("balance");
            newBalance = Double.parseDouble(balance) + sum;
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE account set balance  = ?  WHERE ID = ?");
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setInt(2, idAccount);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
        return String.valueOf(newBalance);
    }
}