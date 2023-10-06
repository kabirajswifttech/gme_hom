package com.gme.hom.merchants.stockholders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gme.hom.merchants.stockholders.model.MerchantsStockholdersDetailsLog;

@Repository
public interface MerchantsStockholderDetailsLogRepository extends JpaRepository<MerchantsStockholdersDetailsLog, Long> {

}
