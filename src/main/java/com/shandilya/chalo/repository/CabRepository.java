package com.shandilya.chalo.repository;

import com.shandilya.chalo.dto.CabDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CabRepository extends JpaRepository<CabDTO, Long> {

    List<CabDTO> findByLicencePlate(String licencePlate);
}