package com.epicode.gestione_viaggi.prenotazione;

import com.epicode.gestione_viaggi.dipendente.Dipendente;
import com.epicode.gestione_viaggi.dipendente.DipendenteRepo;
import com.epicode.gestione_viaggi.dipendente.DipendenteService;
import com.epicode.gestione_viaggi.viaggio.Viaggio;
import com.epicode.gestione_viaggi.viaggio.ViaggioService;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@Validated
public class PrenotazioneService {
    final private PrenotazioneRepo prenotazioneRepo;
    final private DipendenteService dipendenteService;
    final private ViaggioService viaggioService;

    //salvo una prenotazione
    public Prenotazione savePrenotazione(@Valid PrenotazioneCreaRequest prenotazioneRequest) {

        Viaggio viaggio = viaggioService.getViaggioById(prenotazioneRequest.getIdViaggio());
        Dipendente dipendente = dipendenteService.findDipendenteById(prenotazioneRequest.getIdDipendente());

        Long result = prenotazioneRepo.countPrenotazioniByDipendenteAndData(dipendente, viaggio.getData());
        if (result > 0) {
            throw new EntityExistsException("Esiste già una prenotazione di questo utente a questa data.");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataPrenotazione(LocalDate.now());
        prenotazione.setNote(prenotazioneRequest.getNote());


        return prenotazioneRepo.save(prenotazione);
    }
}
