package co.com.bancolombia.r2dbc.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import co.com.bancolombia.r2dbc.entities.DetailBuyEntity;
import reactor.core.publisher.Flux;

public interface DetailBuyRepository extends ReactiveCrudRepository<DetailBuyEntity, Integer> {


    Flux<DetailBuyEntity> findByBuy(Integer buy);
    
}
