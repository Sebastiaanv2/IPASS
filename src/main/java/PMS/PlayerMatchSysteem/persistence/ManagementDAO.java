package PMS.PlayerMatchSysteem.persistence;
// Created on 1-6-2017.

import PMS.PlayerMatchSysteem.model.Management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagementDAO extends BaseDAO {
    public Management getAllRules() {
        Management rules = new Management();
        try {
            Connection con = super.getConnection();
            String query = "SELECT Rule1, Rule2, Rule3, Rule4, Rule5 FROM management";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                double r1 = rs.getDouble(1);
                double r2 = rs.getDouble(2);
                double r3 = rs.getDouble(3);
                double r4 = rs.getDouble(4);
                double r5 = rs.getDouble(5);
                rules = new Management(r1,r2,r3,r4,r5);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("an error occured");
        }
        return rules;
    }

    public boolean updateRules(double r1,double r2,double r3,double r4,double r5){
        boolean success = false;
        try {
            Connection con = super.getConnection();
            String query = "UPDATE management set rule1 = ?, rule2 = ?, rule3 = ?, rule4 = ?, rule5 = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setDouble(1,r1);
            pstmt.setDouble(2,r2);
            pstmt.setDouble(3,r3);
            pstmt.setDouble(4,r4);
            pstmt.setDouble(5,r5);
            pstmt.executeUpdate();
            success = true;
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public String getMPass(){
        String mpass = "";
        try {
            Connection con = super.getConnection();
            String query = "SELECT masterpassword FROM management";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            mpass = rs.getString(1);
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mpass;
    }

    public boolean changeMPass(String pass, String pass1){
        boolean success = false;
        String mPass = this.getMPass();
        if (mPass.equals(pass)){
            try {
                Connection con = super.getConnection();
                String query = "UPDATE management set masterpassword = ? ";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, pass1);
                pstmt.executeUpdate();
                pstmt.close();
                con.close();
                success = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }


}
