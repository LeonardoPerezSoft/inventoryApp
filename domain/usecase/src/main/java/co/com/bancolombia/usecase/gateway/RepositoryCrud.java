package co.com.bancolombia.usecase.gateway;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RepositoryCrud<T, ID> {
    public Mono<T> create(T T);

    public Mono<T> findById(ID id);

    public Flux<T> findAll();

    public Mono<T> update(T T, ID id);

    public Mono<Void> deleteById(ID id);

}
