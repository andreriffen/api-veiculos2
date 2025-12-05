package com.example.api_veiculos2.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * =============================================================================
 * O que essa classe faz?
 * 
 * Centraliza a configuração do {@link ModelMapper} usada em toda a aplicação.
 * Quando outra classe injeta um ModelMapper, ela receberá exatamente esta versão sob medida.
 *
 * =============================================================================
 * 
 * Pra quê serve? Porque usamos essa classe?
 * 
 * 1. O enunciado exige uso de DTOs + ModelMapper, então criamos um bean aqui.
 * 2. Personalizamos estratégia e acesso aos campos para evitar mapeamentos errados.
 * 
 * =============================================================================
 */
@Configuration
public class ModelMapperConfig {

    /**
     * =====================================
     * O QUE É UM BEAN???
     * Bean ModelMapper: o "tradutor oficial" da aplicação
     * 
     * Este método cria UMA ÚNICA instância de ModelMapper para a APLICAÇÃO INTEIRA.
     * Pensa assim: é como ter um dicionário oficial da empresa. Todo mundo usa
     * o mesmo dicionário, ninguém inventa traduções malucas por conta própria.
     * 
     * O @Bean é tipo um carimbo que diz ao Spring: "Ó, quando alguém precisar
     * de um ModelMapper, me chama que eu forneço este aqui, devidamente configurado".
     * 
     * Por que não deixar cada classe criar seu próprio ModelMapper?
     * Seria tipo cada funcionário ter seu próprio dicionário português-inglês.
     * Um traduz "legal" como "cool", outro como "lawful". Vira bagunça! 🤷
     * 
     * Aqui? Uma instância, uma configuração, zero surpresas.
     */
    @Bean
    public ModelMapper modelMapper() {
        // Instancia o componente responsável por converter entidades <-> DTOs.
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                // STRICT garante que só campos com nomes idênticos serão mapeados (evita surpresas).
                .setMatchingStrategy(MatchingStrategies.STRICT)
                // Habilita o mapeamento direto entre campos (sem precisar de getters/setters idênticos).
                .setFieldMatchingEnabled(true)
                // Autoriza o mapper a inspecionar campos privados via reflexão.
                .setFieldAccessLevel(AccessLevel.PRIVATE);
        return mapper;
    }
}
