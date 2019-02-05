package com.accenture.flowershop.be.business.flowerStock;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.access.flowerStock.FlowerStockDAO;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.flowerStock.FlowerStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlowerStockBusinessServiceImpl implements FlowerStockBusinessService {
    @Autowired
    @Qualifier("flowerStockDAOImpl")
    private FlowerStockDAO flowerStockDAO;

    @Override
    public List<FlowerStock> getAllFlowerStocks() throws InternalException {
        try {
            return flowerStockDAO.getAllFlowerStocks();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_STOCKS_GET_ALL, new Throwable(e));
        }
    }

    @Override
    public FlowerStock findFlowerStock(Long id) throws InternalException {
        try {
            return flowerStockDAO.findFlowerStock(id);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_STOCK_FIND_ID, new Throwable(e));
        }
    }

    @Override
    public List<FlowerStock> findFlowerStocksByFlower(Flower flower) throws InternalException {
        try {
            return flowerStockDAO.findFlowerStocksByFlower(flower);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_STOCKS_FIND_FLOWER, new Throwable(e));
        }
    }

    @Override
    public List<FlowerStock> findFlowerStocksByFlowerCount(int flowerCount) throws InternalException {
        try {
            return flowerStockDAO.findFlowerStocksByFlowerCount(flowerCount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_STOCKS_FIND_FLOWER_COUNT, new Throwable(e));
        }
    }

    @Override
    public List<FlowerStock> findFlowerStocksByMinFlowerCount(int minFlowerCount) throws InternalException {
        try {
            return flowerStockDAO.findFlowerStocksByMinFlowerCount(minFlowerCount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_STOCKS_FIND_MIN_FLOWER_COUNT, new Throwable(e));
        }
    }

    @Override
    public List<FlowerStock> findFlowerStocksByMaxFlowerCount(int maxFlowerCount) throws InternalException {
        try {
            return flowerStockDAO.findFlowerStocksByMaxFlowerCount(maxFlowerCount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_STOCKS_FIND_MAX_FLOWER_COUNT, new Throwable(e));
        }
    }


    @Override
    public List<FlowerStock> findFlowerStocksByRangeFlowerCount(int minFlowerCount, int maxFlowerCount) throws InternalException {
        try {
            return flowerStockDAO.findFlowerStocksByRangeFlowerCount(minFlowerCount, maxFlowerCount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_STOCKS_FIND_RANGE_FLOWER_COUNT, new Throwable(e));
        }
    }


    @Override
    public boolean insertFlowerStock(Flower flower, int flowerCount)
            throws InternalException {
        try {
            return flowerStockDAO.insertFlowerStock(flower, flowerCount);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_STOCK_INSERT, new Throwable(e));
        }
    }

    @Override
    public boolean updateFlowerStock(FlowerStock flowerStock)
            throws InternalException {
        try {
            return flowerStockDAO.updateFlowerStock(flowerStock);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_STOCK_UPDATE, new Throwable(e));
        }
    }
}
