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

    @Getter
    @Setter
    @Entity
    @Table(name="wildlife_sightings")
    public static class Researcher {

        @Id
        @Column(name="ResearcherID")
        private Integer researcherID;

        @Column(name = "REmail")
        private String rEmail;

        @Column(name = "RFirstName")
        private String rFirstName;

        @Column(name = "RLastName")
        private String rLastName;


        public Researcher(String rEmail, Integer researcherID, String rFirstName, String rLastName) {
            this.rEmail = rEmail;
            this.researcherID = researcherID;
            this.rFirstName = rFirstName;
            this.rLastName = rLastName;
        }
    }
}