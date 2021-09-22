package de.dlh.lhind.exercise.roommgmt.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Room")
public class Room {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @NotNull
    private Long id;

    @NaturalId
    @ManyToOne(optional = false)
    private Building building;

    @NaturalId
    @Basic(optional = false)
    // @Column(unique = true)
    @NotNull
    private String roomNumber;

    @Basic(optional = false)
    @Range(min = 1, max = 9999, message = "Amount of seats must be between 1 and 9999")
    @NotNull
    @Valid
    private Integer seats;

    @Basic(optional = false)
    private Boolean projectorPresent;

    public Room(Building building,
        @NotNull String roomNumber,
        @Range(min = 1, max = 9999, message = "Amount of seats must be between 1 and 9999")
        @NotNull
        @Valid Integer seats,
        Boolean projectorPresent) {
        this.building = building;
        this.roomNumber = roomNumber;
        this.seats = seats;
        this.projectorPresent = projectorPresent;
    }
}
