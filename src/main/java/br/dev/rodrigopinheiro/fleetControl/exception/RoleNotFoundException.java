package br.dev.rodrigopinheiro.fleetControl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class RoleNotFoundException extends FleetControlException {
    private final Long id;

    public RoleNotFoundException(Long id) {
        this.id = id;

    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pd.setTitle("Role not Found");
        pd.setDetail("There is no role with id: " + id);

        return pd;
    }
}