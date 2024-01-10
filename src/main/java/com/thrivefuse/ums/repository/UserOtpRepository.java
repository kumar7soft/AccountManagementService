package com.thrivefuse.ums.repository;

import com.thrivefuse.ums.entity.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserOtpRepository extends JpaRepository<OtpVerification,Long> {

    Optional<OtpVerification> findByIdentifierAndType(String identifier, String type);
}
