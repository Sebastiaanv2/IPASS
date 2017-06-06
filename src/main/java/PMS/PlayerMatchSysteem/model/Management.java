package PMS.PlayerMatchSysteem.model;
// Created on 1-6-2017.

public class Management {
    private double Rule1;
    private double Rule2;
    private double Rule3;
    private double Rule4;
    private double Rule5;
    private String Masterpassword;

    public Management(double rule1, double rule2, double rule3, double rule4, double rule5, String masterpassword) {
        Rule1 = rule1;
        Rule2 = rule2;
        Rule3 = rule3;
        Rule4 = rule4;
        Rule5 = rule5;
        Masterpassword = masterpassword;
    }

    public Management(){}

    public double getRule1() {
        return Rule1;
    }

    public void setRule1(double rule1) {
        Rule1 = rule1;
    }

    public double getRule2() {
        return Rule2;
    }

    public void setRule2(double rule2) {
        Rule2 = rule2;
    }

    public double getRule3() {
        return Rule3;
    }

    public void setRule3(double rule3) {
        Rule3 = rule3;
    }

    public double getRule4() {
        return Rule4;
    }

    public void setRule4(double rule4) {
        Rule4 = rule4;
    }

    public double getRule5() {
        return Rule5;
    }

    public void setRule5(double rule5) {
        Rule5 = rule5;
    }

    public String getMasterpassword() {
        return Masterpassword;
    }

    public void setMasterpassword(String masterpassword) {
        Masterpassword = masterpassword;
    }
}
