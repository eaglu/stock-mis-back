package com.qfedu.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AnimalTalkService {
    //解耦第一步，通过kv对象存入简称及对应的类路径
    private Map beanReferenceProperties = new HashMap();

    AnimalTalkService() {
        beanReferenceProperties.put("cat", "com.qfedu.test.Cat");
        beanReferenceProperties.put("dog", "com.qfedu.test.Dog");
    }

//    public void talk(Animal animal) {
//        animal.talk();
//    }

    //解耦第二步，重载talk方法，传入简称
    public void talk(String animalName) {
        try {
            //解耦第三步，通过简称获取到类的全路径，使用java反射，进行实例化
            String animalClassName = (String) beanReferenceProperties.get(animalName);
            Animal animal = (Animal) Class.forName(animalClassName).newInstance();
            //调用具体类的talk方法
            animal.talk();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void foreanList(List list) {
        for (Object o : list) {
            System.out.println(o);
        }
        for (Iterator itr = list.iterator(); itr.hasNext(); ) {
            System.out.println(itr.next());
        }
    }

}