package com.springcloudstudy.zuul.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloudstudy.zuul.entity.User;
import com.springcloudstudy.zuul.inter.MovieFeignClient;
import com.springcloudstudy.zuul.inter.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

/**
 * Created by wuy on 2017/7/27.
 */
@Service
public class AggetionService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private MovieFeignClient movieFeignClient;


    public Observable<User> getUserById(Long id){
        return  Observable.create(observer ->{
            User user= userFeignClient.findById(id);
            observer.onNext(user);
            observer.onCompleted();
        });
    }

    public Observable<User> getMovieUserById(Long id){
        return  Observable.create(observer ->{
            User movie= movieFeignClient.findUserById(id);
            observer.onNext(movie);
            observer.onCompleted();
        });
    }


}
