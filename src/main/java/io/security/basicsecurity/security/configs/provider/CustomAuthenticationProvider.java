package io.security.basicsecurity.security.configs.provider;

import io.security.basicsecurity.security.configs.service.AccountContext;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.naming.AuthenticationException;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{

        //TODO 수정
        String userName = authentication.getName();
        String password = (String)authentication.getCredebtials();

        AccountContext accountContext = (AccountContext)userDetailsService.loadUserByUsername(userName);

        if(!passwordEncoder.matches(password, accountContext.getAccount().getPassword())){
            throw new BadCredentialsException("BadCredentialException");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());
        return authenticationToken;
    }

    @Override
    public org.springframework.security.core.Authentication authenticate(org.springframework.security.core.Authentication authentication) throws org.springframework.security.core.AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication){
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }


}
