package PMS.PlayerMatchSysteem.persistence;

import PMS.PlayerMatchSysteem.model.Tournament;

import java.sql.*;
import java.util.ArrayList;

public class TournamentDAO extends BaseDAO {

    public ArrayList<Tournament> getAllTournaments() {
        ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
        try {
            Connection con = super.getConnection();
            String query = "Select * from tournament";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.wasNull()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    Date sd = rs.getDate(2);
                    Date ed = rs.getDate(3);
                    Tournament t = new Tournament(id, sd, ed);
                    tournaments.add(t);
                }
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("an error occured");
        }
        return tournaments;
    }

    public int createTournament() {
        Tournament tournament = new Tournament();
        int tid = 0;
        try {
            Connection con = super.getConnection();
            String query = "INSERT INTO tournament VALUES(null,CURDATE(),null)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();
            pstmt.close();
            String query2 = "SELECT * FROM tournament ORDER BY Tournament_id DESC LIMIT 1";
            PreparedStatement pstmt2 = con.prepareStatement(query2);
            ResultSet rs = pstmt2.executeQuery();
            rs.next();
            tid = rs.getInt("Tournament_id");
            rs.close();
            pstmt2.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tid;
    }

    public int getNewestTournament() {
        int tid = 0;
        try {
            Connection con = super.getConnection();
            String query = "SELECT * FROM tournament ORDER BY Tournament_id DESC LIMIT 1";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.wasNull()) {
                rs.next();
                tid = rs.getInt("Tournament_id");
            }
            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tid;
    }

    public boolean deleteTournament(int tid) {
        boolean success = false;
        try {
            Connection con = super.getConnection();
            String query = "DELETE FROM Tournament WHERE tournament_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, tid);
            pstmt.executeUpdate();
            System.out.println("tournament with id: " + tid + " deleted!");
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

//    public boolean deleteGamesForTournament(int tid) {
//        boolean success = false;
//        try {
//            Connection con = super.getConnection();
//            String query = "DELETE FROM game WHERE tournament_id = ?";
//            PreparedStatement pstmt = con.prepareStatement(query);
//            pstmt.setInt(1, tid);
//            pstmt.executeUpdate();
//            pstmt.close();
//            con.close();
//            System.out.println("games for tournament with id " + tid + " removed!");
//            success = true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return success;
//    }

}
