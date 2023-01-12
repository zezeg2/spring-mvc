package aop.aop_annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Aop2Runner {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop2.xml");
        Member member = context.getBean("member", Member.class);
        member.insert("id",1234);
        member.login("id");
        member.logout();

        Board board = context.getBean("board", Board.class);
        board.getList();

//        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }
}