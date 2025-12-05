package com.example.api_veiculos2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │                   ⚙️  CONFIGURAÇÃO DE SEGURANÇA                         │
 * └─────────────────────────────────────────────────────────────────────────┘
 * 
 * Configuração de segurança da aplicação utilizando Spring Security 🍃.
 * 
 * Esta classe configura a autenticação HTTP Basic e define as regras de
 * autorização para os endpoints da API de veículos. Implementa usuários em 
 * memória para fins de desenvolvimento e testes.
 * 
 * Funcionalidades principais:
 * - Autenticação HTTP Basic
 * - Gerenciamento de usuários em memória (admin e user)
 * - Definição de rotas públicas e protegidas
 * - Desabilitação de CSRF para APIs REST
 * - Configuração de codificação de senhas com BCrypt
 * 
 * @author Andre GB Farias (andre.gbf@aluno.ifsc.edu.br)
 * @version 1.0
 * @since 2025
 * @implNote 
 * ╔═════════════════════════════════════════════════════════════════════════╗
 * ║  ⚠️  ATENÇÃO - JAMAIS USAR EM PRODUÇÃO!                                ║
 * ║                                                                         ║
 * ║  Esta configuração utiliza usuários em memória com senhas simples       ║
 * ║  ("admin123" e "user123") apenas para fins de desenvolvimento.          ║
 * ╚═════════════════════════════════════════════════════════════════════════╝
 */
@Configuration
public class SecurityConfig {

        // Rotas abertas: a galera que entra na festa sem precisar dizer que conhece o
        // dono.
        private static final String[] PUBLIC_PATHS = {
                        "/h2-console/**",
                        "/docs/**",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/api-docs/**"
        };

        // Rotas protegidas: só passa quem lembra a senha do Wi-Fi (ou pelo menos o
        // usuário e a senha).
        private static final String[] PROTECTED_PATHS = {
                        "/clientes/**",
                        "/marcas/**",
                        "/modelos/**",
                        "/cores/**",
                        "/veiculos/**"
        };

        /**
         * Configura a cadeia de filtros de segurança do Spring Security.
         * 
         * Este método é um Bean que define como as requisições HTTP serão protegidas.
         * A SecurityFilterChain é responsável por:
         * - Definir quais endpoints requerem autenticação
         * - Configurar tipos de autenticação (form login, JWT, OAuth2, etc.)
         * - Aplicar filtros de segurança (CSRF, CORS, etc.)
         * - Definir permissões baseadas em roles/authorities
         * 
         * @param http - Objeto HttpSecurity que permite configurar a segurança web
         * @return SecurityFilterChain - A cadeia de filtros configurada
         * @throws Exception - Caso ocorra erro na configuração
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                // CSRF desligado porque nossos scripts automatizados não têm paciência para
                // formulários com CAPTCHA invisível.
                http.csrf(csrf -> csrf.disable())

                                // Libera o frameOptions para o H2-console; afinal, banco em memória é tipo
                                // parente: aparece sem avisar e precisa entrar.
                                .headers(headers -> headers.frameOptions(frame -> frame.disable()))

                                // Mapeia quem entra sem convite e quem precisa mostrar documento na portaria.
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(PUBLIC_PATHS).permitAll()
                                                .requestMatchers(PROTECTED_PATHS).authenticated()
                                                .anyRequest().permitAll())

                                // HTTP Basic: o equivalente a um aperto de mão meio preguiçoso, mas quebra o
                                // galho no estudo.
                                .httpBasic(Customizer.withDefaults());

                return http.build();
        }

        @Bean
        public InMemoryUserDetailsManager users(PasswordEncoder encoder) {
                // Cria o admin: a pessoa que tem a chave mestra e ainda lembra onde largou a
                // senha anotada.
                // Aqui criamos um usuário com papel de ADMIN (da maneira mais preguiçosa
                // possível).
                // Usuário: admin | Senha: admin123 - PORQUE SIM!!! 😎
                UserDetails admin = User.withUsername("admin")
                                .password(encoder.encode("admin123"))
                                .roles("ADMIN")
                                .build();

                // Cria o usuário padrão: acessa quase tudo, mas não dá pra emprestar o carro
                // esportivo.
                // Usuário: user | Senha: user123 😒
                UserDetails user = User.withUsername("user")
                                .password(encoder.encode("user123"))
                                .roles("USER")
                                .build();
                // Retorna o gerenciador de usuários em memória com os dois usuários criados.
                return new InMemoryUserDetailsManager(admin, user);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                // BCrypt: o personal trainer das senhas, deixando qualquer string sedentária
                // fortalecida contra curiosos.
                return new BCryptPasswordEncoder();
        }
}
