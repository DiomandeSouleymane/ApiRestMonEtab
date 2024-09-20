package ci.digitalacademy.monEtab.security;

import ci.digitalacademy.monEtab.models.RoleUser;
import ci.digitalacademy.monEtab.models.User;
import ci.digitalacademy.monEtab.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class DomainUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String pseudo) {

        final Optional<User> user = userRepository.findByPseudo(pseudo);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        final List<GrantedAuthority> grantedAuthorities = user.get()
                .getRoleUser()
                .stream()
                .map(RoleUser::getNameRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return user.map(userRecover -> org.springframework.security.core.userdetails.User.builder()
                .username(userRecover.getPseudo())
                .password(userRecover.getPassword())
                .authorities(grantedAuthorities)
                .build()).orElseThrow(() -> new IllegalArgumentException("User not found"));

    }
}


