package com.sujit.Instagram.service;

import com.sujit.Instagram.model.AuthenticationToken;
import com.sujit.Instagram.model.User;
import com.sujit.Instagram.model.dto.SignInDto;
import com.sujit.Instagram.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    AuthenticationService authenticationService;

//    PasswordEncryption passwordEncryption;

    public String userSignUp(User user){
        User getUser = userRepo.findFirstByEmail(user.getEmail());
        if(getUser!=null){
            return "email already registered , please go for sign in";
        }
        try {
            String encryptedPassword =PasswordEncryption.encrypt(user.getPassword());
            user.setPassword(encryptedPassword);
            userRepo.save(user);
        } catch (NoSuchAlgorithmException e) {
            return "problem in password encryption";
        }

        return "user registration successful";
    }

    public String userSignIn(SignInDto SignIn){
        User user = userRepo.findFirstByEmail(SignIn.getEmail());
        if(user==null){
            return "Given Email not registered";
        }
        try {
            String encryptPassword = PasswordEncryption.encrypt(SignIn.getPassword());
            if(user.getPassword().equals(encryptPassword)){
                AuthenticationToken token = new AuthenticationToken(user);
                authenticationService.addAuthentication(token);
                return token.getTokenValue();
            }else{
                return "password miss match";
            }
        } catch (NoSuchAlgorithmException e) {
            return "Algorithm problem";
        }
    }

    public String userSignOut(String email,String tokenValue) {
        if(authenticationService.Authenticate(email,tokenValue)) {
            authenticationService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }
}
