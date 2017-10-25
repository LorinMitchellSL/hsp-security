package com.hand.controller;

import com.hand.model.User;
import com.hand.serviceimp.ShowUserImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/19                                 *
 *              TIME: 16:03                                      *
 ****************************************************************/
@RestController
public class UserController {

    @Autowired
    private ShowUserImp showUserImp;

    @GetMapping("/me")
    public Object getCurrentUser(@Valid @RequestBody org.springframework.security.core.userdetails.User user){
        return user;
    }

    @GetMapping("/user")
    public List<User> ShowUsers(){
        return showUserImp.ShowUsers() ;
    }
}
