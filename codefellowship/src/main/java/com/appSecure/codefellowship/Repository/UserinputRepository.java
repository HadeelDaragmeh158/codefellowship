package com.appSecure.codefellowship.Repository;

import com.appSecure.codefellowship.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserinputRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

}
