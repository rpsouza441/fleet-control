package br.dev.rodrigopinheiro.fleetControl.repository;

import br.dev.rodrigopinheiro.fleetControl.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
}
