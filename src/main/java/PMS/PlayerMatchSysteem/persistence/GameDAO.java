package PMS.PlayerMatchSysteem.persistence;
// Created on 1-6-2017.

import PMS.PlayerMatchSysteem.model.Game;
import PMS.PlayerMatchSysteem.model.Player;
import PMS.PlayerMatchSysteem.model.Tournament;

import java.sql.*;
import java.util.ArrayList;

public class GameDAO extends BaseDAO {

    private ArrayList<Game> games = new ArrayList<Game>();

    private ArrayList<Player> players = new ArrayList<Player>();

    public boolean checkIfInSpelers(Player newPlayer) {
        boolean succes = false;
        if (newPlayer == null) {
            return succes;
        }
        if (players.size() != 0) {
            for (Player player : players) {
                if (!(player.getName().equals(newPlayer.getName()))
                        && !(player.getID() == newPlayer.getID())) {
                    succes = true;
                }
            }
        } else {
            succes = true;
        }
        return succes;
    }

    public void addPlayer(Player nieuwePlayer) {
        boolean success = false;
        if (checkIfInSpelers(nieuwePlayer)) {
            players.add(nieuwePlayer);
            success = true;
        }
        System.out.println(success);
    }

    public ArrayList<Game> getAllGames() {
        ArrayList<Game> games = new ArrayList<Game>();
        try {
            String query = "Select * from game";
            Statement stmt = DatabaseConn.myConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                int p1 = rs.getInt(3);
                int p2 = rs.getInt(4);
                Game g = new Game(id, p1, p2);
                games.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("an error occured");
        }
        return games;
    }

    public int calculateMatchAmount(int PlayerAmount) {
        int amountOfMatches = PlayerAmount * (PlayerAmount - 1) / 2;
        return amountOfMatches;
    }

    public void printAllGames() throws SQLException {
        for (Game game : this.getAllGames()) {
            System.out.println(game.toString());
        }
    }

    public void createAllGames(ArrayList<Player> players, int tid) {
        for (int i = 0; i <= players.size() - 1; i++) {
            for (int j = i + 1; j <= players.size() - 1; j++) {
                this.createGame(i, j, tid);
/*                for (Game g : games){
                    if (g.getPlayer1().getID() != players.get(i).getID() && g.getPlayer2().getID() != players.get(j).getID()) {
                        break;
                    } else if (g.getPlayer1().getID() != players.get(i).getID() && g.getPlayer2().getID() != players.get(j).getID()){
                        break;
                    } else {
                        this.createGame(players.get(i), players.get(j));
                    }
                }*/
            }
        }
    }

    public void createGame(int p1, int p2, int tid) {
        //new match object
        try {
            String query = "INSERT INTO game VALUES(NULL ,?,?,?)";
            PreparedStatement pstmt = DatabaseConn.myConn.prepareStatement(query);
            pstmt.setInt(1, tid);
            pstmt.setInt(2, p1);
            pstmt.setInt(3, p2);
            pstmt.executeUpdate();
            System.out.println("new match created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void clearGames(int Tournamentid){
        try {
            String query = "DELETE FROM game WHERE true;";
            PreparedStatement pstmt = DatabaseConn.myConn.prepareStatement(query);
            System.out.println("new match created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
