package com.sujit.Instagram.service;

import com.sujit.Instagram.model.AuthenticationToken;
import com.sujit.Instagram.model.User;
import com.sujit.Instagram.repo.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IAuthenticationRepo authenticationRepo;


    public void addAuthentication(AuthenticationToken token){
        authenticationRepo.save(token);
    }

    public boolean Authenticate(String Email,String TokenValue){
        AuthenticationToken token = authenticationRepo.findFirstByTokenValue(TokenValue);
        if(token!=null){
            return token.getUser().getEmail().equals(Email);
        }else {
            return false;
        }
    }

    public void deleteToken(String tokenValue) {
        AuthenticationToken token = authenticationRepo.findFirstByTokenValue(tokenValue);
        authenticationRepo.delete(token);
    }
}
