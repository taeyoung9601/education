//단순 IF문 작성

var bool = true;  // 변수정의 = 변수의 선언 + 초기화

// 모든 제어문에서는 중괄호 기호가 열리고/닫히면, 0개 이상의 실행문장을 넣어서
//  수행시킬 수 있는 블록(block)이 생성됩니다.

bool = false; // 변수의 값 변경

var 돈 = true;
var 신용카드 = false;

if((돈 == true) && (신용카드 == true)) {
    //조건식이 참일 때 수행될 실행문장들이 0개 이상 작성 가능!
    console.log('오늘은 혼술합니다.');
} else { //IF문의 조건식이 거짓(False)일 때 수행
    console.log('오늘은 집에 가자!');
}

// IF문의 종류: 
// (1)단순 IF - 조건식이 참일 때만 고려 (else블록 없음)
// (2)IF-ELSE문 - 조건식이 참/거짓일 때를 다 고려 (else블록 있음)
//(3)다중(여러개의 조건) IF문 - 판단해야 할 조건식이 2개 이상.

var score = 50; // 점수에 따라 A ~ C 등급을 매기자!
                //점수 >= 90, A등급
                //점수 >= 80, B등급
                //점수 >= 70, C등급

var 등급;   // 변수의 선언

if(score >= 90) {
    console.log('A');
    등급 = 'A';     //변수의 초기화
}
    else if (score >= 80) {
        console.log('B');
        등급 = 'B';
}
    else if (score >= 70) {
        console.log('C');
        등급 = 'C';
}   else {  //위의 모든 조건식이 거짓일 때
        console.log('참 잘했습니다.');    
        등급 = 'D';
}

console.log('등급:' , 등급);



// 어떤 언어든, 현대의 언어는 제어문의 중첩(Nested)을 허용합니다.
// 중첩이 무슨 의미입니까?
// IF문 안에, 또 if문, 또 이 if문안에 또 다른 if문이 올 수 있는 것을 '중첩'이라고 함
// 하지만, 중복된 코드의 발생과 이로인한 변경시 모든 중복된 코드를 정확히 다 찾아서 동일하게 변경하지
// 못하면 결국 찾기도 힘든 중대한 버그가 됩니다.
// 때문에 , 중첩은 가능하면 하지마세요!!