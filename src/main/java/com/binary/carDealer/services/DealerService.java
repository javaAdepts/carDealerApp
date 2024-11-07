package com.binary.carDealer.services;

import com.binary.carDealer.entities.Dealer;

import java.util.List;

public interface DealerService {

    public List<Dealer> getAllDealers();
    public Dealer getDealerById(Long id);
    public Dealer saveDealer(Dealer dealer);
    public Dealer updateDealer(Long id, Dealer dealer);
    public void deleteDealer(Long id);

}
