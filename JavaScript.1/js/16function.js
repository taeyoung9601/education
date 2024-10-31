text1 = "함수 선언 전 호출";       //전역 변수
var text2 = "함수 선언 후 호출";    //전역 변수

//함수의 호이스팅(Hoisting)
printMsg(text1);                //함수 선언 전 호출

function printMsg(msg){         //함수선언
    console.log("함수 호출 메시지:",msg);
    
    var isEnd = true;
    if(isEnd){
                return;
                console.log(1);
    }else {
        console.log(2);
    }
    //함수블록 내에서 var로 선언된 변수 블록 안에서만 사용된다. => 지역변수
    //(1)var로 선언된 변수 => 지역변수
    //(2)var 키워드 없이 선언된 변수 => 전역 변수
    // result1 = 100 + 200 ; //  (1)
    result2 = 100+200 ; //(2)var가 없으면 전역변수로 "승급"
    
    //return result; // return문 : 결과값을 반환

}

printMsg(text2); // 함수 선언 후 호출


console.log('result:', result2);


