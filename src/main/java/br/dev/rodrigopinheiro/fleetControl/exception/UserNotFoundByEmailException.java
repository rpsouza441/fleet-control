package br.dev.rodrigopinheiro.fleetControl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotFoundByEmailException extends FleetControlException {
    private final String email;


    public UserNotFoundByEmailException( String email) {
        this.email = email;

    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pd.setTitle("User not Found");
        pd.setDetail("There is no User with email: " + email);

        return pd;
    }
}
