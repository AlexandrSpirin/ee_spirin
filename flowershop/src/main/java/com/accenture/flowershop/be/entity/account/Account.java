package com.accenture.flowershop.be.entity.account;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="accounts")
@XmlRootElement
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_seq", allocationSize = 1)
    private Long id;

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


    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public AccountType getType() { return type; }

    @XmlElement
    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement
    public void setLogin(String login) {
        this.login = login;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement
    public void setType(AccountType type) {
        this.type = type;
    }
}
