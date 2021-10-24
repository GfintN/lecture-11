package by.it.academy.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserInformationJDBC {
    private static final Logger logger = LoggerFactory.getLogger("LOGS");
    private static String url = "jdbc:postgresql://localhost:5432/test_db";
    private static String login = "postgres";
    private static String password = "root";

    public static Connection getConnection (){
        try {
            logger.trace("getConnection success");
            return DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            logger.error("There was an error in the class UserInformationJDBC",e);
            return null;
        }
    }

    public void setUrl(String url) {
        UserInformationJDBC.url = url;
    }

    public void setLogin(String login) {
        UserInformationJDBC.login = login;
    }

    public void setPassword(String password) {
        UserInformationJDBC.password = password;
    }
}
