package db;

import java.sql.DriverManager;
import java.sql.SQLException;

class Conn {

    java.sql.Connection Db() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres");
    }
}