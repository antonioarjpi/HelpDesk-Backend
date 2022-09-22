package com.devsimple.helpdesk.repository;

import com.devsimple.helpdesk.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, String> {
}
