package com.accenture.flowershop.be.business.flowerPool;


import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.access.flowerPool.FlowerPoolDAO;
import com.accenture.flowershop.be.entity.flowerPool.FlowerPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlowerPoolBusinessServiceImpl implements FlowerPoolBusinessService {
    @Autowired
    @Qualifier("flowerPoolDAOImpl")
    private FlowerPoolDAO flowerPoolDAO;

    @Override
    public List<FlowerPool> getAllFlowerPools() throws InternalException {
        try {
            return flowerPoolDAO.getAllFlowerPools();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_POOLS_GET_ALL, new Throwable(e));
        }
    }

    @Override
    public List<FlowerPool> findFlowerPools(long id) throws InternalException {
        try {
            return flowerPoolDAO.findFlowerPools(id);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_POOLS_FIND_ID, new Throwable(e));
        }
    }

    @Override
    public List<FlowerPool> findFlowerPoolsByFlowerId(long flowerId) throws InternalException {
        try {
            return flowerPoolDAO.findFlowerPoolsByFlowerId(flowerId);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_POOLS_FIND_FLOWER_ID, new Throwable(e));
        }
    }

    @Override
    public FlowerPool findFlowerPool(long id, long flowerId) throws InternalException {
        try {
            return flowerPoolDAO.findFlowerPool(id, flowerId);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_POOL_FIND, new Throwable(e));
        }
    }

    @Override
    public List<FlowerPool> findFlowerPoolsByFlowerCount(int flowerCount) throws InternalException {
        try {
            return flowerPoolDAO.findFlowerPoolsByFlowerCount(flowerCount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_POOLS_FIND_FLOWER_COUNT, new Throwable(e));
        }
    }


    @Override
    public List<FlowerPool> findFlowerPoolsByMinFlowerCount(int minFlowerCount) throws InternalException {
        try {
            return flowerPoolDAO.findFlowerPoolsByMinFlowerCount(minFlowerCount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_POOLS_FIND_MIN_FLOWER_COUNT, new Throwable(e));
        }
    }

    @Override
    public List<FlowerPool> findFlowerPoolsByMaxFlowerCount(int maxFlowerCount) throws InternalException {
        try {
            return flowerPoolDAO.findFlowerPoolsByMaxFlowerCount(maxFlowerCount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_POOLS_FIND_MAX_FLOWER_COUNT, new Throwable(e));
        }
    }


    @Override
    public List<FlowerPool> findFlowerPoolsByRangeFlowerCount(int minFlowerCount, int maxFlowerCount) throws InternalException {
        try {
            return flowerPoolDAO.findFlowerPoolsByRangeFlowerCount(minFlowerCount, maxFlowerCount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_POOLS_FIND_RANGE_FLOWER_COUNT, new Throwable(e));
        }
    }

    @Override
    public boolean buyFlowers(long flowerId, int flowerCount) throws InternalException{
        try {
            List<FlowerPool> findFlowerPools = findFlowerPoolsByFlowerId(flowerId);
            if(findFlowerPools != null){
                int flowerCountInFindPools = 0;
                for (FlowerPool fP:findFlowerPools) {
                    flowerCountInFindPools += fP.getFlowerCount();
                }
                if(flowerCountInFindPools>=flowerCount){
                    for (FlowerPool fP:findFlowerPools) {
                        if(fP.getFlowerCount()>flowerCount){
                            fP.setFlowerCount(fP.getFlowerCount()-flowerCount);
                            flowerCount = 0;
                        }
                        else {
                            fP.setFlowerCount(0);
                            flowerCount -= fP.getFlowerCount();
                        }
                    }
                    return true;
                }
            }
            return false;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_POOL_INSERT, new Throwable(e));
        }
    }


    @Override
    public boolean insertFlowerPool(long id, long flowerId, int flowerCount)
            throws InternalException {
        try {
            return flowerPoolDAO.insertFlowerPool(id, flowerId, flowerCount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_POOL_INSERT, new Throwable(e));
        }
    }
}
