package br.dev.rodrigopinheiro.fleetControl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CarLogNotFoundException extends FleetControlException {
    private final Long id;


    public CarLogNotFoundException(Long id) {
        this.id = id;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pd.setTitle("Car Log not Found");
        pd.setDetail("There is no Log with id: " + id);

        return pd;
    }
}
