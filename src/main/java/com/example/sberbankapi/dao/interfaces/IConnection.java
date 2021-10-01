package com.example.sberbankapi.dao.interfaces;

import com.example.sberbankapi.exception.ExceptionApi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface IConnection {
    static Connection getConnection() throws ExceptionApi {
        Connection connect;
        try {
            Connection connection = DriverManager.
                    getConnection("jdbc:postgresql://localhost:5432/SberDB", "a19558994", "1234");
            Class.forName("org.postgresql.Driver");
            connect = connection;
        } catch (SQLException | ClassNotFoundException sqlException) {
            throw new ExceptionApi("Database exception. Connection closed.", 500);
        }
        return connect;
    }
}
