package PMS.PlayerMatchSysteem.persistence;

import PMS.PlayerMatchSysteem.model.Game;
import PMS.PlayerMatchSysteem.model.Player;
import PMS.PlayerMatchSysteem.model.Tournament;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TournamentDAO extends BaseDAO {

    public Tournament generateTournament() {
        Tournament tournament = new Tournament();

        try {
            String query = "INSERT INTO tournament VALUES(null,CURDATE(),null)";
            Statement stmt = null;
            stmt = DatabaseConn.myConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return tournament;
    }

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

    public void printAllTournaments() throws SQLException {
        for (Tournament tour : this.getAllTournaments()) {
            System.out.println(tour.toString());
        }
    }
}
