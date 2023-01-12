package aop.animal;

import org.springframework.stereotype.Component;

@Component("cat")
public class Cat implements Animal {
    @Override
    public String lunch() {
        String lunch = "생선";
        System.out.println(lunch + " 츄르나 더갖고와 집사색기야ㅋㅋ");
        return lunch;
    }
}
