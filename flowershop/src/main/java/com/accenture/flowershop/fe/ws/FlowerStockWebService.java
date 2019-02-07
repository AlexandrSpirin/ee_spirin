package com.accenture.flowershop.fe.ws;


import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface FlowerStockWebService {
    @WebResult void increaseFlowerStockSize(
            @WebParam(name = "request") Long flowerStockId);

}
