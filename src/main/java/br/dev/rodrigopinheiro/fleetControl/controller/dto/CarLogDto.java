package br.dev.rodrigopinheiro.fleetControl.controller.dto;

import br.dev.rodrigopinheiro.fleetControl.entity.Car;
import br.dev.rodrigopinheiro.fleetControl.entity.CarLog;
import br.dev.rodrigopinheiro.fleetControl.entity.enums.CarAction;

import java.time.LocalDateTime;

public record CarLogDto(
         Long carId,
         CarAction action,
         Long mileage,
         LocalDateTime dateTime
) {
    public CarLog toCarLog() {
        var car = new Car();
        car.setId(carId);
        return CarLog.builder()
                .car(car)
                .carAction(action)
                .milage(mileage)
                .dateTime(dateTime)
                .build();

    }

    public CarLogDto fromCar(CarLog carLog) {
        return new CarLogDto(
                carLog.getCar().getId(),
                carLog.getCarAction(),
                carLog.getMilage(),
                carLog.getDateTime()
        );
    }
}
