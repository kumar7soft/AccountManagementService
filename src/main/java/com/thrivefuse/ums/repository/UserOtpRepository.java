package com.thrivefuse.ums.repository;

import com.thrivefuse.ums.entity.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserOtpRepository extends JpaRepository<OtpVerification,Long> {

    @Query("SELECT o FROM OtpVerification o WHERE o.identifier = :identifier AND o.type = :type ORDER BY o.createdAt DESC")
    Optional<OtpVerification> findTopByIdentifierAndTypeOrderByCreatedAtDesc(@Param("identifier") String identifier, @Param("type") String type);

}
