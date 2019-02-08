package com.accenture.flowershop.fe.ws.rest;


import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.business.account.AccountBusinessService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Component
@Path("/login-check")
public class LoginCheckRESTfulWebService {

    @Autowired
    private AccountBusinessService accountBusinessService;

    @Autowired
    private Mapper mapper;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{login}")
    public Boolean getLoginCheck(@PathParam("login") String login){
        try {
            if(accountBusinessService.findAccount(login) != null) {
                return true;
            }
            return false;
        } catch (InternalException e) {
            return false;
        }
    }
}
