package com.govida.security.securityauthserver.config;

import com.govida.security.securityauthserver.dao.AppUserRepository;
import com.govida.security.securityauthserver.entity.AppUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DefaultAuthenticationProvider implements AuthenticationProvider {


    private final AppUserRepository appUserRepository;

    public DefaultAuthenticationProvider(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
    
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        if (authentication.getName() == null || authentication.getCredentials() == null) {
            return null;
        }

        if (authentication.getName().isEmpty() || authentication.getCredentials().toString().isEmpty()) {
            return null;
        }



        final Optional<AppUser> appUser = appUserRepository.findById(authentication.getName());
        System.out.println(appUser.isPresent());
        if (appUser.isPresent()) {

            final AppUser user = appUser.get();
            final String providedUserEmail = authentication.getName();
            final Object providedUserPassword = authentication.getCredentials();

            if (providedUserEmail.equalsIgnoreCase(user.getUserEmail())
                    && providedUserPassword.equals(user.getUserPass())) {
                return new UsernamePasswordAuthenticationToken(
                        user.getUserEmail(),
                        user.getUserPass(),
                        Collections.singleton(new SimpleGrantedAuthority(user.getUserRole())));
            }
        }

        throw new UsernameNotFoundException("Invalid username or password.");
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    private List<SimpleGrantedAuthority> getAuthority() {
        return Collections.emptyList();
    }
}