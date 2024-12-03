public class Sample29 {

    // 목표: 참조타입 중에, 첫 번째 타입인 배열 타입의 객체 생성
    // 방법: 1. 리터럴 방식의 배열의 객체 생성
    public static void main(String[] args){
        //int[] : 원소의 타입이 int 인 배열([]) 객체를
        //        저장하는 배열 타입
        int[] scores = {100, 90, 80};
        String[] names = {"NAME_1", "NAME2_2","NAME_3"};

        System.out.println("scores[0]: "+ scores[0]);
        System.out.println("scores[1]: "+ scores[1]);
        System.out.println("scores[2]: "+ scores[2]);
//        scores[3] = 99;  // xx : 새로운 원소 생성x
//        scores[0] = "100"; // xx : 원소 타입 변경x
        scores[0] = 0;
        System.out.println("");




    }// main

} // end. class
