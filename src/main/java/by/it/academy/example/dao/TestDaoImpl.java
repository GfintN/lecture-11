package by.it.academy.example.dao;


import by.it.academy.example.CommandsSQL;
import by.it.academy.example.UserInformationJDBC;
import com.zaxxer.hikari.pool.HikariPool;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TestDaoImpl<S,P> implements TestDAO<S,P>{
    private Connection connection;
    private HikariPool connectionPool;

    public TestDaoImpl() {
        connection = UserInformationJDBC.getConnection();
        try {
            connectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeTestDaoConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public P save(P p) throws SQLException {
        String sql = CommandsSQL.getInsert();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int count = statement.executeUpdate();
            connection.commit();
            return null;
        } catch (Exception e) {
            connection.rollback();
            return null;
        }

    }

    @Override
    public P get(Serializable id) throws SQLException {
        String sql = CommandsSQL.getSelect();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet count = statement.executeQuery();
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void update(P p) throws SQLException {
        String sql = CommandsSQL.getUpdate();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int count = statement.executeUpdate();
        }
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        String sql = CommandsSQL.getDelete();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int count = statement.executeUpdate();
            if (count != 1) {
                return count;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<S> getAll() throws SQLException {
        List<S> lst = new LinkedList<>();
        PreparedStatement ps = getPrepareStatement("SELECT * FROM test_db.jdbc_test");
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TestUser user = new TestUser();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setNumber(rs.getInt(3));
                lst.add((S) user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return lst;
    }

    @Override
    public S getOneByTwo() throws SQLException {
        String sql = CommandsSQL.getJoin();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet count = statement.executeQuery();
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
