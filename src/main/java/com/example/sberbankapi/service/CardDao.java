package com.example.sberbankapi.service;

import com.example.sberbankapi.dto.CardDto;
import com.example.sberbankapi.model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardDao {

    public List<CardDto> getAll() {
        List<CardDto> cards = null;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SberDB", "a19558994", "1234")) {
            Class.forName("org.postgresql.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, number FROM card");
            ResultSet set = preparedStatement.executeQuery();
            cards = new ArrayList<>();
            while (set.next()) {
                int id = set.getInt("id");
                String number = set.getString("number");
                cards.add(new CardDto(id,number));
            }
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
        return cards;
    }

    public CardDto get(int id) {
        CardDto card = null;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SberDB", "a19558994", "1234")) {
            Class.forName("org.postgresql.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, number FROM card WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet set = preparedStatement.executeQuery();
            set.next();
            String number = set.getString("number");
            card = new CardDto(id, number);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
        return card;
    }

    public CardDto create(Account account){
        CardDto cardDto = null;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SberDB", "a19558994", "1234")) {
            Class.forName("org.postgresql.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO card ( number, cvc, active, id_account) values (?,?,?,?)"
            );
            preparedStatement.setString(1, generateLoon());
            preparedStatement.setInt(2, generateCvc());
            preparedStatement.setBoolean(3,true);
            preparedStatement.setInt(4, account.getId_person());
            preparedStatement.execute();

            PreparedStatement ps = connection.prepareStatement("Select id, number from card");
            ResultSet set = ps.executeQuery();
            set.next();
            cardDto = new CardDto( set.getInt("id"), set.getString("number") );
    } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return cardDto;
    }

    public String generateLoon(){
    return "5469" + "2200" + new Random().nextInt(9999) + new Random().nextInt(9999);}

    public int generateCvc(){ return new Random(10).nextInt(999); }
}