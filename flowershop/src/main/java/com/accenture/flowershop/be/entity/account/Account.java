package com.accenture.flowershop.be.entity.account;

import org.springframework.lang.NonNull;

import javax.persistence.*;


@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_seq", allocationSize = 1)
    @NonNull
    private long ID;

    @NonNull
    private String login;

    @NonNull
    private String password;

    @Enumerated(EnumType.STRING)
    @NonNull
    private AccountType type;

    public Account(){
        this.login = "";
        this.password = "";
        this.type = AccountType.customer;
    }


    public Account(String login, String password, String type){
        this.login = login;
        this.password = password;
        this.type = AccountType.valueOf(type);
    }


    public long getID() {
        return ID;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public AccountType getType() { return AccountType.valueOf(type.toString()); }
}
