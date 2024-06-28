package br.dev.rodrigopinheiro.fleetControl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserEmailAlreadyExistException extends FleetControlException {
    private final String email;


    public UserEmailAlreadyExistException( String email) {
        this.email = email;

    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pd.setTitle("User Already Exist");
        pd.setDetail("There is a User with email: " + email);

        return pd;
    }
}
