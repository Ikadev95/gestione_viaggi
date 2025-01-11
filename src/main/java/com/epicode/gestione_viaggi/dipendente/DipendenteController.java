package com.epicode.gestione_viaggi.dipendente;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dipendenti")
public class DipendenteController {
    final private DipendenteService dipendenteService;

    @GetMapping
    public ResponseEntity<List<Dipendente>> getDipendenti(){
        List<Dipendente> dipendenti = dipendenteService.findAllDipendenti();
        return ResponseEntity.ok(dipendenti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> getDipendenteById(@PathVariable Long id){
        Dipendente dipendente = dipendenteService.findDipendenteById(id);
        return ResponseEntity.ok(dipendente);
    }

    @PostMapping
    public ResponseEntity<Dipendente> createDipendente(@RequestBody DipendenteCreaRequest request){
        return new ResponseEntity<>(dipendenteService.createDipendente(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> modifyDipendente(@PathVariable Long id, @RequestBody DipendenteCreaRequest d){
        return  ResponseEntity.ok(dipendenteService.modifyDipendente(id, d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDipendente(@PathVariable Long id){
        dipendenteService.deleteDipendente(id);
        return new ResponseEntity<>("dipendente eliminato", HttpStatus.NO_CONTENT);
    }

    @PostMapping(path="/foto/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Map> uploadFoto(@RequestPart("file") MultipartFile file, @PathVariable Long id) {
        String folder = "test";

        Map result = dipendenteService.uploadeFoto(file, folder,id);

        return ResponseEntity.ok(result);
    }
}
