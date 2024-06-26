package br.dev.rodrigopinheiro.fleetControl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotFoundException extends FleetControlException {
    private final Long userId;


    public UserNotFoundException(Long userId) {
        this.userId = userId;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pd.setTitle("User not Found");
        pd.setDetail("There is no User with id: " + userId);

        return pd;
    }
}
