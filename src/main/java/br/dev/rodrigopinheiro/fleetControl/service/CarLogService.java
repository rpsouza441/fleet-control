package br.dev.rodrigopinheiro.fleetControl.service;

import br.dev.rodrigopinheiro.fleetControl.controller.dto.CarLogDto;
import br.dev.rodrigopinheiro.fleetControl.controller.dto.CarLogDto;
import br.dev.rodrigopinheiro.fleetControl.entity.Car;
import br.dev.rodrigopinheiro.fleetControl.entity.CarLog;
import br.dev.rodrigopinheiro.fleetControl.exception.CarLogNotFoundException;
import br.dev.rodrigopinheiro.fleetControl.exception.CarNotFoundException;
import br.dev.rodrigopinheiro.fleetControl.exception.FleetControlException;
import br.dev.rodrigopinheiro.fleetControl.repository.CarLogRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarLogService {

    private final CarLogRepository carLogRepository;

    public CarLogService(CarLogRepository carLogRepository) {
        this.carLogRepository = carLogRepository;
    }

    public CarLogDto create(CarLogDto carLogDto) {
        var car = carLogDto.toCarLog();
        var carLogCreated = carLogRepository.save(car);
        return new CarLogDto( carLogCreated.getCar().getId(), carLogCreated.getCarAction(), carLogCreated.getMilage(),
                carLogCreated.getDateTime());
    }

    public List<CarLogDto> findAll() {
        List<CarLog> carLogs = carLogRepository.findAll();
        return carLogs.stream()
                .map(carLog -> new CarLogDto(carLog.getCar().getId(), carLog.getCarAction(), carLog.getMilage(),
                        carLog.getDateTime()))
                .collect(Collectors.toList());
    }

    public CarLogDto findById(Long id) {
        var carLog = carLogRepository.findById(id).orElseThrow(() -> new CarLogNotFoundException(id));

        return new CarLogDto(carLog.getCar().getId(), carLog.getCarAction(), carLog.getMilage(),
                carLog.getDateTime());
    }

    public void delete(Long id) {
        try {
            carLogRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CarLogNotFoundException(id);
        } catch (Exception e) {
            throw new FleetControlException();
        }
    }

    public CarLogDto update(Long id, CarLogDto carLogDto) {
        var updatedCar = carLogRepository.findById(id).map(existingCarLog -> {
            existingCarLog.setMilage(carLogDto.mileage());
            existingCarLog.setDateTime(carLogDto.dateTime());
            existingCarLog.setCarAction(carLogDto.action());
            return carLogRepository.save(existingCarLog);
        }).orElseThrow(() -> new CarNotFoundException(id));
        return carLogDto.fromCar(updatedCar);
    }

}
