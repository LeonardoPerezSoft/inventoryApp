package co.com.bancolombia.api.controllers;

import co.com.bancolombia.model.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import co.com.bancolombia.model.buyProduct.BuyProduct;
import co.com.bancolombia.r2dbc.helper.CustomException;
import co.com.bancolombia.usecase.buy.BuyUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/buys")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class BuyController {
    private final BuyUseCase buyUseCase;

    @PostMapping
    public Mono<BuyProduct> createBuy(@RequestBody BuyProduct buyProduct) {
        return buyUseCase.createBuyProduct(buyProduct)
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST)));
    }

    @GetMapping
    public Flux<BuyProduct> findAllBuys() {
        return buyUseCase.findAllBuyProduct()
                 .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)));
    }


    @GetMapping("/{id}")
    public Mono<BuyProduct> findByIdBuy(@PathVariable("id") Integer id) {
        return buyUseCase.findByIdBuyProduct(id)
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @ExceptionHandler(CustomException.class)
    public Mono<ResponseEntity<String>> handleCustomException(CustomException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String body = "Error: " + ex.getMessage();
        return Mono.just(ResponseEntity.status(status).body(body))
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())));
    }
}
