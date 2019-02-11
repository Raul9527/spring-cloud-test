package com.lrg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Optional;

public class Test<T> {
    public static void main(String[] args){
        new Test<Integer>().main();
    }
    public List<? extends T> main(T... a){
        Map map = new HashMap();
        /*Optional<String> optional = Optional.of("zhangsan");
        System.out.println(optional.orElseGet(()->"呵呵"));*/
        return null;
    }
}
