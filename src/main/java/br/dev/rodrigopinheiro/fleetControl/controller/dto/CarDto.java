package br.dev.rodrigopinheiro.fleetControl.controller.dto;

import br.dev.rodrigopinheiro.fleetControl.entity.Car;
import br.dev.rodrigopinheiro.fleetControl.entity.enums.CarAction;

public record CarDto (
        String licensePlate,
        String model,
        Long milage
) {
    public Car toCar() {
        return Car.builder()
                .milage(milage)
                .model(model)
                .licensePlate(licensePlate)
                .build();

    }

    public CarDto fromCar(Car car) {
        return new CarDto(car.getLicensePlate(),car.getModel(),car.getMilage());
    }
}
