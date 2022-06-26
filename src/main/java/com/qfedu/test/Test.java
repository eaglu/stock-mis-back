package com.qfedu.test;

import com.qfedu.entity.User;

import javax.annotation.Resource;

public class Test {

    @Resource(name="user")
    User user;

    public static void main(String[] args) {
        AnimalTalkService s = new AnimalTalkService();
        s.talk("cat");
        s.talk("dog");
//        s.talk(new Dog());
//        List ar = new ArrayList();
//        ar.add("a");
//        ar.add("b");
//
//        List lk= new LinkedList();
//        lk.add("c");
//        lk.add("d");
//        s.foreanList(ar);
//        s.foreanList(lk);
    }
}
