package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.flower.Flower;

import java.math.BigDecimal;
import java.util.List;

public interface FlowerBusinessService {
    List<Flower> getAllFlowers() throws InternalException;
    Flower findFlower(long id) throws InternalException;
    Flower findFlower(String name) throws InternalException;
    List<Flower> findFlowers(String name) throws InternalException;
    List<Flower> findFlowers(BigDecimal cost) throws InternalException;
    boolean insertFlower(String name, BigDecimal cost) throws InternalException;
}
