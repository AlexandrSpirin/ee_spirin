package com.accenture.flowershop.fe.ws;

import com.accenture.flowershop.fe.dto.account.Account;
import com.accenture.flowershop.fe.dto.flower.Flower;
import com.accenture.flowershop.fe.dto.order.Order;
import com.accenture.flowershop.fe.dto.flowerPool.FlowerPool;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MapService {
    public com.accenture.flowershop.be.entity.account.Account mapToAccountEntity
            (Account accountDto, com.accenture.flowershop.be.entity.account.Account accountEntity) {
        if(accountDto != null) {
            if (accountEntity == null) {
                accountEntity = new com.accenture.flowershop.be.entity.account.Account();
            }
            accountEntity.setId(accountDto.getId());
            accountEntity.setLogin(accountDto.getLogin());
            accountEntity.setPassword(accountDto.getPassword());
            accountEntity.setType(accountDto.getType());
        }
        return accountEntity;
    }

    public Account mapToAccountDto
            (Account accountDto, com.accenture.flowershop.be.entity.account.Account accountEntity) {
        if(accountEntity != null) {
            if (accountDto == null) {
                accountDto = new Account();
            }
            accountDto.setId(accountEntity.getId());
            accountDto.setLogin(accountEntity.getLogin());
            accountDto.setPassword(accountEntity.getPassword());
            accountDto.setType(accountEntity.getType());
        }
        return accountDto;
    }


    public List<com.accenture.flowershop.be.entity.account.Account> mapAllAccountEntities
            (List<Account> accountDtos, List<com.accenture.flowershop.be.entity.account.Account> accountEntities) {
        for (int i = 0; i < accountDtos.size(); i++) {
            if(accountEntities.size() < i+1) {
                accountEntities.add(new com.accenture.flowershop.be.entity.account.Account());
            }
            mapToAccountEntity(accountDtos.get(i), accountEntities.get(i));
        }
        return accountEntities;
    }

    public List<Account> mapAllAccountDtos
            (List<Account> accountDtos, List<com.accenture.flowershop.be.entity.account.Account> accountEntities) {
        for (int i = 0; i<accountEntities.size(); i++) {
            if(accountDtos.size() < i+1) {
                accountDtos.add(new Account());
            }
            mapToAccountDto(accountDtos.get(i), accountEntities.get(i));
        }
        return accountDtos;
    }




    public com.accenture.flowershop.be.entity.flower.Flower mapToFlowerEntity
            (Flower flowerDto, com.accenture.flowershop.be.entity.flower.Flower flowerEntity) {
        if(flowerDto != null) {
            if (flowerEntity == null) {
                flowerEntity = new com.accenture.flowershop.be.entity.flower.Flower();
            }
            flowerEntity.setId(flowerDto.getId());
            flowerEntity.setName(flowerDto.getName());
            flowerEntity.setCost(flowerDto.getCost());
        }
        return flowerEntity;
    }

    public Flower mapToFlowerDto
            (Flower flowerDto, com.accenture.flowershop.be.entity.flower.Flower flowerEntity) {
        if(flowerEntity != null) {
            if (flowerDto == null) {
                flowerDto = new Flower();
            }
            flowerDto.setId(flowerEntity.getId());
            flowerDto.setName(flowerEntity.getName());
            flowerDto.setCost(flowerEntity.getCost());
        }
        return flowerDto;
    }


    public List<com.accenture.flowershop.be.entity.flower.Flower> mapAllFlowerEntities
            (List<Flower> flowerDtos, List<com.accenture.flowershop.be.entity.flower.Flower> flowerEntities) {
        for (int i = 0; i < flowerDtos.size(); i++) {
            if(flowerEntities.size() < i+1) {
                flowerEntities.add(new com.accenture.flowershop.be.entity.flower.Flower());
            }
            mapToFlowerEntity(flowerDtos.get(i), flowerEntities.get(i));
        }
        return flowerEntities;
    }

    public List<Flower> mapAllFlowerDtos
            (List<Flower> flowerDtos, List<com.accenture.flowershop.be.entity.flower.Flower> flowerEntities) {
        for (int i = 0; i < flowerEntities.size(); i++) {
            if(flowerDtos.size() < i+1) {
                flowerDtos.add(new Flower());
            }
            mapToFlowerDto(flowerDtos.get(i), flowerEntities.get(i));
        }
        return flowerDtos;
    }




    public com.accenture.flowershop.be.entity.flowerPool.FlowerPool mapToFlowerPoolEntity
            (FlowerPool flowerPoolDto, com.accenture.flowershop.be.entity.flowerPool.FlowerPool flowerPoolEntity) {
        if(flowerPoolDto != null) {
            if (flowerPoolEntity == null) {
                flowerPoolEntity = new com.accenture.flowershop.be.entity.flowerPool.FlowerPool();
            }
            flowerPoolEntity.setId(flowerPoolDto.getId());
            flowerPoolEntity.setFlowerId(flowerPoolDto.getFlowerId());
            flowerPoolEntity.setFlowerCount(flowerPoolDto.getFlowerCount());
        }
        return flowerPoolEntity;
    }

    public FlowerPool mapToFlowerPoolDto
            (FlowerPool flowerPoolDto, com.accenture.flowershop.be.entity.flowerPool.FlowerPool flowerPoolEntity) {
        if(flowerPoolEntity != null) {
            if (flowerPoolDto == null) {
                flowerPoolDto = new FlowerPool();
            }
            flowerPoolDto.setId(flowerPoolEntity.getId());
            flowerPoolDto.setFlowerId(flowerPoolEntity.getFlowerId());
            flowerPoolDto.setFlowerCount(flowerPoolEntity.getFlowerCount());
        }
        return flowerPoolDto;
    }


    public List<com.accenture.flowershop.be.entity.flowerPool.FlowerPool> mapAllFlowerPoolEntities
            (List<FlowerPool> flowerPoolDtos, List<com.accenture.flowershop.be.entity.flowerPool.FlowerPool> flowerPoolEntities) {
        for (int i = 0; i < flowerPoolDtos.size(); i++) {
            if(flowerPoolEntities.size() < i+1) {
                flowerPoolEntities.add(new com.accenture.flowershop.be.entity.flowerPool.FlowerPool());
            }
            mapToFlowerPoolEntity(flowerPoolDtos.get(i), flowerPoolEntities.get(i));
        }
        return flowerPoolEntities;
    }

    public List<FlowerPool> mapAllFlowerPoolDtos
            (List<FlowerPool> flowerPoolDtos, List<com.accenture.flowershop.be.entity.flowerPool.FlowerPool> orderEntities) {
        for (int i = 0; i < orderEntities.size(); i++) {
            if(flowerPoolDtos.size() < i+1) {
                flowerPoolDtos.add(new FlowerPool());
            }
            mapToFlowerPoolDto(flowerPoolDtos.get(i), orderEntities.get(i));
        }
        return flowerPoolDtos;
    }





    public com.accenture.flowershop.be.entity.order.Order mapToOrderEntity
            (Order orderDto, com.accenture.flowershop.be.entity.order.Order orderEntity) {
        if(orderDto != null) {
            if (orderEntity == null) {
                orderEntity = new com.accenture.flowershop.be.entity.order.Order();
            }
            orderEntity.setId(orderDto.getId());
            orderEntity.setAccountId(orderDto.getAccountId());
            orderEntity.setStatus(orderDto.getStatus());
            orderEntity.setCreateDate(orderDto.getCreateDate());
            orderEntity.setCloseDate(orderDto.getCloseDate());
            orderEntity.setDiscount(orderDto.getDiscount());
            orderEntity.setFinalPrice(orderDto.getFinalPrice());
        }
        return orderEntity;
    }

    public Order mapToOrderDto
            (Order orderDto, com.accenture.flowershop.be.entity.order.Order orderEntity) {
        if(orderEntity != null) {
            if (orderDto == null) {
                orderDto = new Order();
            }
            orderDto.setId(orderEntity.getId());
            orderDto.setAccountId(orderEntity.getAccountId());
            orderDto.setStatus(orderEntity.getStatus());
            orderDto.setCreateDate(orderEntity.getCreateDate());
            orderDto.setCloseDate(orderEntity.getCloseDate());
            orderDto.setDiscount(orderEntity.getDiscount());
            orderDto.setFinalPrice(orderEntity.getFinalPrice());
        }
        return orderDto;
    }


    public List<com.accenture.flowershop.be.entity.order.Order> mapAllOrderEntities
            (List<Order> orderDtos, List<com.accenture.flowershop.be.entity.order.Order> orderEntities) {
        for (int i = 0; i < orderDtos.size(); i++) {
            if(orderEntities.size() < i+1) {
                orderEntities.add(new com.accenture.flowershop.be.entity.order.Order());
            }
            mapToOrderEntity(orderDtos.get(i), orderEntities.get(i));
        }
        return orderEntities;
    }

    public List<Order> mapAllOrderDtos
            (List<Order> orderDtos, List<com.accenture.flowershop.be.entity.order.Order> orderEntities) {
        for (int i = 0; i < orderEntities.size(); i++) {
            if(orderDtos.size() < i+1) {
                orderDtos.add(new Order());
            }
            mapToOrderDto(orderDtos.get(i), orderEntities.get(i));
        }
        return orderDtos;
    }
}
