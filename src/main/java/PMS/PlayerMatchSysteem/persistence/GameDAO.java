package PMS.PlayerMatchSysteem.persistence;
// Created on 1-6-2017.

import PMS.PlayerMatchSysteem.model.Game;
import PMS.PlayerMatchSysteem.model.Player;
import PMS.PlayerMatchSysteem.model.Tournament;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GameDAO extends BaseDAO {

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

    public ArrayList<Game> getAllGames(){
        ArrayList<Game> games = new ArrayList<Game>();
        try {
            String query = "Select * from game";
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt(0);
                String p1 = rs.getString(1);
                String p2 = rs.getString(2);
                Game g = new Game(id,p1,p2);
                games.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("an error occured");
        }
        return games;
    }

    }

    public int calculateMatchAmount(int PlayerAmount) {
        int amountOfMatches = PlayerAmount * (PlayerAmount - 1) / 2;
        return amountOfMatches;
    }

}
