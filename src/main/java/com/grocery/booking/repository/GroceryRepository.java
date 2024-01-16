package com.grocery.booking.repository;

import com.grocery.booking.dto.Grocery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface GroceryRepository extends JpaRepository<Grocery, Long> {

    List<Grocery> findAllByQuantityGreaterThan(int qty);
}
