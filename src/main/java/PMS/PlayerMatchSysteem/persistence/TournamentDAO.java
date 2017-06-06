package PMS.PlayerMatchSysteem.persistence;

import PMS.PlayerMatchSysteem.model.Game;
import PMS.PlayerMatchSysteem.model.Player;
import PMS.PlayerMatchSysteem.model.Tournament;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TournamentDAO extends BaseDAO{

    private ArrayList<Game> games = new ArrayList<Game>();

    public ArrayList<Tournament> getAllTournaments(){
        ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
        try {
            String query = "Select * from tournament";
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt(0);
                Date sd = rs.getDate(1);
                Date ed = rs.getDate(2);
                Tournament t = new Tournament(id,sd,ed);
                tournaments.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("an error occured");
        }
        return tournaments;
    }

    public void createAllGames(ArrayList<Player> players) {
        for (int i = 0; i <= players.size() - 1; i++) {
            for (int j = i + 1; j <= players.size() - 1; j++) {
                this.createGame(players.get(i), players.get(j));
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

    public void createGame(Player p1, Player p2) {
        //new match object
        int matchId = games.size();
        Player player1 = p1;
        Player player2 = p2;

        Game m = new Game(matchId, p1, p2,4);
        games.add(m);
        System.out.println(m);
    }
}
