package by.it.academy.example.dao;

import java.sql.SQLException;
import java.util.List;

public interface TestDAO<E,K> extends DAO<K>{
    List<E> getAll() throws SQLException;
    E getOneByTwo() throws SQLException;
}
