package com.example.liveasy.logistics.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.liveasy.logistics.entities.LoadLogistics;

@Repository
public interface LoadLogisticsDAO extends JpaRepository<LoadLogistics, Integer>{

}
