import java.util.Arrays;
import java.util.stream.IntStream;

public class Sample31 {

    public static void main(String[] args){

        //IntStream.range(startNumber, endNumber).toArray(); -Half open
        //IntStream.rangeClosed(startNumber, endNumber).toArray(); - full-closed-open
        int[] arr = IntStream.range(1,10). toArray();

        System.out.println(Arrays.toString(arr));

        int sum = 0;
        for(int item : arr){
            System.out.println(item);
            sum += item;
        }
        System.out.println(sum);
    }
}
