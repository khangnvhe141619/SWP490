package com.workshop.carautionsystem.service;


import com.workshop.carautionsystem.model.Session;

import java.util.List;

public interface SessionService {

    //list all session ( by index page)
    public List<Session> listAllSession();
}
