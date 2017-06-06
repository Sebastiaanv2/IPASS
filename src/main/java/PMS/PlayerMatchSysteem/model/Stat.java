package PMS.PlayerMatchSysteem.model;
// Created on 1-6-2017.

public class Stat {
    private int Stat_id;
    private double player1_Score;
    private double player2_Score;
    private int Match_id;

    public Stat(int stat_id, double player1_Score, double player2_Score, int match_id) {
        Stat_id = stat_id;
        this.player1_Score = player1_Score;
        this.player2_Score = player2_Score;
        Match_id = match_id;
    }

    public Stat(){}

    public int getStat_id() {
        return Stat_id;
    }

    public void setStat_id(int stat_id) {
        Stat_id = stat_id;
    }

    public double getPlayer1_Score() {
        return player1_Score;
    }

    public void setPlayer1_Score(double player1_Score) {
        this.player1_Score = player1_Score;
    }

    public double getPlayer2_Score() {
        return player2_Score;
    }

    public void setPlayer2_Score(double player2_Score) {
        this.player2_Score = player2_Score;
    }

    public int getMatch_id() {
        return Match_id;
    }

    public void setMatch_id(int match_id) {
        Match_id = match_id;
    }
}
