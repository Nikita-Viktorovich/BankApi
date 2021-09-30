package com.example.sberbankapi.service;

import com.example.sberbankapi.dto.PersonDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {

    public List<PersonDto> getAll(){
        List<PersonDto> personList = null;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SberDB","a19558994", "1234")){
            Class.forName("org.postgresql.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name, surname FROM PERSON ");
            ResultSet set = preparedStatement.executeQuery();
            personList = new ArrayList<>();
            while (set.next()){
                int id = set.getInt("id");
                String name = set.getString("name");
                String surname = set.getString("surname");
                personList.add(new PersonDto(id,name,surname));
            }
            if(personList.size() == 0) System.out.println("Not found");

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return personList;
    }

    public PersonDto get(int id){
        PersonDto person1= null;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SberDB","a19558994", "1234")) {
            Class.forName("org.postgresql.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name, surname FROM PERSON WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet set = preparedStatement.executeQuery();
            set.next();
                String name = set.getString("name");
                String surname = set.getString("surname");
                person1 = new PersonDto(id, name, surname);

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return person1;
    }
}
