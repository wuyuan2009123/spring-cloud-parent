package com.springcloudstudy.zuul.web.controller;

import com.springcloudstudy.zuul.entity.User;
import com.springcloudstudy.zuul.web.service.AggetionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;

import java.util.HashMap;

/**
 * Created by wuy on 2017/7/27.
 */

@RestController
public class AggetionController  {
    @Autowired
    private AggetionService aggetionService;

    @GetMapping("/agg/{id}")
    public DeferredResult<HashMap<String,User>> agg(@PathVariable("id") Long id){
        Observable<HashMap<String,User>> map=this.aggOb(id);
        return this.toDefferResult(map);
    }

    public Observable<HashMap<String,User>> aggOb(Long id){
        return Observable.zip(
                this.aggetionService.getUserById(id),
                this.aggetionService.getMovieUserById(id),
                (user,movieuser) ->{
                    HashMap<String,User> map=new HashMap<>();
                    map.put("user",user);
                    map.put("movieuser",movieuser);
                    return map;
                }
        );
    }

    public DeferredResult<HashMap<String,User>> toDefferResult(Observable<HashMap<String,User>> detail){
        DeferredResult<HashMap<String,User>> result=new DeferredResult<>();
        detail.subscribe(new Observer<HashMap<String,User>>(){
            @Override
            public void onCompleted() {
                System.out.println("完成");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onNext(HashMap<String, User> stringUserHashMap) {
                result.setResult(stringUserHashMap);
            }
        });
        return result;
    }

}
