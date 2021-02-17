package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Product;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
