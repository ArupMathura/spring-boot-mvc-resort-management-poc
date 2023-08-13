package com.poc.resortmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.resortmanagementsystem.entity.Resort;
@Repository
public interface ResortRepository extends JpaRepository<Resort, Long> {
    boolean existsByName(String name);
}
