package com.accenture.flowershop.be.business.flowerStock;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.flowerStock.FlowerStock;

import java.util.List;

public interface FlowerStockBusinessService {
    List<FlowerStock> getAllFlowerStocks() throws InternalException;
    FlowerStock findFlowerStock(Long id) throws InternalException;
    List<FlowerStock> findFlowerStocksByFlower(Flower flower) throws InternalException;
    List<FlowerStock> findFlowerStocksByFlowerCount(int flowerCount) throws InternalException;
    List<FlowerStock> findFlowerStocksByMinFlowerCount(int minFlowerCount) throws InternalException;
    List<FlowerStock> findFlowerStocksByMaxFlowerCount(int maxFlowerCount) throws InternalException;
    List<FlowerStock> findFlowerStocksByRangeFlowerCount(int minFlowerCount, int maxFlowerCount) throws InternalException;
    boolean buyFlowers(Flower flower, int flowerCount) throws InternalException;
    boolean insertFlowerStock(Long id, Flower flower, int flowerCount, Long flowerStockId) throws InternalException;
}
