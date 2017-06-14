package PMS.PlayerMatchSysteem.persistence;
// Created on 1-6-2017.

import PMS.PlayerMatchSysteem.model.Game;
import PMS.PlayerMatchSysteem.model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameDAO extends BaseDAO {

    public ArrayList<Game> getAllGames() {
        ArrayList<Game> games = new ArrayList<Game>();
        try {
            Connection con = super.getConnection();
            String query = "Select Tournament_id, Game_id, Player1, Player2 from game WHERE is_played = 0";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int tid = rs.getInt(1);
                int id = rs.getInt(2);
                int p1 = rs.getInt(3);
                int p2 = rs.getInt(4);
                Game g = new Game(tid, id, p1, p2);
                games.add(g);
            }
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("an error occured");
        }
        return games;
    }

    public ArrayList<Game> getAllGamesWithNames() {
        ArrayList<Game> games = new ArrayList<Game>();
        try {
            Connection con = super.getConnection();
            String query = "SELECT g.Tournament_id, g.Game_id, p1.name, p2.name from game AS g" +
                            " JOIN player AS p1 ON g.Player1 = p1.Player_id" +
                            " JOIN player AS p2 ON g.Player2 = p2.Player_id" +
                            " WHERE is_played = 0";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int tid = rs.getInt(1);
                int id = rs.getInt(2);
                String p1name = rs.getString(3);
                String p2name = rs.getString(4);
                Game g = new Game(tid, id, p1name, p2name);
                games.add(g);
            }
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("an error occured");
        }
        return games;
    }

    public boolean createAllGames() {
        PlayerDAO pd = new PlayerDAO();
        TournamentDAO td = new TournamentDAO();
        ArrayList<Player> players = pd.getAllPlayers();
        boolean success = false;
        int tid = td.createTournament();
        for (int i = 0; i <= players.size() - 1; i++) {
            for (int j = i + 1; j <= players.size() - 1; j++) {
                int p1 = players.get(i).getID();
                int p2 = players.get(j).getID();
                success = this.createGame(p1, p2, tid);
            }
        }
        return success;
    }

    public boolean createGame(int p1, int p2, int tid) {
        boolean success = false;
        try {
            Connection con = super.getConnection();
            String query = "INSERT INTO game VALUES(?, DEFAULT ,?,?, 0)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, tid);
            pstmt.setInt(2, p1);
            pstmt.setInt(3, p2);
            System.out.println("p1: "+p1+" p2: "+p2+" tid: "+tid);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            System.out.println("new match created");
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean deleteGame(int gid){
        boolean success = false;
        try {
            Connection con = super.getConnection();
            String query = "DELETE FROM game WHERE Game_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1,gid);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            System.out.println("game with id "+gid+" removed!");
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public void shuffleGames(){
        ArrayList<Game> games = this.getAllGames();
        for (Game game : games){
            deleteGame(game.getGame_id());
        }
        Collections.shuffle(games);
        for (Game game : games){
            createGame(game.getPlayer1(),game.getPlayer2(),game.getTournament_id());
        }
    }

}
