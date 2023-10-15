package com.sujit.Instagram.service;

import com.sujit.Instagram.model.Post;
import com.sujit.Instagram.model.User;
import com.sujit.Instagram.repo.IPostRepo;
import com.sujit.Instagram.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    IPostRepo postRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    IUserRepo userRepo;

    public String createInstaPost(String email, String tokenValue, Post instaPost) {

        if(authenticationService.Authenticate(email,tokenValue)) {

            User existingUser = userRepo.findFirstByEmail(email);
            instaPost.setUser(existingUser);

            postRepo.save(instaPost);
            return "Post added successfully";

        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String deleteInstaPost(String email, String tokenValue, Integer postId) {

        if(authenticationService.Authenticate(email,tokenValue)) {

            Post instaPost =  postRepo.getById(postId);
            String  postOwnerEmail =  instaPost.getUser().getEmail();

            if(email.equals(postOwnerEmail))
            {


                //finally delete the insta post
                postRepo.deleteById(postId);
                return "post removed!!";

            }
            else {
                return "Un authorized access!!";
            }


        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public Post getPostByID(Integer id){
        return postRepo.getById(id);
    }
}
