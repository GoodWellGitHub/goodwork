package com.org.stream;



import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2018/7/15.
 */
public class TestForeach {
    String[] as={"asdf","hello","workd"};
    List<String> strings= Arrays.asList(as);
    @Test
    public void testForeach (){
/*        try {
            int length=strings.stream()
                    .filter(s->s.startsWith("a"))
                    .mapToInt(String::length)
                    .max();
        }catch (Exception e){
            e.printStackTrace();
        }*/
List<String> result=strings.stream()
        .filter(s->s.length()>4)
        .collect(Collectors.toList());
result.forEach(s-> System.out.println(s));

    }

}
