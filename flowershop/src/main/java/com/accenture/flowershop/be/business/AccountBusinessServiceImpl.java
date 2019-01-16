package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.access.AccountDAO;
import com.accenture.flowershop.be.access.AccountDAOException;
import com.accenture.flowershop.be.entity.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class AccountBusinessServiceImpl implements AccountBusinessService {




    @Autowired
    @Qualifier("AccountDAOStubImpl")
    AccountDAO accountDAOImplVar;

    @Override
    public boolean Login(String login, String password) throws AccountDAOException {
        try {
            Account account = accountDAOImplVar.findAccount(login);
            if(account!=null) {
                if (account.getPassword().equals(password)) {
                    return true;
                }
            }
            return false;
        }
        catch (Exception e) {
            throw new AccountDAOException(AccountDAOException.ERROR_FIND_LOGIN, new Throwable(e));
        }

    }

    @Override
    public boolean Registration(String login, String password, String type) throws AccountDAOException {
        if(accountDAOImplVar.findAccount(login)==null) {
            accountDAOImplVar.insertAccount(login, password, type);
            return true;
        }
        return false;
    }
}
