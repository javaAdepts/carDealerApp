package com.binary.carDealer.services;

import com.binary.carDealer.entities.Dealer;
import com.binary.carDealer.repositories.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealerServiceImpl implements DealerService{

    @Autowired
    DealerRepository dealerRepository;;

    @Override
    public List<Dealer> getAllDealers() {
        return dealerRepository.findAll();
    }

    @Override
    public Dealer getDealerById(Long id) {
        Optional<Dealer> optionalDealer = dealerRepository.findById(id);
        if(optionalDealer.isPresent()){
            return optionalDealer.get();
        }
        return null;
    }

    @Override
    public Dealer saveDealer(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    @Override
    public Dealer updateDealer(Long id, Dealer dealer) {
        Optional<Dealer> optionalDealer = dealerRepository.findById(id);
        if(optionalDealer.isPresent()){
            Dealer updatedDealer = optionalDealer.get();
            if(dealer.getDealerName() != null)
                updatedDealer.setDealerName(dealer.getDealerName());
            return  dealerRepository.save(updatedDealer);
        }
        return null;
    }

    @Override
    public void deleteDealer(Long id) {
       dealerRepository.deleteById(id);
    }
}
