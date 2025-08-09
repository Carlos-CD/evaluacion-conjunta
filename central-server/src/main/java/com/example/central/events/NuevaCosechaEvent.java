package com.example.central.events;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NuevaCosechaEvent {

    private String eventId;
    private String eventType;
    private String timestamp;
    private Payload payload;

    public static class Payload {
        private String cosechaId;         // Long serializado como String
        private String producto;
        private BigDecimal toneladas;
        private String[] requiereInsumos;

        public Payload() {}

        public String getCosechaId() { return cosechaId; }
        public void setCosechaId(String cosechaId) { this.cosechaId = cosechaId; }

        public String getProducto() { return producto; }
        public void setProducto(String producto) { this.producto = producto; }

        public BigDecimal getToneladas() { return toneladas; }
        public void setToneladas(BigDecimal toneladas) { this.toneladas = toneladas; }

        public String[] getRequiereInsumos() { return requiereInsumos; }
        public void setRequiereInsumos(String[] requiereInsumos) { this.requiereInsumos = requiereInsumos; }
    }

    public NuevaCosechaEvent() {}

    public NuevaCosechaEvent(Long cosechaId, String producto, BigDecimal toneladas) {
        this.eventId = UUID.randomUUID().toString();
        this.eventType = "nueva_cosecha";
        this.timestamp = Instant.now().toString();
        this.payload = new Payload();
        this.payload.setCosechaId(String.valueOf(cosechaId));
        this.payload.setProducto(producto);
        this.payload.setToneladas(toneladas);
        this.payload.setRequiereInsumos(new String[]{"Semilla Arroz L-23", "Fertilizante N-PK"});
    }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public Payload getPayload() { return payload; }
    public void setPayload(Payload payload) { this.payload = payload; }
}
