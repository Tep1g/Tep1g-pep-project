package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    public Account getAccount(String username, String password) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username=? and password=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password")
                );
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account createAccount(String username, String password) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                sql = "INSERT INTO account (username, password) VALUES (?, ?)";
                ps = connection.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                if (ps.executeUpdate() == 1) {
                    return getAccount(username, password);
                }
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
