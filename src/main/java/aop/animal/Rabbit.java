package org.example.aop.animal;

import org.springframework.stereotype.Component;

@Component("rabbit")
public class Rabbit implements Animal {
    @Override
    public String lunch() {
        String lunch = "당근";
        System.out.println(lunch + " 토끼풀뜯어먹는 토끼에용");
        return lunch;
    }
}
