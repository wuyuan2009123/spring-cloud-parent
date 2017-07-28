package com.springcloudstudy.productauth.dao;

import com.springcloudstudy.productauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wuy on 2017/7/26.
 */
@Repository
public interface UserRepositoty extends JpaRepository<User,Long> {

}
