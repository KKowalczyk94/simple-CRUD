package com.geocode.repository;

import com.geocode.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Override
    Optional<Location> findById(Long aLong);
}
