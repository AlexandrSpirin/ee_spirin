package com.accenture.flowershop.be.access.flowerStock;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.flowerStock.FlowerStock;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class FlowerStockDAOImpl implements FlowerStockDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FlowerStock> getAllFlowerStocks() throws InternalException {
        try {
            TypedQuery<FlowerStock> q = entityManager.createQuery("SELECT fS FROM flower_stocks fS", FlowerStock.class);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_STOCKS_GET_ALL, new Throwable(e));
        }
    }

    @Override
    public FlowerStock findFlowerStock(Long id) throws InternalException{
        try {
            TypedQuery<FlowerStock> q = entityManager.createQuery("SELECT fS FROM flower_stocks fS WHERE fS.id = :id", FlowerStock.class);
            q.setParameter("id", id);
            List<FlowerStock> foundFlowerStock = q.getResultList();
            if(!foundFlowerStock.isEmpty())
            {
                return foundFlowerStock.get(0);
            }
            return null;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_STOCK_FIND_ID, new Throwable(e));
        }
    }

    @Override
    public List<FlowerStock> findFlowerStocksByFlower(Flower flower) throws InternalException{
        try {
            TypedQuery<FlowerStock> q = entityManager.createQuery("SELECT fS FROM FlowerStock fS WHERE fS.flower.id = :fId", FlowerStock.class);
            q.setParameter("fId", flower.getId());
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_STOCKS_FIND_FLOWER, new Throwable(e));
        }
    }

    @Override
    public List<FlowerStock> findFlowerStocksByFlowerCount(int flowerCount) throws InternalException{
        try {
            TypedQuery<FlowerStock> q = entityManager.createQuery("SELECT fS FROM flower_stocks fS WHERE fS.flowerCount = :fC",
                    FlowerStock.class);
            q.setParameter("fC", flowerCount);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_STOCKS_FIND_FLOWER_COUNT, new Throwable(e));
        }
    }

    @Override
    public List<FlowerStock> findFlowerStocksByMinFlowerCount(int minFlowerCount) throws InternalException{
        try {
            TypedQuery<FlowerStock> q = entityManager.createQuery("SELECT fS FROM flower_stocks fS WHERE fS.flowerCount >= :minFC",
                    FlowerStock.class);
            q.setParameter("minFC", minFlowerCount);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_STOCKS_FIND_MIN_FLOWER_COUNT, new Throwable(e));
        }
    }

    @Override
    public List<FlowerStock> findFlowerStocksByMaxFlowerCount(int maxFlowerCount) throws InternalException{
        try {
            TypedQuery<FlowerStock> q = entityManager.createQuery("SELECT fS FROM flower_stocks fS WHERE fS.flowerCount <= :maxFC", FlowerStock.class);
            q.setParameter("maxFC", maxFlowerCount);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_STOCKS_FIND_MAX_FLOWER_COUNT, new Throwable(e));
        }
    }

    @Override
    public List<FlowerStock> findFlowerStocksByRangeFlowerCount(int minFlowerCount, int maxFlowerCount) throws InternalException{
        try {
            TypedQuery<FlowerStock> q = entityManager.createQuery("SELECT fS FROM flower_stocks fS WHERE fS.flowerCount >= :minFC AND " +
                    "fS.flowerCount <= :maxFC", FlowerStock.class);
            q.setParameter("minFC", minFlowerCount);
            q.setParameter("maxFC", maxFlowerCount);
            return q.getResultList();
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_STOCKS_FIND_RANGE_FLOWER_COUNT, new Throwable(e));
        }
    }


    @Override
    @Transactional
    public boolean insertFlowerStock(Long id, Flower flower, int flowerCount, Long flowerStockId) throws InternalException{
        try {
            if(findFlowerStock(id) == null)
            {
                entityManager.persist(new FlowerStock(id, flower, flowerCount, flowerStockId));
                return true;
            }
            return false;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_STOCK_INSERT, new Throwable(e));
        }
    }
}