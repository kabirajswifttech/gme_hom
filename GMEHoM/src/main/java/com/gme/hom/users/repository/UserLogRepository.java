package com.gme.hom.users.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gme.hom.users.models.UserLog;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Long> {

}
