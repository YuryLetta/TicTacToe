/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mahmoud
 */
public class DB {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public DB() {
        try {
            DriverManager.registerDriver(new Driver());
            con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/xogame", "root", "mnwraea");
        } catch (SQLException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    public boolean checkForLogin(String username, String password) throws SQLException {
        pst = con.prepareStatement("select * from player where username= ? and password=?");
        pst.setString(1, username);
        pst.setString(2, password);
        rs = pst.executeQuery();
        //rs.next();
        if (rs.next() == false) {
            return false;
        } else {

            return true;
        }
    }

    public boolean checkForValidation(String fullname, String age, String username, String password) throws SQLException {
        
        int val;
        boolean valid=true;
        System.out.println(fullname+age+username+password);
        if (Integer.parseInt(age) < 100 && Integer.parseInt(age) > 0 && password.length() > 6 ) {
            pst = con.prepareStatement("insert into player values(?,?,?,?)");
            pst.setString(1, username);
            pst.setString(2, fullname);
            pst.setString(3, age);
            pst.setString(4, password);
            val=pst.executeUpdate();
            if(val!=0){
                valid=true;
            }
            else{
                valid=false;
            }
        }
        else{
            valid=false;
        }
        return valid;
    }
}
