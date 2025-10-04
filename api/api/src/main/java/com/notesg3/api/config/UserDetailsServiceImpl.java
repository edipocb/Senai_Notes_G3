package com.notesg3.api.config;

import com.notesg3.api.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // 1. Declaramos o nosso repositório de usuários.
    // Ele é 'final' porque será injetado uma vez no construtor e não deve ser alterado depois.
    private final UsuarioRepository usuarioRepository;

    // 2. Usamos a injeção de dependência via construtor.
    // (neste caso, o UsuarioRepository) para a nossa classe.
    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // 3. @Override: Indica que estamos implementando o método definido na interface UserDetailsService.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Este é o único método que precisamos implementar.
        // O Spring Security o chama automaticamente durante o processo de autenticação,
        // passando o 'username' que o usuário forneceu na tela de login (que para nós, é o email).

        // 4. Usamos nosso repositório para buscar o usuário no banco de dados pelo email.
        return (UserDetails) usuarioRepository.findByEmail(username)
                // 5. O método findByEmail retorna um 'Optional'. Se o Optional estiver vazio
                // (ou seja, o usuário não foi encontrado), o método .orElseThrow() é executado.
                // Ele lança uma exceção padrão do Spring Security, informando que o login falhou
                // porque o usuário não existe.
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

        // 6. Se o usuário for encontrado, o objeto Usuario é retornado.
    }
}