package co.com.bancolombia.r2dbc.adapters;

import org.springframework.stereotype.Service;

import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.r2dbc.entities.ProductEntity;
import co.com.bancolombia.r2dbc.repositories.ProductRepository;
import co.com.bancolombia.usecase.gateway.RepositoryCrud;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductAdapter implements RepositoryCrud<Product, Integer> {

        private final ProductRepository productRepository;

        @Override
        public Mono<Product> create(Product product) {

                ProductEntity entity = ProductEntity.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .inInventory(product.getInInventory())
                                .enabled(product.isEnabled())
                                .min(product.getMin())
                                .max(product.getMax())
                                .build();

                if (entity.getInInventory() > entity.getMax() || entity.getInInventory() < entity.getMin())
                        return Mono.error(new Throwable(
                                        "No se puede insertar el dato, el inventario queda fuera del máximo o minimo"));
                else
                        return productRepository.save(entity)
                                        .map(productEntity -> Product.builder()
                                                        .id(productEntity.getId())
                                                        .name(productEntity.getName())
                                                        .inInventory(productEntity.getInInventory())
                                                        .enabled(productEntity.isEnabled())
                                                        .min(productEntity.getMin())
                                                        .max(productEntity.getMax())
                                                        .build());
        }

        @Override
        public Mono<Product> findById(Integer id) {
                return productRepository.findById(id)
                                .map(productEntity -> Product.builder()
                                                .id(productEntity.getId())
                                                .name(productEntity.getName())
                                                .inInventory(productEntity.getInInventory())
                                                .enabled(productEntity.isEnabled())
                                                .min(productEntity.getMin())
                                                .max(productEntity.getMax())
                                                .build());
        }

        @Override
        public Flux<Product> findAll() {
                return productRepository.findAll()
                                .map(productEntity -> Product.builder()
                                                .id(productEntity.getId())
                                                .name(productEntity.getName())
                                                .inInventory(productEntity.getInInventory())
                                                .enabled(productEntity.isEnabled())
                                                .min(productEntity.getMin())
                                                .max(productEntity.getMax())
                                                .build());
        }

        @Override
        public Mono<Product> update(Product product, Integer id) {
                return productRepository.findById(id)
                                .flatMap(productData -> {
                                        ProductEntity productNew = productData;

                                        productNew.setName(product.getName());
                                        productNew.setInInventory(product.getInInventory());
                                        productNew.setEnabled(product.isEnabled());
                                        productNew.setMin(product.getMin());
                                        productNew.setMax(product.getMax());

                                        if (productNew.getInInventory() > productNew.getMax()
                                                        || productNew.getInInventory() < productNew.getMin())
                                                return Mono.error(new Throwable(
                                                                "No se puede hacer la actualización, valores de máximo o minimo no permitidos"));
                                        else
                                                return productRepository.save(productNew);

                                }).map(
                                                productEntity -> Product.builder()
                                                                .id(productEntity.getId())
                                                                .name(productEntity.getName())
                                                                .inInventory(productEntity.getInInventory())
                                                                .enabled(productEntity.isEnabled())
                                                                .min(productEntity.getMin())
                                                                .max(productEntity.getMax())
                                                                .build());
        }

        @Override
        public Mono<Void> deleteById(Integer id) {
                return productRepository.deleteById(id);
        }



}
