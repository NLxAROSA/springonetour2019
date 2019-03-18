package io.pivotal.workshop.workshopfortuneservice;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Fortune {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String text;
}
