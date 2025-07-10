// repository/LandEntryRepository.java
package com.land.management.repository;

import com.land.management.model.LandEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandEntryRepository extends JpaRepository<LandEntry, Long> {
}
