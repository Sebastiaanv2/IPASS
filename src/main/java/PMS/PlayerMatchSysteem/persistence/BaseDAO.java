package PMS.PlayerMatchSysteem.persistence;
// Created on 1-6-2017.

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
    public static Connection getConnection() {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}

