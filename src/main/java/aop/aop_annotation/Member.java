package aop.aop_annotation;

import org.springframework.stereotype.Component;

@Component
public class Member {
    public void login(String id){
        System.out.println(id + " login");
    }
    public void logout(){
        System.out.println("logout successfully");
    }
    public void insert(String id, int pw){
        System.out.println(id + "sign up");
    }
}
