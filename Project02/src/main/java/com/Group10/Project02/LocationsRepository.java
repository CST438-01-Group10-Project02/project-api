package com.Group10.Project02;

import com.Group10.Project02.Entities.Locations;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Peter Gloag
 * @since 2/25/2026
 */
public interface LocationsRepository extends JpaRepository<Locations, Long> {
}
