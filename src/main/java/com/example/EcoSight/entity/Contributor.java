package com.example.EcoSight.entity;

import com.example.EcoSight.entity.User.User;
import com.example.EcoSight.entity.User.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@DiscriminatorValue("CONTRIBUTOR")
public class Contributor extends User {
    @Override
    public UserRole getRole() {
        return UserRole.CONTRIBUTOR;
    }
}