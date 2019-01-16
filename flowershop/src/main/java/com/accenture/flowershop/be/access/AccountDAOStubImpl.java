package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.account.Account;

import java.util.ArrayList;
import java.util.List;

//@Component
public class AccountDAOStubImpl implements AccountDAO {

    List<Account> accounts = new ArrayList();

    int count;

    private AccountDAOStubImpl()throws AccountDAOException
    {
        try {
            insertAccount("admin", "123456", "admin");
            insertAccount("eaglesss", "qwerty", "customer");
            insertAccount("oops", "asd123", "customer");
        }
        catch (Exception e){
            throw new AccountDAOException(AccountDAOException.ERROR_INSERT_ACCOUNT, new Throwable(e));
        }
    }

    public Account findAccount(long ID) throws AccountDAOException {
        try {

            for (Account acc: accounts) {
                if(acc.getID()==ID)
                {
                    return new Account(acc.getLogin(), acc.getPassword(), acc.getType().toString());
                }
            }
            return null;
        }
        catch (Exception e){
            throw new AccountDAOException(AccountDAOException.ERROR_FIND_ID, new Throwable(e));
        }
    }

    public Account findAccount(String login) throws AccountDAOException {
        try {
            for (Account acc: accounts) {
                if(acc.getLogin().equals(login))
                {
                    return new Account(acc.getLogin(), acc.getPassword(), acc.getType().toString());
                }
            }
            return null;
        }
        catch (Exception e){
            throw new AccountDAOException(AccountDAOException.ERROR_FIND_LOGIN, new Throwable(e));
        }
    }

    public boolean insertAccount(String login, String password, String type)
            throws AccountDAOException {
        try {
            accounts.add(new Account(login, password, type));
            count++;
            return true;
        }
        catch (Exception e){
            throw new AccountDAOException(AccountDAOException.ERROR_INSERT_ACCOUNT, new Throwable(e));
        }
    }
}
