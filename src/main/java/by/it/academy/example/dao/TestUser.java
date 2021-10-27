package by.it.academy.example.dao;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TestUser{
    private int id;
    private String name;
    private int number;

    @Override
    public String toString() {
        return "TestUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}

