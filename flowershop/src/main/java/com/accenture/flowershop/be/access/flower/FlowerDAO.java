package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.flower.Flower;

import java.math.BigDecimal;
import java.util.List;

public interface FlowerDAO {
    List<Flower> getAllFlowers() throws InternalException;
    Flower findFlower(Long id) throws InternalException;
    Flower findFlower(String name) throws InternalException;
    List<Flower> findFlowers(String name) throws InternalException;
    List<Flower> findFlowers(String name, BigDecimal minCost, BigDecimal maxCost) throws InternalException;
    List<Flower> findFlowers(BigDecimal cost) throws InternalException;
    List<Flower> findFlowersByMinCost(BigDecimal minCost) throws InternalException;
    List<Flower> findFlowersByMaxCost(BigDecimal maxCost) throws InternalException;
    List<Flower> findFlowersByRange(BigDecimal minCost, BigDecimal maxCost) throws InternalException;
    boolean insertFlower(String name, BigDecimal cost) throws InternalException;
}
