package com.example.central.service;

import com.example.central.config.RabbitConfig;
import com.example.central.dto.CosechaReq;
import com.example.central.events.NuevaCosechaEvent;
import com.example.central.model.Agricultor;
import com.example.central.model.Cosecha;
import com.example.central.repository.AgricultorRepository;
import com.example.central.repository.CosechaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.example.central.config.RabbitConfig.EXCHANGE;
import static com.example.central.config.RabbitConfig.ROUTING_KEY;

@Service
@RequiredArgsConstructor
public class CosechaService {

    private final AgricultorRepository agricRepo;
    private final CosechaRepository coseRepo;
    private final AmqpTemplate amqp;

    @Transactional
    public Cosecha registrar(CosechaReq req){
        Agricultor ag = agricRepo.findById(req.agricultorId())
                .orElseThrow(() -> new IllegalArgumentException("Agricultor no existe"));

        Cosecha c = Cosecha.builder()
                .agricultor(ag)
                .producto(req.producto())
                .toneladas(BigDecimal.valueOf(req.toneladas()))
                .estado(Cosecha.Estado.REGISTRADA)
                .build();

        c = coseRepo.save(c);

        // Publicar evento (misma routing key → llega a inventario y facturación)
        NuevaCosechaEvent ev = new NuevaCosechaEvent(c.getId(), c.getProducto(), c.getToneladas());
        amqp.convertAndSend(EXCHANGE, ROUTING_KEY, ev);

        return c;
    }

    @Transactional
    public Cosecha actualizarEstado(Long id, Cosecha.Estado estado){
        Cosecha c = coseRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cosecha no existe"));
        c.setEstado(estado);
        return c;
    }
}
