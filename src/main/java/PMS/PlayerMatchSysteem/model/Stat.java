package PMS.PlayerMatchSysteem.model;
// Created on 1-6-2017.

public class Stat {
    private int stat_id;
    private double player1_Score;
    private double player2_Score;
    private int game_id;
    private double player1_Punten;
    private double player2_Punten;
    private int tournament_id;

    private int rank;
    private String name;
    private double punten;
    private double doelsaldo;
    private double tegenpunten;
    private int games;

    public Stat(int rank, String name, double punten, double doelsaldo, double tegenpunten, int games) {
        this.rank = rank;
        this.name = name;
        this.punten = punten;
        this.doelsaldo = doelsaldo;
        this.tegenpunten = tegenpunten;
        this.games = games;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPunten() {
        return punten;
    }

    public double getDoelsaldo() {
        return doelsaldo;
    }

    public double getTegenpunten() {
        return tegenpunten;
    }

    public int getGames() {
        return games;
    }
}
