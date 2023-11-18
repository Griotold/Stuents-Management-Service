package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentUpdateForm {

    private Long id;
    private String name;
    private String residence;
    private String university;
    private String major;
}
