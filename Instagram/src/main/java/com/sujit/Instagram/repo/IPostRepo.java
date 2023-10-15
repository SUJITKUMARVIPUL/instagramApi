package com.sujit.Instagram.repo;

import com.sujit.Instagram.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post,Integer> {
}
