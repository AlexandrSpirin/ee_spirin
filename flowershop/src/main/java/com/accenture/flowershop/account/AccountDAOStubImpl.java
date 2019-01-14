package com.accenture.flowershop.account;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("AccountDAOStubImpl")
public class AccountDAOStubImpl implements AccountDAO {

    List<Account> accounts = new ArrayList();

    int count;

    private AccountDAOStubImpl()throws AccountDAOException
    {
        try {
            insertAccount("admin", "123456", "Aleksandr", "Spirin", "ddd@gmail.com");
            insertAccount("eaglesss", "qwerty", "Nikita", "Orlov", "agamon@mail.ru");
            insertAccount("oops", "asd123", "Vasya", "Popov", "boooo@rambler.ru");
        }
        catch (Exception e){
            throw new AccountDAOException(AccountDAOException.ERROR_INSERT_ACCOUNT, new Throwable(e));
        }
    }

    public List<Account> findAccount(String firstName, String lastName)
            throws AccountDAOException {
        try {
            List<Account> findedAccounts = new ArrayList();
            for (Account acc: accounts) {
                if(acc.getFirstName().equals(firstName)||acc.getLastName().equals(lastName)) {
                    findedAccounts.add(new AccountImpl(acc.getID(), acc.getLogin(), acc.getPassword(), acc.getFirstName(), acc.getLastName(), acc.getEmail()));
                }
            }
            return findedAccounts;
        }
        catch (Exception e){
            throw new AccountDAOException(AccountDAOException.ERROR_FIND_NAME, new Throwable(e));
        }
    }

    public Account findAccount(int id) throws AccountDAOException {
        try {

            for (Account acc: accounts) {
                if(acc.getID()==id)
                {
                    return new AccountImpl(acc.getID(), acc.getLogin(), acc.getPassword(), acc.getFirstName(), acc.getLastName(), acc.getEmail());
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
                    return new AccountImpl(acc.getID(), acc.getLogin(), acc.getPassword(), acc.getFirstName(), acc.getLastName(), acc.getEmail());
                }
            }
            return null;
        }
        catch (Exception e){
            throw new AccountDAOException(AccountDAOException.ERROR_FIND_LOGIN, new Throwable(e));
        }
    }

    public boolean insertAccount(String login, String password, String firstName, String lastName, String email)
            throws AccountDAOException {
        try {
            accounts.add(new AccountImpl(count, login, password, firstName, lastName, email));
            count++;
            return true;
        }
        catch (Exception e){
            throw new AccountDAOException(AccountDAOException.ERROR_INSERT_ACCOUNT, new Throwable(e));
        }
    }
}
