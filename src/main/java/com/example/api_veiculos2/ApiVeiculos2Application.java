package com.example.api_veiculos2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação API de Gestão de Veículos.
 * <p>
 * Esta é a classe de inicialização da aplicação Spring Boot que gerencia
 * clientes, veículos, marcas, modelos e cores de uma lavação de veículos.
 * A aplicação fornece uma API REST completa para operações CRUD (Create, Read,
 * Update, Delete) de todos os recursos.
 * </p>
 * <p>
 * Funcionalidades principais:
 * <ul>
 * <li>Gerenciamento de marcas de veículos</li>
 * <li>Gerenciamento de modelos vinculados a marcas</li>
 * <li>Gerenciamento de cores disponíveis</li>
 * <li>Gerenciamento de clientes proprietários</li>
 * <li>Gerenciamento completo de veículos com todas as informações</li>
 * <li>Validação de dados de entrada</li>
 * <li>Persistência em banco de dados H2</li>
 * </ul>
 * </p>
 * <p>
 * -----
 * Uma aplicação java sempre contém a classe principal para funcionar
 * "public static void main(String[] args)" - Obrigatório
 * 
 * "Aprender Java é como 
 * aprender a andar de bicicleta.
 * Só que não tem guidão, 
 * as rodas são quadradas, 
 * e a bike está em chamas"
 * -----
 * 
 * </p>
 * 
 * @author Andre GB Farias @andreriffen
 * @version 1.0
 * @since 2024-12-01
 */
@SpringBootApplication
public class ApiVeiculos2Application {

    /**
     * Método principal que inicia a aplicação Spring Boot.
     * "public static void main" é o preço de entrada para participar da religião
     * Java, então aceitamos
     * a liturgia e apertamos RUN.
     *
     * @param args argumentos de linha de comando passados para a aplicação
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiVeiculos2Application.class, args);
    }

}
