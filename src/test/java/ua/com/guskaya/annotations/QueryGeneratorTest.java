package ua.com.guskaya.annotations;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueryGeneratorTest {

    private static final QueryGenerator QUERY_GENERATOR = new QueryGenerator();

    @Test
    public void testGenerateGetAll() throws Exception {
        //when
        String query = QUERY_GENERATOR.generateGetAll(Person.class);

        //then
        assertEquals("SELECT id, name FROM person;", query);
    }

    @Test
    public void testGenerateInsert() throws Exception {
        //when
        String query = QUERY_GENERATOR.generateInsert(Person.class);

        //then
        assertEquals("INSERT INTO person (id, name) VALUES (?, ?);", query);
    }

    @Test
    public void testGenerateGetById() throws Exception {
        //when
        String query = QUERY_GENERATOR.generateGetById(Person.class);

        //then
        assertEquals("SELECT id, name FROM person WHERE id = ?;", query);
    }

    @Test
    public void testGenerateDelete() throws Exception {
        //when
        String query = QUERY_GENERATOR.generateDelete(Person.class);

        //then
        assertEquals("DELETE FROM person WHERE id = ?;", query);
    }
}