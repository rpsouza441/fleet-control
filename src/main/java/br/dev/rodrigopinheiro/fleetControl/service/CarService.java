package br.dev.rodrigopinheiro.fleetControl.service;

import br.dev.rodrigopinheiro.fleetControl.controller.dto.CarDto;
import br.dev.rodrigopinheiro.fleetControl.entity.Car;
import br.dev.rodrigopinheiro.fleetControl.exception.FleetControlException;
import br.dev.rodrigopinheiro.fleetControl.exception.CarNotFoundException;
import br.dev.rodrigopinheiro.fleetControl.repository.CarRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarDto create(CarDto carDto) {
        var car = carDto.toCar();
        var carCreated = carRepository.save(car);
        return new CarDto( carCreated.getLicensePlate(), carCreated.getLicensePlate(),carCreated.getMilage());
    }

    public List<CarDto> findAll() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(car -> new CarDto(car.getLicensePlate(), car.getLicensePlate(),car.getMilage()))
                .collect(Collectors.toList());
    }

    public CarDto findById(Long id) {
        var car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));

        return new CarDto(car.getLicensePlate(), car.getLicensePlate(),car.getMilage());
    }

    public void delete(Long id) {
        try {
            carRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CarNotFoundException(id);
        } catch (Exception e) {
            throw new FleetControlException();
        }
    }

    public CarDto update(Long id, CarDto carDto) {
        var updatedCar = carRepository.findById(id).map(existingCar -> {
            existingCar.setMilage(carDto.milage());
            existingCar.setModel(carDto.model());
            existingCar.setLicensePlate(carDto.licensePlate());
            return carRepository.save(existingCar);
        }).orElseThrow(() -> new CarNotFoundException(id));
        return carDto.fromCar(updatedCar);
    }

}
