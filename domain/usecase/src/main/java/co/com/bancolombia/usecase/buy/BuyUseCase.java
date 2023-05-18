package co.com.bancolombia.usecase.buy;

import co.com.bancolombia.model.buyProduct.BuyProduct;
import co.com.bancolombia.usecase.gateway.RepositoryCrud;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BuyUseCase {

    private final RepositoryCrud<BuyProduct, Integer> repositoryCrud;

    public Mono<BuyProduct> createBuyProduct(BuyProduct buyProduct) {
        return repositoryCrud.create(buyProduct);
    }

    public Mono<BuyProduct> findByIdBuyProduct(Integer id) {
        return repositoryCrud.findById(id);
    }

    public Flux<BuyProduct> findAllBuyProduct() {
        return repositoryCrud.findAll();
    }

    public Mono<BuyProduct> updateBuyProduct(BuyProduct buyProduct, Integer id) {
        return repositoryCrud.update(buyProduct, id);
    }

    public Mono<Void> deleteBuyProductById(Integer id) {
        return repositoryCrud.deleteById(id);
    }
}
