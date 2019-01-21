package com.accenture.flowershop.fe.dto.account;

import com.accenture.flowershop.be.entity.account.AccountType;

public class Account {
    private long id;

    private String login;

    private String password;

    private AccountType type;


    public Account(){}

    public Account(long id, String login, String password, AccountType type){
        this.id = id;
        this.login = login;
        this.password = password;
        this.type = type;
    }


    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public AccountType getType() { return type; }


    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
