package de.dlh.lhind.exercise.roommgmt.model;

import java.time.Instant;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Meeting")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    private Room room;

    @Basic(optional = false)
    private String topic;

    @Basic(optional = false)
    private Instant start;

    @Basic(optional = false)
    private Instant end;

    public Meeting(@NotNull Room room, String topic, Instant start, Instant end) {
        this.room = room;
        this.topic = topic;
        this.start = start;
        this.end = end;
    }

}
