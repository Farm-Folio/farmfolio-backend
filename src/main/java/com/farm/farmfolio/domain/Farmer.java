package com.farm.farmfolio.domain;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "farmer")
@Where(clause = "is_deleted = false")
public class Farmer extends AuditableBase{
    private String name;
    private String lastName;
    private String email;
    private String mobileNumber;
    private Double latitude;
    private Double longitude;
}
