package PMS.PlayerMatchSysteem.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConn {
    public Connection myConn;

    private String databaseDriver = "oracle:thin:@";
    private String databaseUrl = "localhost";
    private int databasePort = 1521;
    private String databaseName = "xe";
    private String databaseUser = "sebastiaan";
    private String databasePass = "Welkom01";

    public DatabaseConn(String db) {
        if (db.toUpperCase().contains("MYSQL")) {
            databaseDriver = "mysql:";
            databasePort = 3306;
            databaseName = "ipassdb";
            databaseUser = "root";
            databasePass = "Welkom01";
            return;
        }

        if (System.getProperty("os.name").equals("Windows 10")) {
            this.databaseUrl = "127.0.0.1";
        }
    }

    public void open() throws SQLException {
        myConn = DriverManager.getConnection("jdbc:" +
                        databaseDriver + "//" +
                        databaseUrl + ":" +
                        databasePort + "/" +
                        databaseName + "",
                        databaseUser,
                        databasePass);

        System.out.println("myConn:\t" + myConn);

    }

    public void close() throws SQLException {
        myConn.close();

    }
}

