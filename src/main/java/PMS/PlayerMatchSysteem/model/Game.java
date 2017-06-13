package PMS.PlayerMatchSysteem.model;

public class Game {
    private int tournament_id;
    private int game_id;
    private int player1;
    private int player2;

    private String player1name;

    private String player2name;
    public Game(int tournament_id, int game_id, int player1, int player2) {
        this.tournament_id = tournament_id;
        this.game_id = game_id;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Game(int tournament_id, int game_id, String player1name, String player2name) {
        this.tournament_id = tournament_id;
        this.game_id = game_id;
        this.player1name = player1name;
        this.player2name = player2name;
    }

    public Game() {
    }

    public int getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        this.tournament_id = tournament_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    public String getPlayer1name() {
        return player1name;
    }

    public void setPlayer1name(String player1name) {
        this.player1name = player1name;
    }

    public String getPlayer2name() {
        return player2name;
    }

    public void setPlayer2name(String player2name) {
        this.player2name = player2name;
    }

    @Override
    public String toString() {
        String s = "Game_id= " + game_id +
                "\n\tp1: "+player1+
                "\n\tp2: "+player2 +
                "\n\ttid: "+tournament_id;
        return s;
    }
}
