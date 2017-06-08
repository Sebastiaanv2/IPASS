package PMS.PlayerMatchSysteem.persistence;
// Created on 1-6-2017.

import java.sql.SQLException;

import static PMS.PlayerMatchSysteem.persistence.DatabaseConn.close;

public class DAOtest {
    public static void main(String[] args) throws SQLException {
        new DatabaseConn("MYSQL").open();
        PlayerDAO pd = new PlayerDAO();
        TournamentDAO td = new TournamentDAO();
        GameDAO gd = new GameDAO();

        //playerDAO test

        pd.printAllPlayers();
        System.out.println();

//        //TournamentDAO test
//        td.createTournament();
//        td.printAllTournaments();
//
//        //create matches
//        gd.createAllGames(pd.getAllPlayers(),1);

        close();
    }
}
