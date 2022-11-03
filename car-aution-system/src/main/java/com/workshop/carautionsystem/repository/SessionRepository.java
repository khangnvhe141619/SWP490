package com.workshop.carautionsystem.repository;

import com.workshop.carautionsystem.model.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session,Integer>{
}
