package hello.hellospring.controller;

public class MemberForm {
    private String name;  //이 name이랑 createMemberForm의 name이 매칭되어 값이 들어옴

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
