package co.com.bancolombia.r2dbc.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.com.bancolombia.r2dbc.entities.BuyEntity;

public interface BuyRepository extends ReactiveCrudRepository<BuyEntity, Integer> {

}
