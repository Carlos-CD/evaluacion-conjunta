package com.example.central.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "cosechas";
    public static final String ROUTING_KEY = "nueva";
    public static final String QUEUE_INV = "cola_inventario";
    public static final String QUEUE_FAC = "cola_facturacion";

    @Bean
    public DirectExchange cosechasExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    public Queue queueInventario() {
        return QueueBuilder.durable(QUEUE_INV).build();
    }

    @Bean
    public Queue queueFacturacion() {
        return QueueBuilder.durable(QUEUE_FAC).build();
    }

    @Bean
    public Binding bindingInventario(@Qualifier("queueInventario") Queue q, DirectExchange ex) {
        return BindingBuilder.bind(q).to(ex).with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingFacturacion(@Qualifier("queueFacturacion") Queue q, DirectExchange ex) {
        return BindingBuilder.bind(q).to(ex).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2Converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory cf, Jackson2JsonMessageConverter conv) {
        RabbitTemplate tpl = new RabbitTemplate(cf);
        tpl.setMessageConverter(conv);
        return tpl;
    }
}
