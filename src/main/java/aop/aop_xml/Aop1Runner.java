package aop.aop_xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Aop1Runner {
    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        ApplicationContext context = new ClassPathXmlApplicationContext("aop1.xml");
        Member member = context.getBean("member", Member.class);
        member.insert("id",1234);
        member.login("id");
        member.logout();

        Board board = context.getBean("board", Board.class);
        board.getList();

//        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }
}