package com.example.central.api;

import com.example.central.dto.CosechaReq;
import com.example.central.model.Agricultor;
import com.example.central.model.Cosecha;
import com.example.central.repository.AgricultorRepository;
import com.example.central.repository.CosechaRepository;
import com.example.central.service.CosechaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CentralController {

    private final AgricultorRepository agricRepo;
    private final CosechaRepository coseRepo;
    private final CosechaService service;

    // Agricultores
    @PostMapping("/agricultores")
    public Agricultor crearAgricultor(@RequestBody @Valid Agricultor a){ return agricRepo.save(a); }

    @GetMapping("/agricultores")
    public List<Agricultor> listarAgricultores(){ return agricRepo.findAll(); }

    // Cosechas
    @PostMapping("/cosechas")
    public Cosecha crearCosecha(@RequestBody @Valid CosechaReq req){ return service.registrar(req); }

    @GetMapping("/cosechas")
    public List<Cosecha> listarCosechas(){ return coseRepo.findAll(); }

    @PutMapping("/cosechas/{id}/estado/{estado}")
    public Cosecha estado(@PathVariable Long id, @PathVariable Cosecha.Estado estado){
        return service.actualizarEstado(id, estado);
    }
}
