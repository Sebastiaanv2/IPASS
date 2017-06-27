package PMS.PlayerMatchSysteem.persistence;
// Created on 1-6-2017.

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
    protected final Connection getConnection() {

        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (con == null) {
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");

                con = ds.getConnection();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }
        return con;
    }

}

