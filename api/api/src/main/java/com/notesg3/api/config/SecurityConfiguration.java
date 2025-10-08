package com.notesg3.api.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // Todo token precisa de uma chave secreta a defino como string:
    @Value("${api.jwt.secret}")
    private String jwtSecret;

    //private String jwtSecret = "gPy7aFp_b4E5uX3mG8vK2rD7sW0zJ1cR9pQ4tH6uI-o=";;

    // BEANS: Os "Motores" da Nossa Segurança

    // 1. O GERADOR DE TOKENS (JWT Encoder)
    @Bean
    public JwtEncoder jwtEncoder() {
        // Cria a chave de criptografia a partir da nossa string secreta.
        var secretKey = new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA256");
        // Retorna a implementação do codificador que usará nossa chave para assinar os tokens.
        return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey));
    }

    // 2. O VERIFICADOR DE TOKENS (JWT Decoder)
    @Bean
    public JwtDecoder jwtDecoder() {
        // Cria a mesma chave usada para codificar.
        var secretKey = new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA256");
        // Retorna a implementação do decodificador que usará a chave para verificar a autenticidade dos tokens.
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
    }

    // 3. O CRIPTOGRAFADOR DE SENHAS
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Retorna a implementação BCrypt, o padrão recomendado para criptografar senhas.
        return new BCryptPasswordEncoder();
    }

    // 4. O GERENTE DE AUTENTICAÇÃO
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // Pega o gerenciador de autenticação padrão do Spring, que já sabe como usar nosso
        // UserDetailsService e PasswordEncoder para validar as credenciais de login.
        return config.getAuthenticationManager();
    }

    // --- O FILTRO DE SEGURANÇA ---

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desativa a proteção CSRF, que não é necessária para APIs stateless.
                .csrf(csrf -> csrf.disable())
                // Configura a gestão de sessão para ser stateless (sem estado).
                // O servidor não guarda informações do usuário entre as requisições.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST ,"/api/Usuarios/cadastrarUser/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "swagger-ui.html").permitAll()

                        // Permite TODAS as requisições para QUALQUER endpoint.
//                        .anyRequest().permitAll()
                        .anyRequest().authenticated()

                ) .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));


        return http.build();
    }
}