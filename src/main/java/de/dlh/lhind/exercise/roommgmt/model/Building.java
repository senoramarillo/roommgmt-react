package de.dlh.lhind.exercise.roommgmt.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Building")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(unique = true)
    @NotBlank
    @NotNull
    private String buildingNumber;

    private String description;

    @Basic(optional = false)
    private Boolean publicAccess;

    public Building(@NotNull String buildingNumber, String description, Boolean publicAccess) {
        this.buildingNumber = buildingNumber;
        this.description = description;
        this.publicAccess = publicAccess;
    }

}
