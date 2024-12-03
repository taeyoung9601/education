import java.util.Arrays;

public class Sample32 {

    public static void main(String[] args){

//        int[] arr1 = new int[3];

//        double[] arr2 = new double[3];
//        System.out.println(Arrays.toString(arr2));
//
//        boolean[] arr3 = new boolean[3];
//        System.out.println(Arrays.toString(arr3));

//        arr1[0] = 10;
//        arr1[1] = 100;
//        arr1[2] = 1000;

        int[] arr1 = {1,2,3};  // 리터럴 방식 배열 객체 생성

        System.out.println(Arrays.toString(arr1));
//        tempMethod(배열객체);
//        tempMethod(여기서 직접 배열객체를 생성해서 전달);
//        tempMethod({1,2,3});  //  XX
//        tempMethod(new int[3]);  // OK, 값을 지정 못함
        tempMethod(new int[] {1,2,3});  // ok. Normal
//        tempMethod(new int[3] {1,2,3});  // XX
    } // main

    //매개변수로 받은 배열 객체를 이브게 출력해주는 메소드
    static void tempMethod(int[] arr){
        System.out.println(Arrays.toString(arr));

    } // tempMethod

}// end.class

