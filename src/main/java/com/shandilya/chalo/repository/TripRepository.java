package com.shandilya.chalo.repository;

import com.shandilya.chalo.dto.TripDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<TripDTO, Long> {
    List<TripDTO> findByRiderUniqueId(String riderUniqueId);
}