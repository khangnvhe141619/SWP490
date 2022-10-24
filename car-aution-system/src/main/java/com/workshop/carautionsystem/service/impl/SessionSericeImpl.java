package com.workshop.carautionsystem.service.impl;

import com.workshop.carautionsystem.model.Session;
import com.workshop.carautionsystem.repository.SessionRepository;
import com.workshop.carautionsystem.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionSericeImpl implements SessionService {

    @Autowired
    private SessionRepository repo;

    @Override
    public List<Session> listAllSession() {
        return (List<Session>) repo.findAll() ;
    }
}
