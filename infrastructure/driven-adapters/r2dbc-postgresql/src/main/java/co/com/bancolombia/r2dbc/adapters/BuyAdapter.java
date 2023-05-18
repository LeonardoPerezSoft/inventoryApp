package co.com.bancolombia.r2dbc.adapters;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import co.com.bancolombia.model.product.Product;
import org.springframework.stereotype.Service;

import co.com.bancolombia.model.buyProduct.BuyProduct;
import co.com.bancolombia.model.productQuantity.ProductQuantity;
import co.com.bancolombia.r2dbc.entities.BuyEntity;
import co.com.bancolombia.r2dbc.entities.DetailBuyEntity;
import co.com.bancolombia.r2dbc.repositories.BuyRepository;
import co.com.bancolombia.r2dbc.repositories.DetailBuyRepository;
import co.com.bancolombia.usecase.gateway.RepositoryCrud;
import co.com.bancolombia.usecase.product.ProductUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BuyAdapter implements RepositoryCrud<BuyProduct, Integer> {

    private final ProductUseCase productUseCase;
    private final BuyRepository buyRepository;
    private final DetailBuyRepository detailBuyRepository;

    @Override
    public Mono<BuyProduct> create(BuyProduct buyProduct) {
        List<ProductQuantity> listProductsBuy = buyProduct.getProducts();
        BuyEntity entity = BuyEntity.builder()
                .id(buyProduct.getId())
                .dni(buyProduct.getDni())
                .date(LocalDateTime.now())
                .idType(buyProduct.getIdType())
                .clientName(buyProduct.getClientName())
                 .build();

        Mono<BuyProduct> nt = buyRepository.save(entity)
                .flatMap(buyEntity -> {
                    updateProduct(listProductsBuy, buyEntity.getId());
                    BuyProduct n = new BuyProduct(buyEntity.getId(), buyEntity.getDni(), buyEntity.getDate(),
                            buyEntity.getIdType(), buyEntity.getClientName(), listProductsBuy);
                    return Mono.just(n);
                });
        return nt;
    }

    @Override
    public Mono<BuyProduct> findById(Integer id)  {
        return buyRepository.findById(id)
                .flatMap(buy -> {
                    List<ProductQuantity> buys = new ArrayList<>();

                    detailBuyRepository.findByBuy(buy.getId())
                            .flatMap(detailBuy -> {
                                ProductQuantity productQuantity = new ProductQuantity(detailBuy.getProduct(), detailBuy.getQuantity());
                                buys.add(productQuantity);
                                return Mono.just(productQuantity);
                            });
                    BuyProduct buyProduct = new BuyProduct(buy.getId(), buy.getDni(), buy.getDate(), buy.getIdType(),
                            buy.getClientName(), buys);

                    return Mono.just(buyProduct);
                });
    }

    @Override
    public Flux<BuyProduct> findAll() {
        return buyRepository.findAll()
                .map(buyEntity -> BuyProduct.builder()
                        .id(buyEntity.getId())
                        .dni(buyEntity.getDni())
                        .idType(buyEntity.getIdType())
                        .date(buyEntity.getDate())
                        .clientName(buyEntity.getClientName())
                        //.products(ProductQuantity.builder().quantity(buyEntity.))
                            .build());
    }

    @Override
    public Mono<BuyProduct> update(BuyProduct buyProduct, Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }



    private void updateProduct(List<ProductQuantity> listProductsBuy, Integer idBuy) {

        for (ProductQuantity product : listProductsBuy) {
            Integer id = product.getIdProduct();
            int quantity = product.getQuantity();
              productUseCase.findByIdProduct(id)
                    .flatMap(producto -> {
                        int min = producto.getMin();
                        if ((producto.getInInventory() - quantity) >= min) {
                            producto.setInInventory(producto.getInInventory() - quantity);
                            return productUseCase.updateProduct(producto, id).doOnNext(pr ->{
                            DetailBuyEntity detailBuyEntity = new DetailBuyEntity(null, producto.getId(), idBuy, product.getQuantity());
                            detailBuyRepository.save(detailBuyEntity).subscribe();
                        });
                             } else
                            return Mono.error(new Throwable("El inventario esta por debajo del limite minimo"));
                    }).subscribe();
        }
    }

}
