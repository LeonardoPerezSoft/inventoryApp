package co.com.bancolombia.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.r2dbc.helper.CustomException;
import co.com.bancolombia.usecase.product.ProductUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class ProductController {
    private final ProductUseCase productUseCase;

     @GetMapping("/{id}")
    public Mono<Product> findByIdProduct(@PathVariable("id") Integer id) {
        return productUseCase.findByIdProduct(id)
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }


    @GetMapping
    public Flux<Product> findAllProduct() {
        return productUseCase.findAllProduct()
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)));
    }


    @PostMapping
    public Mono<Product> createProduct(@RequestBody Product product) {
        return productUseCase.createProduct(product)
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST)));
    }

    @PutMapping("/{id}")
    public Mono<Product> updateProduct(@RequestBody Product product, @PathVariable("id") Integer id) {
        return productUseCase.updateProduct(product, id)
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable("id") Integer id) {
        return productUseCase.findByIdProduct(id)
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(product -> productUseCase.deleteProductById(id));
    }

    @ExceptionHandler(CustomException.class)
    public Mono<ResponseEntity<String>> handleCustomException(CustomException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String body = "Error: " + ex.getMessage();
        return Mono.just(ResponseEntity.status(status).body(body))
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())));
    }
}
