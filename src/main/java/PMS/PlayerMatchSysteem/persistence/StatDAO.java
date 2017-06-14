package PMS.PlayerMatchSysteem.persistence;
// Created on 1-6-2017.

import PMS.PlayerMatchSysteem.model.Stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatDAO extends BaseDAO {

    public ArrayList<Stat> getAllStats() {
        ArrayList<Stat> stats = new ArrayList<Stat>();
        try {
            Connection con = super.getConnection();
            String query = "SELECT player, punten, doelsaldo, tegenpunten, games FROM( " +
                            "SELECT name as player, sum(punten) AS punten, sum(doelsaldo) AS doelsaldo, sum(tegenpunten) AS tegenpunten, COUNT(Game_id) as games FROM ( " +
                            "SELECT g.Game_id, p1.name, s.Player1_Punten as punten, s.Player1_Score as doelsaldo, s.Player2_Score as tegenpunten from game AS g " +
                            "JOIN player AS p1 ON g.Player1 = p1.Player_id " +
                            "JOIN player AS p2 ON g.Player2 = p2.Player_id " +
                            "JOIN stat AS s ON s.Game_id = g.Game_id " +
                            "UNION " +
                            "SELECT g.Game_id, p2.name, s.Player2_Punten as punten, s.Player2_Score as doelsaldo, s.Player1_Score as tegenpunten from game AS g " +
                            "JOIN player AS p1 ON g.Player1 = p1.Player_id " +
                            "JOIN player AS p2 ON g.Player2 = p2.Player_id " +
                            "JOIN stat AS s ON s.Game_id = g.Game_id " +
                            ") scorematch " +
                            "GROUP BY player " +
                            ") scorematch2 " +
                            "order by punten desc";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            int rank = 1;
            while(rs.next()){
                String name = rs.getString(1);
                double punten = rs.getDouble(2);
                double doelsaldo = rs.getDouble(3);
                double tegenpunten = rs.getDouble(4);
                int games = rs.getInt(5);

                Stat s = new Stat(rank,name,punten,doelsaldo,tegenpunten,games);
                stats.add(s);
                rank++;
            }
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("an error occured");
        }
        return stats;
    }

    public boolean setStat(double p1s, double p2s, int gid, double p1p, double p2p) {
        boolean success = false;
        TournamentDAO td = new TournamentDAO();
        int tid = td.getNewestTournament();
        try {
            Connection con = super.getConnection();
            String query = "INSERT INTO stat (Stat_id, Player1_Score, Player2_Score, Game_id, Player1_Punten, Player2_Punten, Tournament_id) VALUES (DEFAULT ,?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setDouble(1, p1s);
            pstmt.setDouble(2, p2s);
            pstmt.setInt(3, gid);
            pstmt.setDouble(4, p1p);
            pstmt.setDouble(5, p2p);
            pstmt.setInt(6, tid);
            pstmt.executeUpdate();
            pstmt.close();
            String query2 = "UPDATE game SET is_played = 1 WHERE game_id = ?";
            PreparedStatement pstmt2 = con.prepareStatement(query2);
            pstmt2.setInt(1, gid);
            pstmt2.executeUpdate();
            pstmt2.close();
            success = true;
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("an error occured");
        }
        return success;
    }

}
