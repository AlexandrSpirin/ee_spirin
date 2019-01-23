package com.accenture.flowershop.be.access.flowerPool;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.flowerPool.FlowerPool;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class FlowerPoolDAOImpl implements FlowerPoolDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FlowerPool> getAllFlowerPools() throws InternalException {
        try {
            TypedQuery<FlowerPool> q = entityManager.createQuery("SELECT fP FROM flower_pools fP", FlowerPool.class);
            List<FlowerPool> foundFlowers = q.getResultList();
            return foundFlowers;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_POOLS_GET_ALL, new Throwable(e));
        }
    }

    @Override
    public List<FlowerPool> findFlowerPools(long id) throws InternalException{
        try {
            TypedQuery<FlowerPool> q = entityManager.createQuery("SELECT fP FROM flower_pools fP WHERE fP.id = :id", FlowerPool.class);
            q.setParameter("id", id);
            List<FlowerPool> foundFlowerPools = q.getResultList();
            return foundFlowerPools;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_POOLS_FIND_ID, new Throwable(e));
        }
    }

    @Override
    public List<FlowerPool> findFlowerPoolsByFlowerId(long flowerId) throws InternalException{
        try {
            TypedQuery<FlowerPool> q = entityManager.createQuery("SELECT fP FROM flower_pools fP WHERE fP.flowerId = :fId", FlowerPool.class);
            q.setParameter("fId", flowerId);
            List<FlowerPool> foundFlowerPools = q.getResultList();
            return foundFlowerPools;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_POOLS_FIND_FLOWER_ID, new Throwable(e));
        }
    }

    @Override
    public FlowerPool findFlowerPool(long id, long flowerId) throws InternalException{
        try {
            TypedQuery<FlowerPool> q = entityManager.createQuery("SELECT fP FROM flower_pools fP WHERE fP.id = :id AND" +
                    "fP.flowerId = :fId", FlowerPool.class);
            q.setParameter("id", id);
            q.setParameter("fId", flowerId);
            List<FlowerPool> foundFlowerPools = q.getResultList();
            if(!foundFlowerPools.isEmpty())
            {
                return foundFlowerPools.get(0);
            }
            return null;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_POOL_FIND, new Throwable(e));
        }
    }

    @Override
    public List<FlowerPool> findFlowerPoolsByFlowerCount(int flowerCount) throws InternalException{
        try {
            TypedQuery<FlowerPool> q = entityManager.createQuery("SELECT fP FROM flower_pools fP WHERE fP.flowerCount = :fC",
                    FlowerPool.class);
            q.setParameter("fC", flowerCount);
            List<FlowerPool> foundFlowerPools = q.getResultList();
            return foundFlowerPools;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_POOLS_FIND_FLOWER_COUNT, new Throwable(e));
        }
    }

    @Override
    public List<FlowerPool> findFlowerPoolsByMinFlowerCount(int minFlowerCount) throws InternalException{
        try {
            TypedQuery<FlowerPool> q = entityManager.createQuery("SELECT fP FROM flower_pools fP WHERE fP.flowerCount >= :minFC",
                    FlowerPool.class);
            q.setParameter("minFC", minFlowerCount);
            List<FlowerPool> foundFlowerPools = q.getResultList();
            return foundFlowerPools;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_POOLS_FIND_MIN_FLOWER_COUNT, new Throwable(e));
        }
    }

    @Override
    public List<FlowerPool> findFlowerPoolsByMaxFlowerCount(int maxFlowerCount) throws InternalException{
        try {
            TypedQuery<FlowerPool> q = entityManager.createQuery("SELECT fP FROM flower_pools fP WHERE fP.flowerCount <= :maxFC", FlowerPool.class);
            q.setParameter("maxFC", maxFlowerCount);
            List<FlowerPool> foundFlowerPools = q.getResultList();
            return foundFlowerPools;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_POOLS_FIND_MAX_FLOWER_COUNT, new Throwable(e));
        }
    }

    @Override
    public List<FlowerPool> findFlowerPoolsByRangeFlowerCount(int minFlowerCount, int maxFlowerCount) throws InternalException{
        try {
            TypedQuery<FlowerPool> q = entityManager.createQuery("SELECT fP FROM flower_pools fP WHERE fP.flowerCount >= :minFC AND " +
                    "fP.flowerCount <= :maxFC", FlowerPool.class);
            q.setParameter("minFC", minFlowerCount);
            q.setParameter("maxFC", maxFlowerCount);
            List<FlowerPool> foundFlowers = q.getResultList();
            return foundFlowers;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_POOLS_FIND_RANGE_FLOWER_COUNT, new Throwable(e));
        }
    }


    @Override
    @Transactional
    public boolean insertFlowerPool(long id, long flowerId, int flowerCount) throws InternalException{
        try {
            if(findFlowerPool(id, flowerId) == null)
            {
                entityManager.persist(new FlowerPool(id, flowerId, flowerCount));
                return true;
            }
            return false;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_POOL_INSERT, new Throwable(e));
        }
    }
}
