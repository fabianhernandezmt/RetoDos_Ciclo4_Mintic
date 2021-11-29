package com.example.Ciclo4_Mintic.service;

import java.util.List;
import java.util.Optional;

import com.example.Ciclo4_Mintic.model.Clothe;
import com.example.Ciclo4_Mintic.repository.ClotheRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @autor fabianhr
 */
@Service
public class ClotheService {

    @Autowired
    private ClotheRepository clotheRepository;

    public List<Clothe> getAll(){
        return clotheRepository.getAll();
    }

    public Optional<Clothe> getById(String reference){
        return clotheRepository.getById(reference);
    }

    public Clothe registrar(Clothe clothe){
        if(clothe.getReference()== null)
            return clothe;
            Optional<Clothe> existeClothe = getById(clothe.getReference());

            if (existeClothe.isPresent())
            return clothe;
        
            return clotheRepository.save(clothe);

    }

    public Clothe update(Clothe clothe) {
        
        if (clothe.getReference() == null)
          return clothe;
      
      Optional<Clothe> existeClothe = clotheRepository.getById(clothe.getReference());
      
      if (existeClothe.isPresent() == false)
          return null;

          clotheRepository.save(clothe);
          return clothe;
      }


    public boolean deleteClothe(String reference){
        Boolean aBoolean = getById(reference).map(clothe ->{
            clotheRepository.delete(clothe);
            return true;    
        }).orElse(false);
        return aBoolean;
    }
    
}
