package PMS.PlayerMatchSysteem.persistence;
// Created on 1-6-2017.

import PMS.PlayerMatchSysteem.model.Player;

import java.sql.SQLException;

public class DAOtest {
    public static void main(String[] args) throws SQLException {
        new DatabaseConn("MYSQL").open();
        PlayerDAO pd = new PlayerDAO();

        pd.printAllPlayers();

        pd.createPlayer("Johnny Depp");

        pd.printAllPlayers();

        pd.deletePlayer("Johnny Depp");

        pd.printAllPlayers();
    }
}
