package com.accenture.flowershop.be.business.account;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.access.account.AccountDAO;
import com.accenture.flowershop.be.entity.account.Account;
import com.accenture.flowershop.be.entity.account.AccountType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountBusinessServiceImpl implements AccountBusinessService {

    @Autowired
    @Qualifier("accountDAOImpl")
    private AccountDAO accountDAO;


    @Override
    public List<Account> getAllAccounts() throws InternalException{
        try {
            return accountDAO.getAllAccounts();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ACCOUNTS_GET_ALL, new Throwable(e));
        }
    }

    @Override
    public Account findAccount(Long id) throws InternalException{
        try {
            return accountDAO.findAccount(id);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ACCOUNT_FIND_ID, new Throwable(e));
        }
    }

    @Override
    public Account findAccount(String login) throws InternalException{
        try {
            return accountDAO.findAccount(login);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ACCOUNT_FIND_LOGIN, new Throwable(e));
        }
    }

    @Override
    public boolean login(String login, String password) throws InternalException {
        try {
            Account findAccount=accountDAO.findAccount(login);
            if (findAccount!=null) {
                return StringUtils.equals(findAccount.getPassword(), password);
            }
            return false;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ACCOUNT_LOGIN, new Throwable(e));
        }
    }

    @Override
    public boolean registration(String login, String password, AccountType type) throws InternalException{
        try {
            return accountDAO.insertAccount(login, password, type);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ACCOUNT_REGISTER, new Throwable(e));
        }
    }

    @Override
    public boolean isAdmin(String login) throws InternalException{
        try {
            return ((accountDAO.findAccount(login) != null) && (accountDAO.findAccount(login).getType() == AccountType.ADMIN));
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_ACCOUNT_IS_ADMIN, new Throwable(e));
        }
    }
}
