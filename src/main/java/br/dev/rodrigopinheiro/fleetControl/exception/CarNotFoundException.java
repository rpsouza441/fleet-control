package br.dev.rodrigopinheiro.fleetControl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CarNotFoundException  extends FleetControlException {
    private final Long id;

    public CarNotFoundException(Long id) {
        this.id = id;

    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pd.setTitle("Car not Found");
        pd.setDetail("There is no car with id: " + id);

        return pd;
    }
}
