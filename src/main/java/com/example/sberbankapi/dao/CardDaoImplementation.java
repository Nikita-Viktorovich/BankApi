package com.example.sberbankapi.dao;

import com.example.sberbankapi.dao.interfaces.CardDao;
import com.example.sberbankapi.dao.interfaces.IConnection;
import com.example.sberbankapi.dto.CardDto;
import com.example.sberbankapi.exception.ExceptionApi;
import com.example.sberbankapi.model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class CardDaoImplementation implements CardDao {

    private static final String SELECT_ALL = "SELECT id, number FROM card where id_account = ?";
    private static final String SELECT_ROW = "SELECT id, number FROM card WHERE ID = ? and id_account = ?";
    private static final String INSERT_ROW = "INSERT INTO card ( number, cvc, active, id_account, date_created) values (?,?,?,?,?)";

    @Override
    public Optional<List<CardDto>> getAll(int idAccount)
            throws SQLException, ExceptionApi {
        List<CardDto> cards;
        try (Connection connection = IConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            preparedStatement.setInt(1, idAccount);
            ResultSet set = preparedStatement.executeQuery();
            if (!set.isBeforeFirst()) throw new ExceptionApi("Not Found", 404);
            cards = new ArrayList<>();
            while (set.next()) {
                int id = set.getInt("id");
                String number = set.getString("number");
                cards.add(new CardDto(id, number));
            }
        }
        return Optional.of(cards);
    }

    @Override
    public Optional<CardDto> get(int idAccount, int id)
            throws SQLException, ExceptionApi {
        CardDto card;
        try (Connection connection = IConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROW);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, idAccount);
            ResultSet set = preparedStatement.executeQuery();
            if (!set.isBeforeFirst()) throw new ExceptionApi("Not Found", 404);
            set.next();
            String number = set.getString("number");
            card = new CardDto(id, number);
        }
        return Optional.of(card);
    }

    @Override
    public Optional<String> create(Account account)
            throws SQLException, ExceptionApi {
        try (Connection connection = IConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROW);
            String number = generateLoon();
            preparedStatement.setString(1, number);
            preparedStatement.setInt(2, generateCvc());
            preparedStatement.setBoolean(3, true);
            preparedStatement.setInt(4, account.getId_person());
            preparedStatement.setDate(5, new Date(System.currentTimeMillis()));
            preparedStatement.execute();
        }
        return Optional.of("Your card was success created");
    }

    public String generateLoon() {
        return "5469" + "2200" + new Random().nextInt(9999) + new Random().nextInt(9999);
    }

    public int generateCvc() {
        return new Random().nextInt(999);
    }
}