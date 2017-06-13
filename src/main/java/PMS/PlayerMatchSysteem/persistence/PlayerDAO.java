package PMS.PlayerMatchSysteem.persistence;
// Created on 1-6-2017.

import PMS.PlayerMatchSysteem.model.Player;

import java.sql.*;
import java.util.ArrayList;

public class PlayerDAO extends BaseDAO {

    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> allPlayers = new ArrayList<Player>();
        try {
            Connection con = super.getConnection();
            String query = "SELECT * FROM PLAYER";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet RS = pstmt.executeQuery();
            while (RS.next()) {
                int Player_id = RS.getInt("Player_id");
                String Name = RS.getString("Name");
                Player p = new Player(Player_id, Name);
                allPlayers.add(p);
            }
            RS.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPlayers;
    }

    public boolean createPlayer(String Name) {
        boolean success = false;
        try {
            Connection con = super.getConnection();
            String query = "INSERT INTO PLAYER VALUES(NULL, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, Name);
            pstmt.executeUpdate();
            System.out.println("player with name: " + Name + " created!");
            success = true;
            pstmt.close();
            String query2 = "SELECT * FROM player ORDER BY Player_id DESC LIMIT 1";
            PreparedStatement pstmt2 = con.prepareStatement(query2);
            ResultSet rs = pstmt2.executeQuery();
            rs.next();
            int pid = rs.getInt("Player_id");
            rs.close();
            pstmt.close();
            con.close();
            createGamesforPlayer(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean deletePlayer(int playerid) {
        boolean success = false;
        try {
//            this.deleteGamesforPlayer(playerid);
            Connection con = super.getConnection();
            String query = "DELETE FROM PLAYER WHERE player_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, playerid);
            pstmt.executeUpdate();
            System.out.println("player with id: " + playerid + " deleted!");
            success = true;
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

//    public void deleteGamesforPlayer(int playerid) {
//        try {
//            Connection con = super.getConnection();
//            String query = "DELETE FROM game WHERE player1 = ? or player2 = ?";
//            PreparedStatement pstmt = con.prepareStatement(query);
//            pstmt.setInt(1, playerid);
//            pstmt.setInt(2, playerid);
//            pstmt.executeUpdate();
//            System.out.println("games for player with " + playerid + " deleted!");
//            pstmt.close();
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void createGamesforPlayer(int playerid) {
        GameDAO gd = new GameDAO();
        TournamentDAO td = new TournamentDAO();
        ArrayList<Player> players = this.getAllPlayers();
        int tid = td.getNewestTournament();
        for (int i = 0; i <= players.size() - 1; i++) {
            int p1 = playerid;
            int p2 = players.get(i).getID();
            if(p1 != p2){
                gd.createGame(p1, p2, tid);
            }
        }
    }
}
