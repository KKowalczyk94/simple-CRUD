package com.geocode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(name = "seq_location", sequenceName = "seq_location", allocationSize = 1)
public class GeoLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_location")
    @JsonIgnore
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Results[] results;
}
