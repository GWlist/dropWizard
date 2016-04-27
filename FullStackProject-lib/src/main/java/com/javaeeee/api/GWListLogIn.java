package com.javaeeee.api;

import com.javaeeee.dao.LogInDAO;
import com.javaeeee.pojos.Session;

public class GWListLogIn {

    private LogInDAO logInDAO;

    public GWListLogIn(LogInDAO dao) {
        this.logInDAO = dao;
    }

    public Session logIn(String username){
        return logInDAO.logIn(username);
    }

}
