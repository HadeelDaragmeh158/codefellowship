package com.appSecure.codefellowship.Repository;

import com.appSecure.codefellowship.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {


}

