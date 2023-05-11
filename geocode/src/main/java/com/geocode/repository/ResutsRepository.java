package com.geocode.repository;

import com.geocode.model.Results;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResutsRepository extends JpaRepository<Results, Long> {
}
