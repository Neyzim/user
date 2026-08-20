package com.neyzimho.user.infrastructure.repositories;

import com.neyzimho.user.infrastructure.entities.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {
}
