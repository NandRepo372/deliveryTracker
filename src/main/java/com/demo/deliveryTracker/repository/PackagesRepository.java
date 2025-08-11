package com.demo.deliveryTracker.repository;

import com.demo.deliveryTracker.entity.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackagesRepository extends JpaRepository<Packages,Long> {

}
