package PMS.PlayerMatchSysteem.model;

public class Game {
    private int Game_id;
    private int player1;
    private int player2;

    public Game(int Game_id, int player1, int player2) {
        this.Game_id = Game_id;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Game() {
    }

    public int getGame_id() {
        return Game_id;
    }

    public void setGame_id(int game_id) {
        Game_id = game_id;
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

    @Override
    public String toString() {
        String s = "Game_id= " + Game_id;
        int p1;
        int p2;
        if (player1 == 0){
            p1 = -0;
        } else {
            p1 = player1;
        }
        if (player2 == 0){
            p2 = -0;
        } else {
            p2 = player2;
        }

        s += "\n\t"+ p1+
                "\n\t"+p2;
        return s;
    }
}
