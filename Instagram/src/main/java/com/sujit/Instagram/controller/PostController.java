package com.sujit.Instagram.controller;

import com.sujit.Instagram.model.Post;
import com.sujit.Instagram.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class PostController {
    @Autowired
    PostService postService;


    @PostMapping("InstaPost")
    public String createInstaPost(@RequestParam String email, @RequestParam String tokenValue, @RequestBody Post instaPost)
    {
        return postService.createInstaPost(email,tokenValue,instaPost);
    }


    @DeleteMapping("InstaPost/{postId}")
    public String deleteInstaPost(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId)
    {
        return postService.deleteInstaPost(email,tokenValue,postId);
    }

}
