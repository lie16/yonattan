package com.training.yonattan.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.training.yonattan.entities.Stock;

@Component
public class StocksSpecification  {
    private  Specification<Stock> stockCode(String stockCode) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("stockCode"), stockCode);
    }
    private  Specification<Stock> description(String description) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("description"), "%"+ description + "%");
    }
    private  Specification<Stock> active (String active) {
        if(active.equalsIgnoreCase("y")){
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("active"), true);
        } else if(active.equalsIgnoreCase("n")) {
            return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("active"), false);
        } else {
            throw new RuntimeException("Unknown parameter: " + active);
        }
    }
    private  Specification<Stock> stockType (int stockTypeId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("stock_type_stock_type_id"), stockTypeId);
    }
    public Specification<Stock> filter(
            String stockCode,
            String description,
            String active,
            Integer stockType) {
        Specification<Stock> spec = Specification.where(null);

        if(stockCode!=null && !stockCode.isBlank()){
            spec = spec.and(stockCode(stockCode));
        }
        if(description!=null && !description.isBlank()){
            spec = spec.and(description(description));
        }
        if(active!=null && !active.isBlank()){
            spec = spec.and(active(active));
        }
        if(stockType != null){
            spec = spec.and(stockType(stockType));
        }
        return  spec;
    }
}
