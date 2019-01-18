package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.access.AccountDAO;
import com.accenture.flowershop.be.entity.account.Account;
import com.accenture.flowershop.be.entity.account.AccountType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class AccountBusinessServiceImpl implements AccountBusinessService {

    @Autowired
    @Qualifier("accountDAOImpl")
    AccountDAO accountDAO;

    @Override
    public boolean login(String login, String password) throws InternalException {
        try {
            Account findAccount=accountDAO.findAccount(login);
            if (findAccount!=null) {
                return StringUtils.equals(findAccount.getPassword(), password)?true:false;
            }
            return false;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_LOGIN, new Throwable(e));
        }
    }

    @Override
    public boolean registration(String login, String password, AccountType type) throws InternalException{
        try {
            if (accountDAO.insertAccount(login, password, type)) {
                return true;
            }
            return false;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_REGISTER, new Throwable(e));
        }
    }
}
