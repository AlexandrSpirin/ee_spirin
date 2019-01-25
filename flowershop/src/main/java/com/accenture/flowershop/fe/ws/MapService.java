package com.accenture.flowershop.fe.ws;

import com.accenture.flowershop.fe.dto.account.Account;
import com.accenture.flowershop.fe.dto.customer.Customer;
import com.accenture.flowershop.fe.dto.flower.Flower;
import com.accenture.flowershop.fe.dto.order.Order;
import com.accenture.flowershop.fe.dto.flowerStock.FlowerStock;
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




    public com.accenture.flowershop.be.entity.customer.Customer mapToCustomerEntity
            (Customer customerDto, com.accenture.flowershop.be.entity.customer.Customer customerEntity) {
        if(customerDto != null) {
            if (customerEntity == null) {
                customerEntity = new com.accenture.flowershop.be.entity.customer.Customer();
            }
            customerEntity.setId(customerDto.getId());
            customerEntity.setAccount(mapToAccountEntity(customerDto.getAccount(), new com.accenture.flowershop.be.entity.account.Account()));
            customerEntity.setFirstName(customerDto.getFirstName());
            customerEntity.setMiddleName(customerDto.getMiddleName());
            customerEntity.setLastName(customerDto.getLastName());
            customerEntity.setEmail(customerDto.getEmail());
            customerEntity.setPhoneNumber(customerDto.getPhoneNumber());
            customerEntity.setMoney(customerDto.getMoney());
            customerEntity.setDiscount(customerDto.getDiscount());
        }
        return customerEntity;
    }

    public Customer mapToCustomerDto
            (Customer customerDto, com.accenture.flowershop.be.entity.customer.Customer customerEntity) {
        if(customerEntity != null) {
            if (customerDto == null) {
                customerDto = new Customer();
            }
            customerDto.setId(customerEntity.getId());
            customerDto.setAccount(mapToAccountDto(new Account(), customerEntity.getAccount()));
            customerDto.setFirstName(customerEntity.getFirstName());
            customerDto.setMiddleName(customerEntity.getMiddleName());
            customerDto.setLastName(customerEntity.getLastName());
            customerDto.setEmail(customerEntity.getEmail());
            customerDto.setPhoneNumber(customerEntity.getPhoneNumber());
            customerDto.setMoney(customerEntity.getMoney());
            customerDto.setDiscount(customerEntity.getDiscount());
        }
        return customerDto;
    }


    public List<com.accenture.flowershop.be.entity.customer.Customer> mapAllCustomerEntities
            (List<Customer> customerDtos, List<com.accenture.flowershop.be.entity.customer.Customer> customerEntities) {
        for (int i = 0; i < customerDtos.size(); i++) {
            if(customerEntities.size() < i+1) {
                customerEntities.add(new com.accenture.flowershop.be.entity.customer.Customer());
            }
            mapToCustomerEntity(customerDtos.get(i), customerEntities.get(i));
        }
        return customerEntities;
    }

    public List<Customer> mapAllCustomerDtos
            (List<Customer> customerDtos, List<com.accenture.flowershop.be.entity.customer.Customer> customerEntities) {
        for (int i = 0; i<customerEntities.size(); i++) {
            if(customerDtos.size() < i+1) {
                customerDtos.add(new Customer());
            }
            mapToCustomerDto(customerDtos.get(i), customerEntities.get(i));
        }
        return customerDtos;
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




    public com.accenture.flowershop.be.entity.flowerStock.FlowerStock mapToFlowerStockEntity
            (FlowerStock flowerStockDto, com.accenture.flowershop.be.entity.flowerStock.FlowerStock flowerStockEntity) {
        if(flowerStockDto != null) {
            if (flowerStockEntity == null) {
                flowerStockEntity = new com.accenture.flowershop.be.entity.flowerStock.FlowerStock();
            }
            flowerStockEntity.setId(flowerStockDto.getId());
            flowerStockEntity.setFlower(mapToFlowerEntity(flowerStockDto.getFlower(), new com.accenture.flowershop.be.entity.flower.Flower()));
            flowerStockEntity.setFlowerCount(flowerStockDto.getFlowerCount());
        }
        return flowerStockEntity;
    }

    public FlowerStock mapToFlowerStockDto
            (FlowerStock flowerStockDto, com.accenture.flowershop.be.entity.flowerStock.FlowerStock flowerStockEntity) {
        if(flowerStockEntity != null) {
            if (flowerStockDto == null) {
                flowerStockDto = new FlowerStock();
            }
            flowerStockDto.setId(flowerStockEntity.getId());
            flowerStockDto.setFlower(mapToFlowerDto(new Flower(), flowerStockEntity.getFlower()));
            flowerStockDto.setFlowerCount(flowerStockEntity.getFlowerCount());
        }
        return flowerStockDto;
    }


    public List<com.accenture.flowershop.be.entity.flowerStock.FlowerStock> mapAllFlowerStockEntities
            (List<FlowerStock> flowerStockDtos, List<com.accenture.flowershop.be.entity.flowerStock.FlowerStock> flowerStockEntities) {
        for (int i = 0; i < flowerStockDtos.size(); i++) {
            if(flowerStockEntities.size() < i+1) {
                flowerStockEntities.add(new com.accenture.flowershop.be.entity.flowerStock.FlowerStock());
            }
            mapToFlowerStockEntity(flowerStockDtos.get(i), flowerStockEntities.get(i));
        }
        return flowerStockEntities;
    }

    public List<FlowerStock> mapAllFlowerStockDtos
            (List<FlowerStock> flowerStockDtos, List<com.accenture.flowershop.be.entity.flowerStock.FlowerStock> orderEntities) {
        for (int i = 0; i < orderEntities.size(); i++) {
            if(flowerStockDtos.size() < i+1) {
                flowerStockDtos.add(new FlowerStock());
            }
            mapToFlowerStockDto(flowerStockDtos.get(i), orderEntities.get(i));
        }
        return flowerStockDtos;
    }





    public com.accenture.flowershop.be.entity.order.Order mapToOrderEntity
            (Order orderDto, com.accenture.flowershop.be.entity.order.Order orderEntity) {
        if(orderDto != null) {
            if (orderEntity == null) {
                orderEntity = new com.accenture.flowershop.be.entity.order.Order();
            }
            orderEntity.setId(orderDto.getId());
            orderEntity.setCustomer(mapToCustomerEntity(orderDto.getCustomer(), new com.accenture.flowershop.be.entity.customer.Customer()));
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
            orderDto.setCustomer(mapToCustomerDto(new Customer(), orderEntity.getCustomer()));
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
