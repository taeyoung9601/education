public class Sample30 {

    public static void main(String[] args){
        int[] scores = {1,2,3,4,5,6,7};
        int sum = 0;
        double avg = 0;
        //배열의 순회 ( Traverse)
        for(int index = 0; index<scores.length;index++){
            System.out.println(scores[index]);
            sum += scores[index];
        } //classical for

        avg = sum / scores.length;
        System.out.println("sum: "+ sum);
        System.out.println("avg: " + avg);

    } // main

} // end. class
