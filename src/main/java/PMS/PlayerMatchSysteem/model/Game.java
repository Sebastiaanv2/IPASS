package PMS.PlayerMatchSysteem.model;

import java.util.HashMap;

public class Game {
    private int Game_id;
    private Player player1;
    private Player player2;
    private int Tournament_id;

    public Game(int Game_id, Player player1, Player player2, int tournament_id) {
        this.Game_id = Game_id;
        this.player1 = player1;
        this.player2 = player2;
        Tournament_id = tournament_id;
    }

    public Game() {
    }

    public int getTournament_id() {
        return Tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        Tournament_id = tournament_id;
    }

    public int getGame_id() {
        return Game_id;
    }

    public void setGame_id(int game_id) {
        Game_id = game_id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    @Override
    public String toString() {
        String s = "Game_id= " + Game_id;
        String p1;
        String p2;
        if (player1 == null){
            p1 = "null";
        } else {
            p1 = player1.toString();
        }
        if (player2 == null){
            p2 = "null";
        } else {
            p2 = player2.toString();
        }

        s += "\n\t"+ p1+
                "\n\t"+p2;
        return s;
    }
}
