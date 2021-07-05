package test.rest.api.repository;

import org.springframework.data.repository.CrudRepository;
import test.rest.api.model.ProductModel;

public interface ProductRepository extends CrudRepository<ProductModel, Integer> {
}
