public class Sample37 {

    public static void main(String[] args){

        Gender gender = Gender.Man;
        gender = Gender.Woman;

        System.out.println(gender);

        //---------
        //열거 타입이 제공하는 메소드들을 알자
        //---------
        // 1. 열거타입.name();
        String s = gender.name();
        System.out.println(s);

        // 2. 열거타입.ordinal();
        //열거 타입 변수 안에 저장되어 있는 열거 상수의 선언된 순서번호
        int ordinalNumber = gender.ordinal();
        System.out.println(ordinalNumber);

        // 3. 열거타입.compareTo(동일한 열거 타입 변수);
        Gender man = Gender.Man;
        Gender woman = Gender.Woman;

        /* 메소드의 리턴 값:
        (1). a negative integer(음수) : 기준<비교
        (2). zero(0)                  : 기준==비교
        (3). a positive integer(양수) : 기준>비교
        목적: 같은 열거 타입의 두 변수가 있을 떄,
        두 변수에 저장되어 있는 열거 상수 객체의 선언된 순서를
        비교해서, 누가 먼저 선언된 값을 가지고 있는지 판단
        즉, 동일한 열거 타입으로 선언된 두 참조 변수에
        같은 열거 상수가 저장되어 있는지/아닌지 판단
        */

        int result = man.compareTo(woman);
        System.out.println(result);

        // 4. 성별의 값이, 우리가 선언한 Gender 타입의 열거상수가
        //    아니라, 문자열 같은 다른 타입으로 들어왔을 때,
        //    이 문자열 -> 성별 열거 타입으로 변환해주는 메소드


        gender = Gender.valueOf("Man");
        System.out.println(gender);
    }
}
