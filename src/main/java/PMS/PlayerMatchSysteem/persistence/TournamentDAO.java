package PMS.PlayerMatchSysteem.persistence;

import PMS.PlayerMatchSysteem.model.Game;
import PMS.PlayerMatchSysteem.model.Player;
import PMS.PlayerMatchSysteem.model.Tournament;

import java.sql.*;
import java.util.ArrayList;

public class TournamentDAO extends BaseDAO {

    public ArrayList<Tournament> getAllTournaments() {
        ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
        try {
            String query = "Select * from tournament";
            Statement stmt = DatabaseConn.myConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                Date sd = rs.getDate(2);
                Date ed = rs.getDate(3);
                Tournament t = new Tournament(id, sd, ed);
                tournaments.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("an error occured");
        }
        return tournaments;
    }

    public void createTournament() {
        Tournament tournament = new Tournament();

        try {
            String query = "INSERT INTO tournament VALUES(null,CURDATE(),null)";
            Statement stmt = null;
            stmt = DatabaseConn.myConn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTournament(int tid){
        try {
            String query = "DELETE FROM Tournament WHERE tournament_id = ?";
            PreparedStatement pstmt = DatabaseConn.myConn.prepareStatement(query);
            pstmt.setInt(1,tid);
            pstmt.executeUpdate();
            System.out.println("tournament with id: "+tid+" deleted!");
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void printAllTournaments() throws SQLException {
        for (Tournament tour : this.getAllTournaments()) {
            System.out.println(tour.toString());
        }
    }
}
