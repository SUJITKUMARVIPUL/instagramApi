package com.sujit.Instagram.repo;

import com.sujit.Instagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo  extends JpaRepository<User,Integer> {
    User findFirstByEmail(String email);
}
