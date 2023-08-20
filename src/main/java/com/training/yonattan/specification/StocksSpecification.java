package com.training.yonattan.specification;

import com.training.yonattan.entities.Stock;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

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
    public Specification<Stock> filter(
            String stockCode,
            String description,
            String active) {
        Specification spec = Specification.where(null);

        if(stockCode!=null && !stockCode.isBlank()){
            spec = spec.and(stockCode(stockCode));
        }
        if(description!=null && !description.isBlank()){
            spec = spec.and(description(description));
        }
        if(active!=null && !active.isBlank()){
            spec = spec.and(active(active));
        }
        System.out.println("Specification = ");
        System.out.println(spec.toString());
        return  spec;
    }
}
