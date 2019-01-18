package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.account.Account;
import com.accenture.flowershop.be.entity.account.AccountType;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class AccountDAOImpl implements AccountDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public Account findAccount(long id) throws InternalException {
        try {
            return entityManager.find(Account.class, id);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FIND_ID, new Throwable(e));
        }
    }

    public Account findAccount(String login) throws InternalException {
        try {
            TypedQuery<Account> q = entityManager.createQuery("SELECT a FROM accounts a WHERE a.login = :l ", Account.class);
            q.setParameter("l", login);
            List<Account> foundAccounts = q.getResultList();
            if(!foundAccounts.isEmpty())
            {
                return foundAccounts.get(0);
            }
            return null;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FIND_LOGIN, new Throwable(e));
        }
    }

    public boolean insertAccount(String login, String password, AccountType type)
            throws InternalException {
        try {
            if(findAccount(login)==null)
            {
                entityManager.persist(new Account(login, password, type));
                return true;
            }
            return false;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_INSERT_ACCOUNT, new Throwable(e));
        }
    }
}
