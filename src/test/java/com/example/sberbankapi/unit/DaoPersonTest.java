package com.example.sberbankapi.unit;

import com.example.sberbankapi.dto.PersonDto;
import com.example.sberbankapi.exception.ExceptionApi;
import com.example.sberbankapi.dao.PersonDaoImplementation;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoPersonTest {

    private final PersonDaoImplementation personDaoImplementation = new PersonDaoImplementation();

    @Test
    void getPersonShouldTrue() throws ExceptionApi, SQLException {
    assertEquals( personDaoImplementation.get(1),
            new PersonDto(1, "nick", "aler"));
    }
    @Test
    void getAllPersons() throws ExceptionApi, SQLException {
        assertEquals(personDaoImplementation.getAll(),
                Stream.of(new PersonDto(1,"nick","aler"))
                        .collect(Collectors.toList()));
    }


}
