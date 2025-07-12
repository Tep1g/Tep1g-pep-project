package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public boolean createAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username=?";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getUsername());
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                sql = "INSERT INTO account (username, password) VALUES (?, ?)";
                ps = connection.prepareStatement(sql);
                ps.setString(1, account.getUsername());
                ps.setString(2, account.getPassword());
                if (ps.executeUpdate() == 1) {
                    rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        account.setAccount_id(
                            rs.getInt("account_id")
                        );
                    }
                    return true;
                }
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
