package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.InternalException;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

@Component
public class FlowerDAOImpl implements FlowerDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Flower> getAllFlowers() throws InternalException {
        try {
            TypedQuery<Flower> q = entityManager.createQuery("SELECT f FROM flowers f", Flower.class);
            List<Flower> foundFlowers = q.getResultList();
            return foundFlowers;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_GET_ALL, new Throwable(e));
        }
    }

    @Override
    public Flower findFlower(long id) throws InternalException {
        try {
            return entityManager.find(Flower.class, id);
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_FIND_ID, new Throwable(e));
        }
    }

    @Override
    public Flower findFlower(String name) throws InternalException {
        try {
            TypedQuery<Flower> q = entityManager.createQuery("SELECT f FROM flowers f WHERE f.name = :n", Flower.class);
            q.setParameter("n", name);
            List<Flower> foundFlowers = q.getResultList();
            if(!foundFlowers.isEmpty())
            {
                return foundFlowers.get(0);
            }
            return null;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_FIND_NAME, new Throwable(e));
        }
    }

    @Override
    public List<Flower> findFlowers(String name) throws InternalException {
        try {
            TypedQuery<Flower> q = entityManager.createQuery("SELECT f FROM flowers f WHERE f.name LIKE :n", Flower.class);
            q.setParameter("n", "%" + name + "%");
            List<Flower> foundFlowers = q.getResultList();
            return foundFlowers;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWERS_FIND_NAME, new Throwable(e));
        }
    }

    @Override
    public List<Flower> findFlowers(BigDecimal cost) throws InternalException {
        try {
            TypedQuery<Flower> q = entityManager.createQuery("SELECT f FROM flowers f WHERE f.cost = :c", Flower.class);
            q.setParameter("c", cost);
            List<Flower> foundFlowers = q.getResultList();
            return foundFlowers;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWERS_FIND_COST, new Throwable(e));
        }
    }

    @Override
    public List<Flower> findFlowersByMinCost(BigDecimal minCost) throws InternalException {
        try {
            TypedQuery<Flower> q = entityManager.createQuery("SELECT f FROM flowers f WHERE f.cost >= :minC", Flower.class);
            q.setParameter("minC", minCost);
            List<Flower> foundFlowers = q.getResultList();
            return foundFlowers;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWERS_FIND_MIN_COST, new Throwable(e));
        }
    }

    @Override
    public List<Flower> findFlowersByMaxCost(BigDecimal maxCost) throws InternalException {
        try {
            TypedQuery<Flower> q = entityManager.createQuery("SELECT f FROM flowers f WHERE f.cost <= :maxC", Flower.class);
            q.setParameter("maxC", maxCost);
            List<Flower> foundFlowers = q.getResultList();
            return foundFlowers;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWERS_FIND_MAX_COST, new Throwable(e));
        }
    }

    @Override
    public List<Flower> findFlowersByRange(BigDecimal minCost, BigDecimal maxCost) throws InternalException {
        try {
            TypedQuery<Flower> q = entityManager.createQuery("SELECT f FROM flowers f WHERE f.cost >= :minC AND " +
                    "f.cost <= :maxC", Flower.class);
            q.setParameter("minC", minCost);
            q.setParameter("maxC", maxCost);
            List<Flower> foundFlowers = q.getResultList();
            return foundFlowers;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWERS_FIND_RANGE_COST, new Throwable(e));
        }
    }


    @Override
    @Transactional
    public boolean insertFlower(String name, BigDecimal cost)
            throws InternalException {
        try {
            if(findFlower(name) == null)
            {
                entityManager.persist(new Flower(name, cost));
                return true;
            }
            return false;
        }
        catch (Exception e){
            throw new InternalException(InternalException.ERROR_DAO_FLOWER_INSERT, new Throwable(e));
        }
    }
}
