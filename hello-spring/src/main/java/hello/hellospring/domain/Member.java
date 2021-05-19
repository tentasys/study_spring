package hello.hellospring.domain;

import javax.persistence.*;

@Entity //jpa가 관리하는 entity가 됨
public class Member {
    //PK 매핑
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY) //db가 알아서 생성해 주는 것을 identity라고 함
    private Long id;

    @Column(name = "name")  //db에 있는 컬럼 username과 매핑됨
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
