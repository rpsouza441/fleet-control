package br.dev.rodrigopinheiro.fleetControl.controller;

import br.dev.rodrigopinheiro.fleetControl.controller.dto.CarDto;
import br.dev.rodrigopinheiro.fleetControl.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/create")
    public ResponseEntity<CarDto> createCarLog(@RequestBody CarDto carDto) {
      return ResponseEntity.ok(carService.create(carDto));
    }
}
