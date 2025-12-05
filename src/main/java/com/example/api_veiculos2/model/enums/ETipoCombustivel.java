package com.example.api_veiculos2.model.enums;

/**
 * Enumeração que representa os tipos de combustível de veículos.
 * <p>
 * Define os tipos de combustível disponíveis para motores
 * de veículos cadastrados no sistema.
 * </p>
 * 
 * @author Andre GB Farias @andreriffen
 * @version 1.0
 * @since 2025-12-01
 */
public enum ETipoCombustivel {
    /** Motor movido a gasolina */
    GASOLINA,

    /** Motor movido a etanol */
    ETANOL,

    /** Motor flex (gasolina e etanol) */
    FLEX,

    /** Motor movido a diesel */
    DIESEL,

    /** Motor movido a Gás Natural Veicular */
    GNV,

    /** Outros tipos de combustível */
    OUTRO
}
