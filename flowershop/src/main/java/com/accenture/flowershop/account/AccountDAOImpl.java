package com.accenture.flowershop.account;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//@Component("AccountDAOImpl")
public class AccountDAOImpl implements AccountDAO {

    //private Connection conn;

    public AccountDAOImpl(Connection conn) {
        //this.conn = conn;
    }

    public List<Account> findAccount(String firstName, String lastName)
            throws AccountDAOException {
        /*try {

            PreparedStatement pStmt =
                    conn.prepareStatement("select * from com.accenture.flowershop.account where UPPER(FIRST_NAME) LIKE ? AND UPPER(LAST_NAME) LIKE ?");
            pStmt.setString(1, "%" + firstName.toUpperCase() + "%");
            pStmt.setString(2, "%" + lastName.toUpperCase() + "%");
            ResultSet rs = pStmt.executeQuery();
            List<Account> findedAccounts = new ArrayList();
            while(rs.next())
            {
                findedAccounts.add(new AccountImpl(rs.getInt("ID"), rs.getString("LOGIN"),
                        rs.getString("PASSWORD"), rs.getString("FIRST_NAME"),
                        rs.getString("LAST_NAME"), rs.getString("E_MAIL")));
            }
            conn.commit();
            rs.close();
            return findedAccounts;
        }
        catch (Exception e){
            throw new AccountDAOException(AccountDAOException.ERROR_FIND_NAME, new Throwable(e));
        }*/
        return null;
    }

    public Account findAccount(int id) throws AccountDAOException {
        /*try {

            PreparedStatement pStmt =
                    conn.prepareStatement("select * from com.accenture.flowershop.account where ID = ?");
            pStmt.setInt(1, id);
            ResultSet rs = pStmt.executeQuery();
            Account account = null;
            while(rs.next())
            {
                account = new AccountImpl(rs.getInt("ID"), rs.getString("LOGIN"),
                        rs.getString("PASSWORD"), rs.getString("FIRST_NAME"),
                        rs.getString("LAST_NAME"), rs.getString("E_MAIL"));
            }
            conn.commit();
            rs.close();
            return account;
        }
        catch (Exception e){
            throw new AccountDAOException(AccountDAOException.ERROR_FIND_ID, new Throwable(e));
        }
        */
        return null;
    }

    public Account findAccount(String login) throws AccountDAOException {
        /*try {

            PreparedStatement pStmt =
                    conn.prepareStatement("select * from com.accenture.flowershop.account where LOGIN = ?");
            pStmt.setString(1, login);
            ResultSet rs = pStmt.executeQuery();
            Account account = null;
            while(rs.next())
            {
                account = new AccountImpl(rs.getInt("ID"), rs.getString("LOGIN"),
                        rs.getString("PASSWORD"), rs.getString("FIRST_NAME"),
                        rs.getString("LAST_NAME"), rs.getString("E_MAIL"));
            }
            conn.commit();
            rs.close();
            return account;
        }
        catch (Exception e){
            throw new AccountDAOException(AccountDAOException.ERROR_FIND_LOGIN, new Throwable(e));
        }
        */
        return null;
    }

    public boolean insertAccount(String login, String password, String firstName, String lastName, String email)
            throws AccountDAOException {
        /*try {
            PreparedStatement pStmt = conn.prepareStatement
                    ("insert into com.accenture.flowershop.account (ID, LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, E_MAIL) VALUES " +
                            "(ACCOUNT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)");
            pStmt.setString(1,login);
            pStmt.setString(2,password);
            pStmt.setString(3,firstName);
            pStmt.setString(4,lastName);
            pStmt.setString(5,email);
            pStmt.executeUpdate();
            conn.commit();
            return true;
        }
        catch (Exception e){
            throw new AccountDAOException(AccountDAOException.ERROR_INSERT_ACCOUNT, new Throwable(e));
        }
        */
        return false;
    }

}
