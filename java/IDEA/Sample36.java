public class Sample36{

    // 목표: 열거 타입의 선언과 이 타입의 사용법을 배우자!!
    public static void main(String[] args){
        //Week란 열거타입의 참조변수로,
        // 오로지 이 Week 열거 타입에서 나열한 열거 상수(객체)만을
        // 값으로 가지도록 제약됩니다.
        Week today = Week.일요일;
        System.out.println("today:" + today);

        //열거타입의 목적 자체가, 제한된 값(열거 상수)만을
        //가지도록 하는 참조 타입이기 떄문에, 다른 타입의 객체는
        //절대로 저장할 수 없다!!
//        today = 일요일;

        switch(today) {
            case Week.월요일 :
            case Week.화요일 :
            case Week.수요일 :
            case Week.목요일 :
            case Week.금요일 : System.out.println("평일엔 놀자"); break;
            case Week.토요일 :
            case Week.일요일 : System.out.println("주말엔 쉬자");

        } //switch

        //--------------------------
        Gender gender = Gender.Man;
        gender = Gender.Woman;
        //---------------------------



    }//main

}// end. class
