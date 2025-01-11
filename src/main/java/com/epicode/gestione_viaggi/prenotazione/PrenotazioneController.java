package com.epicode.gestione_viaggi.prenotazione;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    final private PrenotazioneService prenotazioneService;

    @PostMapping
    public ResponseEntity<Prenotazione> savePrenotazione(PrenotazioneCreaRequest p){
        return new ResponseEntity<>(prenotazioneService.savePrenotazione(p), HttpStatus.CREATED);
    }
}
