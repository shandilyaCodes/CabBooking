package com.shandilya.chalo.repository;

import com.shandilya.chalo.dto.RiderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<RiderDTO, Long> {}