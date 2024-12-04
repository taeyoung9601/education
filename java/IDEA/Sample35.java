import java.util.Arrays;

public class Sample35 {

    //목표: 배열 객체의 복제
    // 복제(사) 방법 2가지:
    // 1. 얕은 복제(사) - Thin/Shallow Copy
    // 2. 깊은 복제(사) - Deep Copy (****)
    public static void main(String[] args){

        String[] sourceArr = {"A", "B", "C"};

        // 1. Thin/Shallow Copy - 원 배열변수의 값을 할당
        String[] targetArr = sourceArr;
        targetArr[0] = "Tae";

        // 2. Deep Copy - source 객체를 완전히 다른 객체로 복제하는 방법

        // 1st. method -여러분이 직접 원소를 하나씩 복사해주는 것
        targetArr = new String[3];

        for(int index = 0; index < sourceArr.length; index++){
            targetArr[index] = sourceArr[index];
        }// classical for

        // 2nd. method - System.arrayCopy() method
//        System.arraycopy(src, srcOffset, target, targetOffset, length);
        System.arraycopy(sourceArr, 0, targetArr,0, sourceArr.length);

        // 3rd. method = Best choice for array's deep copy
        targetArr = Arrays.copyOf(sourceArr,sourceArr.length);


        System.out.println(Arrays.toString(sourceArr));
        System.out.println(Arrays.toString(targetArr));


    }//main

}//end. class