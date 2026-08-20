package com.neyzimho.user.infrastructure.repositories;

import com.neyzimho.user.infrastructure.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
