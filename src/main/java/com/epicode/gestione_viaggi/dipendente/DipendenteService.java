package com.epicode.gestione_viaggi.dipendente;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class DipendenteService {
    @Autowired
    private DipendenteRepo dipendenteRepo;

    //restituisco tutti i dipendenti
    public List<Dipendente> findAllDipendenti(){
        return dipendenteRepo.findAll();
    }

    //restituisco un dipendente cercandolo per id
    public Dipendente findDipendenteById(Long id){
        if(!dipendenteRepo.existsById(id)){
            throw new EntityNotFoundException("il dipendente non è stato trovato");
        }
        return dipendenteRepo.findById(id).get();
    }

    //inserisco un nuovo dipendente
    public Dipendente createDipendente(@Valid DipendenteCreaRequest request){
        Dipendente dipendente = new Dipendente();
        if(dipendenteRepo.existsByEmail(request.getEmail()) || dipendenteRepo.existsByUsername(request.getUsername())){
            throw new EntityExistsException("il dipendente con questa mail esiste già");
        }
        BeanUtils.copyProperties(request, dipendente);

        return dipendenteRepo.save(dipendente);
    }

    //modifico un dipendente
    public Dipendente modifyDipendente(Long id, DipendenteCreaRequest d){
        Dipendente dip = findDipendenteById(id);
        BeanUtils.copyProperties(d,dip);
        return dipendenteRepo.save(dip);
    }

    //elimino un dipendente
    public Boolean deleteDipendente(Long id){
        if(!dipendenteRepo.existsById(id)){
            throw new EntityNotFoundException("il dipendente da eliminare non è stato trovato");
        }
        dipendenteRepo.deleteById(id);
        return true;
    }
}
