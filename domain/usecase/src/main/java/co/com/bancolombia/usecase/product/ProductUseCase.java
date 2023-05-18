package co.com.bancolombia.usecase.product;

import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.usecase.gateway.RepositoryCrud;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductUseCase {

    private final RepositoryCrud<Product, Integer> repositoryCrud;

    public Mono<Product> createProduct(Product product) {
        return repositoryCrud.create(product);
    }

    public Mono<Product> findByIdProduct(Integer id) {
        return repositoryCrud.findById(id);
    }

    public Flux<Product> findAllProduct() {
        return repositoryCrud.findAll();
    }

    public Mono<Product> updateProduct(Product product, Integer id) {
        return repositoryCrud.update(product, id);
    }

    public Mono<Void> deleteProductById(Integer id) {
        return repositoryCrud.deleteById(id);
    }
}
