package com.example.sberbankapi.dao;

import com.example.sberbankapi.dao.interfaces.IConnection;
import com.example.sberbankapi.dao.interfaces.PersonDao;
import com.example.sberbankapi.dto.PersonDto;
import com.example.sberbankapi.exception.ExceptionApi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImplementation implements PersonDao {

    private static final String SELECT_ALL = "SELECT id, name, surname FROM PERSON";
    private static final String SELECT_ROW = "SELECT id, name, surname FROM PERSON WHERE ID = ?";

    @Override
    public List<PersonDto> getAll() throws ExceptionApi, SQLException {
        List<PersonDto> personList;
        try (Connection connection = IConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet set = preparedStatement.executeQuery();
            if (!set.isBeforeFirst()) throw new ExceptionApi("Not Found", 404);
            personList = new ArrayList<>();
            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("name");
                String surname = set.getString("surname");
                personList.add(new PersonDto(id, name, surname));
            }
        }
        return personList;
    }

    @Override
    public PersonDto get(int id) throws ExceptionApi, SQLException {
        PersonDto person1;
        try (Connection connection = IConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROW);
            preparedStatement.setInt(1, id);
            ResultSet set = preparedStatement.executeQuery();
            if (!set.isBeforeFirst()) throw new ExceptionApi("Not Found", 404);
            set.next();
            String name = set.getString("name");
            String surname = set.getString("surname");
            person1 = new PersonDto(id, name, surname);
        }
        return person1;
    }
}
