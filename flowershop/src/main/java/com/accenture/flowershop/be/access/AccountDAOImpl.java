package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.account.Account;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class AccountDAOImpl implements AccountDAO {

    @PersistenceContext
    private EntityManager em;

    public AccountDAOImpl() { }


    public Account findAccount(long ID) throws AccountDAOException {
        try {
            return em.find(Account.class, ID);
        }
        catch (Exception e){
            throw new AccountDAOException(AccountDAOException.ERROR_FIND_ID, new Throwable(e));
        }
    }

    public Account findAccount(String login) throws AccountDAOException {
        try {
            TypedQuery<Account> q = em.createQuery(" Select a from Account a where a.login = :l ", 	Account.class);
            q.setParameter("l", login);
            List<Account> foundAccounts = q.getResultList();
            if(foundAccounts.isEmpty())
            {
                return foundAccounts.get(0);
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
            if(findAccount(login)==null)
            {
                em.persist(new Account(login, password, type));
                return true;
            }
            return false;
        }
        catch (Exception e){
            throw new AccountDAOException(AccountDAOException.ERROR_INSERT_ACCOUNT, new Throwable(e));
        }
    }
}