package gr.aueb.cf.project.repository;

import gr.aueb.cf.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
