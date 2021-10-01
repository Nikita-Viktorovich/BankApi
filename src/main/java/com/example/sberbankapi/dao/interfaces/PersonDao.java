package com.example.sberbankapi.dao.interfaces;

import com.example.sberbankapi.dto.PersonDto;
import com.example.sberbankapi.exception.ExceptionApi;

import java.sql.SQLException;
import java.util.List;

public interface PersonDao {

    List<PersonDto> getAll() throws ExceptionApi, SQLException;

    PersonDto get(int id) throws ExceptionApi, SQLException;

}
