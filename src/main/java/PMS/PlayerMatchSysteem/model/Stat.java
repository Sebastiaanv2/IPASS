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

    public Stat(int stat_id, double player1_Score, double player2_Score, int game_id, double player1_Punten, double player2_Punten, int tournament_id) {
        this.stat_id = stat_id;
        this.player1_Score = player1_Score;
        this.player2_Score = player2_Score;
        this.game_id = game_id;
        this.player1_Punten = player1_Punten;
        this.player2_Punten = player2_Punten;
        this.tournament_id = tournament_id;
    }

    public Stat(int rank, String name, double punten, double doelsaldo, double tegenpunten, int games) {
        this.rank = rank;
        this.name = name;
        this.punten = punten;
        this.doelsaldo = doelsaldo;
        this.tegenpunten = tegenpunten;
        this.games = games;
    }

    public int getStat_id() {
        return stat_id;
    }

    public void setStat_id(int stat_id) {
        this.stat_id = stat_id;
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

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public double getPlayer1_Punten() {
        return player1_Punten;
    }

    public void setPlayer1_Punten(double player1_Punten) {
        this.player1_Punten = player1_Punten;
    }

    public double getPlayer2_Punten() {
        return player2_Punten;
    }

    public void setPlayer2_Punten(double player2_Punten) {
        this.player2_Punten = player2_Punten;
    }

    public int getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        this.tournament_id = tournament_id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public void setPunten(double punten) {
        this.punten = punten;
    }

    public double getDoelsaldo() {
        return doelsaldo;
    }

    public void setDoelsaldo(double doelsaldo) {
        this.doelsaldo = doelsaldo;
    }

    public double getTegenpunten() {
        return tegenpunten;
    }

    public void setTegenpunten(double tegenpunten) {
        this.tegenpunten = tegenpunten;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }
}
