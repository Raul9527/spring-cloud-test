package com.lrg;

import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Optional;

public class Test<T> {
    public static void main(String[] args){
        new Test<Integer>().main();
    }
    public List<? extends T> main(T... a){
        String id = null;
        Assert.notNull(id,"xyx");
        assert id == null:"hehe";
        Map map = new HashMap();
       /* Optional<String> optional = Optional.of("zhangsan");
        System.out.println(optional.orElseGet(()->"уехЩ"));*/
        return null;
    }
}
