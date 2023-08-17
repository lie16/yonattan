package com.training.yonattan.specification;

import com.training.yonattan.entities.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {
    private  Specification<Employee> nik (String nik) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("nik"), nik);
    }
    private  Specification<Employee> employeeName (String employeeName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("employeeName"), "%"+ employeeName + "%");
    }
    private  Specification<Employee> address (String address) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("address"), "%"+ address + "%");
    }
    private  Specification<Employee> city (String city) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("city"), city);
    }
    private  Specification<Employee> province (String province) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("province"), province);
    }
    private  Specification<Employee> phoneNumber (String phoneNumber) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("phoneNumber"), "%"+ phoneNumber + "%");
    }
    private  Specification<Employee> email (String email) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("email"), "%"+ email + "%");
    }
    private  Specification<Employee> active (String active) {
        if(active.equalsIgnoreCase("y")){
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("active"), "true");
        } else if(active.equalsIgnoreCase("n")) {
            return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("active"), "false");
        } else {
            throw new RuntimeException("Unknown parameter: " + active);
        }
    }
    public Specification<Employee> filter(
            String nik,
            String employeeName,
            String address,
            String city,
            String province,
            String email,
            String phoneNumber,
            String active) {
        Specification spec = Specification.where(null);

        if(nik!=null && !nik.isBlank()){
            spec = spec.and(nik(nik));
        }
        if(employeeName!=null && !employeeName.isBlank()){
            spec = spec.and(employeeName(employeeName));
        }
        if(address!=null && !address.isBlank()){
            spec = spec.and(address(address));
        }
        if(city!=null && !city.isBlank()){
            spec = spec.and(city(city));
        }
        if(province!=null && !province.isBlank()){
            spec = spec.and(province(province));
        }
        if(email!=null && !email.isBlank()){
            spec = spec.and(email(email));
        }
        if(phoneNumber!=null && !phoneNumber.isBlank()){
            spec = spec.and(phoneNumber(phoneNumber));
        }
        if(active!=null && !active.isBlank()){
            spec = spec.and(active(active));
        }
        return  spec;
    }
}
