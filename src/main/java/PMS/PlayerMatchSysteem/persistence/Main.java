package PMS.PlayerMatchSysteem.persistence;

import PMS.PlayerMatchSysteem.model.Game;
import PMS.PlayerMatchSysteem.model.Player;

public class Main {
    public static void main(String[] args) {
        TournamentDAO t = new TournamentDAO();

        //create some new players
        Player s1 = new Player(0, "henk");
        Player s2 = new Player(1, "freek");
        Player s3 = new Player(2, "jan");
        Player s4 = new Player(3, "michael");
        Player s5 = new Player(25, "sebastiaan");

        //add the players to the spelers ArrayList
        t.addPlayer(s1);
        t.addPlayer(s2);
        t.addPlayer(s3);
        t.addPlayer(s4);
        t.addPlayer(s5);

        //create new match object manually and add it to the matches arraylist
        Game m = new Game(15,s3,s5,2);
        System.out.println(m.toString());
        t.getGames().add(m);

        //start creating matches
        t.createAllMatches();

    }
}
