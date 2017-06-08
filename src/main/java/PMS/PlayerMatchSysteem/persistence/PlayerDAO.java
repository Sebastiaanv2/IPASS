package PMS.PlayerMatchSysteem.persistence;
// Created on 1-6-2017.

import PMS.PlayerMatchSysteem.model.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlayerDAO extends BaseDAO {

    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> allPlayers = new ArrayList<Player>();
        try {
            Statement stmt = DatabaseConn.myConn.createStatement();
            ResultSet RS = stmt.executeQuery("SELECT * FROM PLAYER");
            while (RS.next()) {
                int Player_id = RS.getInt("Player_id");
                String Name = RS.getString("Name");
                Player p = new Player(Player_id, Name);
                allPlayers.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPlayers;
    }

    public void printAllPlayers() {
        for (Player player : this.getAllPlayers()) {
            System.out.println(player.toString());
        }
    }

    public boolean createPlayer(String Name) {
        boolean success = false;
        try {
            String query = "INSERT INTO PLAYER VALUES(NULL, ?)";
            PreparedStatement pstmt = DatabaseConn.myConn.prepareStatement(query);
            pstmt.setString(1, Name);
            pstmt.executeUpdate();
            System.out.println("player with name: " + Name + " created!");
            pstmt.close();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean deletePlayer(String Name) {
        boolean success = false;
        try {
            String query = "DELETE FROM PLAYER WHERE Name = ?";
            PreparedStatement pstmt = DatabaseConn.myConn.prepareStatement(query);
            pstmt.setString(1, Name);
            pstmt.executeUpdate();
            System.out.println("player with name: " + Name + " deleted!");
            pstmt.close();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

}
