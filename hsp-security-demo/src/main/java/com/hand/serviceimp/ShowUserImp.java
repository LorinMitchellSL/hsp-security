package com.hand.serviceimp;

import com.hand.model.User;
import com.hand.service.ShowUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*******************Copyright Information************************
 *              AUTHOR: Lorin.Mitchell                           *
 *              DATE: 2017/10/19                                 *
 *              TIME: 16:38                                      *
 ****************************************************************/
@Service
public class ShowUserImp implements ShowUser {
    @Override
    public List<User> ShowUsers() {
        List<User> list = new ArrayList<User>();
        User user1 = new User("张三","男");
        User user2 = new User("李四","女");
        list.add(user1);
        list.add(user2);

        return list;
    }
}
