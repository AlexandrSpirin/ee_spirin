package com.accenture.flowershop.be.business.flowerPool;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.flowerPool.FlowerPool;

import java.util.List;

public interface FlowerPoolBusinessService {
    List<FlowerPool> getAllFlowerPools() throws InternalException;
    List<FlowerPool> findFlowerPools(long id) throws InternalException;
    List<FlowerPool> findFlowerPoolsByFlowerId(long flowerId) throws InternalException;
    FlowerPool findFlowerPool(long id, long flowerId) throws InternalException;
    List<FlowerPool> findFlowerPoolsByFlowerCount(int flowerCount) throws InternalException;
    List<FlowerPool> findFlowerPoolsByMinFlowerCount(int minFlowerCount) throws InternalException;
    List<FlowerPool> findFlowerPoolsByMaxFlowerCount(int maxFlowerCount) throws InternalException;
    List<FlowerPool> findFlowerPoolsByRangeFlowerCount(int minFlowerCount, int maxFlowerCount) throws InternalException;
    boolean buyFlowers(long flowerId, int flowerCount) throws InternalException;
    boolean insertFlowerPool(long id, long flowerId, int flowerCount) throws InternalException;
}
