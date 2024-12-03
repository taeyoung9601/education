public class Sample27 {

    // 참조타입 변수의 동등비교( ==, !=) 연산
    public static void main(String[] args){
        //변수의 타입을 선언하는 목적:
        // 1. 변수가 저장 가능한 값의 종류
        // 2. 변수가 저장 가능한 값의 범위(=도메인)

        //JVM이 아래의 2행을 수행시킬 떄,
        //문자열 리터럴(String 타입의 객체)은 힙 영역에 오로지 단 한번만 생성
        String s1 = "Tae";  // 문자열 객체의 주소 저장
        String s2 = "Tae";  // 문자열 객체의 주소 저장

        //동등비교 - 두 참조타입 변수에 대한 동등 비교 연산의 의미
        // 참조타입변수에 대한 동등비교연산의 대상은, 바로 
        if(s1 == s2){
            System.out.println("s1 == s2");
        }else{
            System.out.println("s1 != s2");
        }

        System.out.println("s1: "+ s1);
        System.out.println("s2: "+ s2);
    }// end. main

} // end class

