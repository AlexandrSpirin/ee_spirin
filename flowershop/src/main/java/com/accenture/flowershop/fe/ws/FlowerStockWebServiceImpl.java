package com.accenture.flowershop.fe.ws;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.business.flowerStock.FlowerStockBusinessService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

@WebService(endpointInterface = "com.accenture.flowershop.fe.ws.FlowerStockWebService")
public class FlowerStockWebServiceImpl implements FlowerStockWebService {

    @Autowired
    private FlowerStockBusinessService flowerStockBusinessService;

    public void increaseFlowerStockSize(Long flowerStockId) {
        try {
            flowerStockBusinessService.increaseFlowerStockSize(flowerStockId);
        } catch (InternalException e) {
            e.printStackTrace();
        }
    }
}
