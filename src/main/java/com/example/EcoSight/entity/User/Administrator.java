package com.example.EcoSight.entity.User;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@DiscriminatorValue("ADMINISTRATOR")
public class Administrator extends User {
    @Override
    public UserRole getRole() {
        return UserRole.ADMINISTRATOR;
    }
}
