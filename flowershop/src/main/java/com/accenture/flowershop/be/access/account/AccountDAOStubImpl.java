package com.accenture.flowershop.be.access.account;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.account.Account;
import com.accenture.flowershop.be.entity.account.AccountType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAOStubImpl implements AccountDAO {

    List<Account> accounts = new ArrayList();

    int count;

    private AccountDAOStubImpl()throws InternalException {
        try {
            insertAccount("admin", "123456", AccountType.ADMIN);
            insertAccount("eaglesss", "qwerty", AccountType.CUSTOMER);
            insertAccount("oops", "asd123", AccountType.CUSTOMER);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ACCOUNT_INSERT, new Throwable(e));
        }
    }

    @Override
    public List<Account> getAllAccounts() throws InternalException {
        try {
            List<Account> allAccounts= new ArrayList();
            for (Account acc: accounts) {
                allAccounts.add(new Account(acc.getLogin(), acc.getPassword(), acc.getType()));
            }
            return allAccounts;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ACCOUNT_FIND_ID, new Throwable(e));
        }
    }

    @Override
    public Account findAccount(Long id) throws InternalException {
        try {
            for (Account acc: accounts) {
                if(acc.getId()==id) {
                    return new Account(acc.getLogin(), acc.getPassword(), acc.getType());
                }
            }
            return null;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ACCOUNT_FIND_ID, new Throwable(e));
        }
    }

    @Override
    public Account findAccount(String login) throws InternalException {
        try {
            for (Account acc: accounts) {
                if(acc.getLogin().equals(login)) {
                    return new Account(acc.getLogin(), acc.getPassword(), acc.getType());
                }
            }
            return null;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ACCOUNT_FIND_LOGIN, new Throwable(e));
        }
    }

    @Override
    public boolean insertAccount(String login, String password, AccountType type)
            throws InternalException {
        try {
            accounts.add(new Account(login, password, type));
            count++;
            return true;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_ACCOUNT_INSERT, new Throwable(e));
        }
    }
}
