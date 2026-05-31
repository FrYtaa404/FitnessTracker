package pl.wsb.fitnesstracker.user.api;

import org.springframework.dao.DuplicateKeyException;

public class UserKeyException extends DuplicateKeyException {
    public UserKeyException(String message) {
        super(message);
    }
}
