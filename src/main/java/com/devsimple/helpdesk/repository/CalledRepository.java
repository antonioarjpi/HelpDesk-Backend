package com.devsimple.helpdesk.repository;

import com.devsimple.helpdesk.model.Called;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalledRepository extends JpaRepository<Called, String> {
}
