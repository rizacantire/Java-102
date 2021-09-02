package entities;

import business.abstracts.Insurance;

import java.util.List;

public abstract class Account {
    private User user;
    private boolean AuthenticationStatus;
    private List<Insurance> insurances;
    final void showUserInfo() {

    }

    enum AuthenticationStatus{
        SUCCESS,
        FAIL
    }
}
