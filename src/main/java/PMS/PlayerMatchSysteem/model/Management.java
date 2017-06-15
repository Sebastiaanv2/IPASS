package PMS.PlayerMatchSysteem.model;
// Created on 1-6-2017.

public class Management {
    private double Rule1;
    private double Rule2;
    private double Rule3;
    private double Rule4;
    private double Rule5;
    private String Masterpassword;

    public Management(double rule1, double rule2, double rule3, double rule4, double rule5) {
        Rule1 = rule1;
        Rule2 = rule2;
        Rule3 = rule3;
        Rule4 = rule4;
        Rule5 = rule5;
    }

    public Management(){}

    public double getRule1() {
        return Rule1;
    }

    public double getRule2() {
        return Rule2;
    }

    public double getRule3() {
        return Rule3;
    }

    public double getRule4() {
        return Rule4;
    }

    public double getRule5() {
        return Rule5;
    }
}
