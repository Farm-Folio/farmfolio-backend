package com.farm.farmfolio.domain;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "app_user")
@Where(clause = "is_deleted = false")
public class AppUser extends AuditableBase{
    private String name;
    private String lastName;
    private String email;
    private String mobileNumber;
    private Double latitude;
    private Double longitude;
    private Integer type;
}
