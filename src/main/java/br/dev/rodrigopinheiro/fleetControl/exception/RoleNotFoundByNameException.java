package br.dev.rodrigopinheiro.fleetControl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class RoleNotFoundByNameException extends FleetControlException {
    private final String name;

    public RoleNotFoundByNameException(String name) {
        this.name = name;

    }
    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pd.setTitle("Role not Found");
        pd.setDetail("There is no role with name: " + name);

        return pd;
    }
}
