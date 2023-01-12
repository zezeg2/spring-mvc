package org.example.aop.aop_annotation;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Board {
    public void insert(int seq) {
        System.out.println(seq + "번째 게시물 저장");
    }

    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("p1");
        list.add("p2");
        list.add("p3");
        list.add("p4");
        list.add("p5");
        return list;
    }
}
