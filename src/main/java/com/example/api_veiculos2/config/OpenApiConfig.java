package com.example.api_veiculos2.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * =============================================================================
 * ATENÇÃO PEGUIÇOSOS, TDAHs e DISTRAÍDOS:
 * 
 * O QUE ESSA CLASSE FAZ? (versão que não vai te fazer dormir)
 * 
 * Sabe quando você vai num restaurante chique e tem aquele menu enorme e bonito
 * com foto de cada prato, ingredientes, história do chef e até poesia?
 * 
 * Pois é. Essa classe é o "menu chique" da nossa API.
 * 
 * Aqui a gente usa o Springdoc OpenAPI (exigido no enunciado, tipo aquela
 * matéria obrigatória que você não pode pular) pra criar uma documentação
 * LINDA no Swagger UI.
 * 
 * Resultado? Em vez de ficar testando sua API pelo Postman feito um maluco,
 * você abre o /swagger-ui.html e tem uma interface gráfica bonitinha onde dá
 * pra ver TODOS os endpoints, testar na hora, e ainda parecer profissional
 * quando mostrar pra alguém. 📚✨
 *
 * =============================================================================
 * 
 * 🤔 POR QUE CRIAMOS ISSO?
 * 
 * - Sem o bean {@link OpenAPI}, o Swagger ia aparecer com aquele nome genérico
 *   tipo "Api Documentation" (CHAAATO...). Aqui a gente customiza com nome do
 *   projeto, seu nome (pra ganhar crédito, óbvio), licença MIT (que é tipo o
 *   "fique à vontade" do mundo open source) e até link pro GitHub.
 * 
 * - O {@link GroupedOpenApi} é tipo aquele filtro do Instagram: ele deixa
 *   aparecer SÓ os endpoints que importam (/clientes, /veiculos, etc) e 
 *   esconde o resto da bagunça. Porque ninguém quer ver endpoint de
 *   "actuator" poluindo a documentação, né? 🙈
 * 
 * TL;DR: Essa classe faz sua API parecer profissional. Use com orgulho.
 * =============================================================================
 */
@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI apiVeiculosOpenAPI() {
                // Constrói o objeto OpenAPI com todas as informações humanas da API.
                return new OpenAPI()
                                .info(new Info()
                                                .title("API Veículos 2")
                                                .description("API avançada para gestão de clientes, veículos, cores, marcas e modelos")
                                                .version("v2")
                                                .contact(new Contact()
                                                                .name("Andre Guilherme Barreto de Farias")
                                                                .url("https://github.com/andreriffen"))
                                                .license(new License()
                                                                .name("MIT")
                                                                .url("https://opensource.org/licenses/MIT")))
                                .externalDocs(new ExternalDocumentation()
                                                .description("Enunciado avançado")
                                                .url("https://github.com/andreriffen/api-veiculos2"));
        }

        @Bean
        public GroupedOpenApi apiVeiculosGroup() {
                // Agrupa apenas os endpoints relevantes (clientes, marcas, modelos, cores, veículos) na documentação.
                return GroupedOpenApi.builder()
                                .group("api-veiculos")
                                .pathsToMatch("/clientes/**", "/marcas/**", "/modelos/**", "/cores/**", "/veiculos/**")
                                .build();
        }
}
