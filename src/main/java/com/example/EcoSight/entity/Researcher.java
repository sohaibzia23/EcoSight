package com.example.EcoSight.entity;

import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.entity.User.UserRole;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@DiscriminatorValue("RESEARCHER")
public class Researcher extends User {
    @Override
    public UserRole getRole() {
        return UserRole.RESEARCHER;
    }
}
