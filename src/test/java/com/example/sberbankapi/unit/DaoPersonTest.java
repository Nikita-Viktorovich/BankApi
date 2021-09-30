package com.example.sberbankapi.unit;

import com.example.sberbankapi.dto.PersonDto;
import com.example.sberbankapi.service.PersonDao;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoPersonTest {

    private final PersonDao personDao = new PersonDao();

    @Test
    void getPersonShouldTrue() {
    assertEquals( personDao.get(1),
            new PersonDto(1, "nick", "aler"));
    }
    @Test
    void getAllPersons(){
        assertEquals(personDao.getAll(),
                Stream.of(new PersonDto(1,"nick","aler"))
                        .collect(Collectors.toList()));
    }


}
