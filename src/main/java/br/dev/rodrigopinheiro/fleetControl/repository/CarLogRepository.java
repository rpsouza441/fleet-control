package br.dev.rodrigopinheiro.fleetControl.repository;

import br.dev.rodrigopinheiro.fleetControl.entity.CarLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarLogRepository extends JpaRepository<CarLog, Long> {
}
