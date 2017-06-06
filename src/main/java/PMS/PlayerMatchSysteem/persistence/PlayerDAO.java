package PMS.PlayerMatchSysteem.persistence;
// Created on 1-6-2017.

import PMS.PlayerMatchSysteem.model.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static PMS.PlayerMatchSysteem.persistence.DatabaseConn.close;

public class PlayerDAO {

    public ArrayList<Player> getAllPlayers() throws SQLException {
        ArrayList<Player> allPlayers = new ArrayList<Player>();
        Statement stmt = DatabaseConn.myConn.createStatement();
        ResultSet RS = stmt.executeQuery("SELECT * FROM PLAYER");
        while (RS.next()) {
            int Player_id = RS.getInt("Player_id");
            String Name = RS.getString("Name");
            Player p = new Player(Player_id, Name);
            allPlayers.add(p);
        }
        return allPlayers;
    }

    public void printAllPlayers() throws SQLException {
        for (Player player : getAllPlayers()){
            System.out.println(player.toString());
        }
    }



    public void createPlayer(String Name) throws SQLException {
        String query = "INSERT INTO PLAYER VALUES(NULL, ?)";
        PreparedStatement pstmt = DatabaseConn.myConn.prepareStatement(query);
        pstmt.setString(1,Name);
        pstmt.executeUpdate();
        System.out.println("player with name: "+Name+" created!");
        pstmt.close();
    }

    public void deletePlayer(String Name) throws SQLException {
        String query = "DELETE FROM PLAYER WHERE Name = ?";
        PreparedStatement pstmt = DatabaseConn.myConn.prepareStatement(query);
        pstmt.setString(1,Name);
        pstmt.executeUpdate();
        System.out.println("player with name: "+Name+" deleted!");
        pstmt.close();
    }
}
