package com.energy.users.exception;

public class EnergyUsersException extends RuntimeException {
//    static final long serialVersionUID = -1848914673093119416L;

    public EnergyUsersException() {
    }

    public EnergyUsersException(String s) {
        super(s);
    }

    public EnergyUsersException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnergyUsersException(Throwable cause) {
        super(cause);
    }
}