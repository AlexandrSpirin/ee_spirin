package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.access.flower.FlowerDAO;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {

    @Autowired
    @Qualifier("flowerDAOImpl")
    private FlowerDAO flowerDAO;

    @Override
    public List<Flower> getAllFlowers() throws InternalException {
        try {
            return flowerDAO.getAllFlowers();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWERS_GET_ALL, new Throwable(e));
        }
    }

    @Override
    public Flower findFlower(Long id) throws InternalException {
        try {
            return flowerDAO.findFlower(id);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_FIND_ID, new Throwable(e));
        }
    }

    @Override
    public Flower findFlower(String name) throws InternalException {
        try {
            return flowerDAO.findFlower(name);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_FIND_NAME, new Throwable(e));
        }
    }

    @Override
    public List<Flower> findFlowers(String name) throws InternalException {
        try {
            return flowerDAO.findFlowers(name);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWERS_FIND_NAME, new Throwable(e));
        }
    }

    @Override
    public List<Flower> findFlowers(String name, BigDecimal minCost, BigDecimal maxCost) throws InternalException {
        try {
            return flowerDAO.findFlowers(name, minCost, maxCost);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWERS_FIND_NAME_AND_RANGE_COST, new Throwable(e));
        }
    }

    @Override
    public List<Flower> findFlowers(BigDecimal cost) throws InternalException {
        try {
            return flowerDAO.findFlowers(cost);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWERS_FIND_COST, new Throwable(e));
        }
    }

    @Override
    public List<Flower> findFlowersByMinCost(BigDecimal minCost) throws InternalException{
        try {
            return flowerDAO.findFlowersByMinCost(minCost);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWERS_FIND_MIN_COST, new Throwable(e));
        }
    }

    @Override
    public List<Flower> findFlowersByMaxCost(BigDecimal maxCost) throws InternalException{
        try {
            return flowerDAO.findFlowersByMaxCost(maxCost);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWERS_FIND_MAX_COST, new Throwable(e));
        }
    }

    @Override
    public List<Flower> findFlowersByRange(BigDecimal minCost, BigDecimal maxCost) throws InternalException{
        try {
            return flowerDAO.findFlowersByRange(minCost, maxCost);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWERS_FIND_RANGE_COST, new Throwable(e));
        }
    }


    @Override
    public boolean insertFlower(String name, BigDecimal cost)
            throws InternalException {
        try {
            return flowerDAO.insertFlower(name, cost);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_SERVICE_FLOWER_INSERT, new Throwable(e));
        }
    }
}
