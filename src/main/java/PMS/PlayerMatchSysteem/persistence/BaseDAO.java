package PMS.PlayerMatchSysteem.persistence;
// Created on 1-6-2017.

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class BaseDAO {
    protected final Connection getConnection() {
        Connection result = null;

        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/Postgres");

            result = ds.getConnection();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return result;
    }

}
