package aop.animal;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class AnimalRunner {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("animal.xml");

        Animal[] animals = new Animal[3];

        animals[0] = context.getBean("dog", Animal.class);
        animals[1] = context.getBean("cat", Animal.class);
        animals[2] = context.getBean("rabbit", Animal.class);

        Arrays.stream(animals).forEach(animal -> animal.lunch());
    }
}
