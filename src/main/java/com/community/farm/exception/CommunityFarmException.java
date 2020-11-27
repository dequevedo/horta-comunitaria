package com.community.farm.exception;

public class CommunityFarmException extends RuntimeException {
//    static final long serialVersionUID = -1848914673093119416L;

    public CommunityFarmException() {
    }

    public CommunityFarmException(String s) {
        super(s);
    }

    public CommunityFarmException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommunityFarmException(Throwable cause) {
        super(cause);
    }
}