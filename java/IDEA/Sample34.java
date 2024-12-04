import java.util.Arrays;

public class Sample34 {

    //목표: 배열의 원소가 문자열(String) 객체일 때, 동등 비교 연산 해보자!
    public static void main(String[] args){
//        String[] arr= new String[]{"a", "b", "c"};
        String[] arr= new String[3];

//        System.out.println(arr);
        System.out.println(Arrays.toString(arr));

        //리터럴 객체는 힙에 오직 단 한번만 생성하고
        arr[0] = "JAVA";
        arr[1] = "JAVA";
        arr[2] = new String("JAVA");

        System.out.println(Arrays.toString(arr));
        System.out.println(arr[0]==arr[1]);
        System.out.println(arr[0]== arr[2]);
    }//main
}//end. class
