package com.epicode.gestione_viaggi.prenotazione;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@AllArgsConstructor
@Validated
public class PrenotazioneService {
    final private PrenotazioneRepo prenotazioneRepo;


}
