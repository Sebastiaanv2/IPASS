package PMS.PlayerMatchSysteem.persistence;

import PMS.PlayerMatchSysteem.model.Game;
import PMS.PlayerMatchSysteem.model.Player;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TournamentDAO extends BaseDAO{

    private ArrayList<Game> games = new ArrayList<Game>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private AtomicInteger seq = new AtomicInteger();

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
        boolean succces = false;
        if (checkIfInSpelers(nieuwePlayer)) {
            players.add(nieuwePlayer);
            succces = true;
        }
    }

    public void createAllMatches() {
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

    public int calculateMatchAmount(int PlayerAmount) {
        int amountOfMatches = PlayerAmount * (PlayerAmount - 1) / 2;
        return amountOfMatches;
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

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

}
