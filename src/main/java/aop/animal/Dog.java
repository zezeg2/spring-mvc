package aop.animal;

import org.springframework.stereotype.Component;

@Component("dog")
public class Dog implements Animal {
    @Override
    public String lunch() {
        String lunch = "사료";
        System.out.println(lunch + " 오ㅓㄹ오ㅏㄹ왈ㄹ왈");
        return lunch;
    }
}
