package com.accenture.flowershop.be.entity.account;

import javax.persistence.*;


@Entity(name="accounts")
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_seq", allocationSize = 1)
    private long id;

    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    private AccountType type;


    public Account(){}

    public Account(String login, String password, AccountType type){
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
