package org.zerock.myapp.set;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@Log4j2
@ToString
@EqualsAndHashCode
@AllArgsConstructor

// Set 컬렉션 요소 또는 Map 컬렉션의 키로 사용될 참조타입
public class Member {
    private String name;
    private int age;


    /**
    @Override
    public int hashCode() {
        log.debug("hashCode() invoked.");

        return name.hashCode() + age;
    }

    @Override
    public boolean equals(Object obj) {
        log.debug("equals({}) invoked.", obj);

        if(obj instanceof Member member) {
            // 동등성 비교 수행 - 즉 데이터로 같은 회원인지 아닌지 판단하라!
            return this.name.equals(member.name) && this.age == member.age;
        }

        return false;
    }
    */

}
