package com.accenture.flowershop.be.access.flowerStock;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.flowerStock.FlowerStock;

import java.util.List;


public interface FlowerStockDAO {
    List<FlowerStock> getAllFlowerStocks() throws InternalException;
    FlowerStock findFlowerStock(Long id) throws InternalException;
    List<FlowerStock> findFlowerStocksByFlower(Flower flower) throws InternalException;
    List<FlowerStock> findFlowerStocksByFlowerCount(int flowerCount) throws InternalException;
    List<FlowerStock> findFlowerStocksByMinFlowerCount(int minFlowerCount) throws InternalException;
    List<FlowerStock> findFlowerStocksByMaxFlowerCount(int maxFlowerCount) throws InternalException;
    List<FlowerStock> findFlowerStocksByRangeFlowerCount(int minFlowerCount, int maxFlowerCount) throws InternalException;
    boolean increaseFlowerStockSize(Long id, int increaseValue) throws InternalException;
    boolean insertFlowerStock(Flower flower, int flowerCount) throws InternalException;
    boolean updateFlowerStock(FlowerStock flowerStock) throws InternalException;
}
