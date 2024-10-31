//전통적인 반복문인 for문에 대해서 정석대로 배우자!  카운터 변수

//구현할 기능: 1+2+3+4....+10 = 55

var sum = 0;
var counter = 1
// ---(1)초기식----- (2)조건식-----(3)증감식--
for( ; ;)  {       //조건식을 생략하게 되면 무한루프에 빠진다.
    if(counter > 10) { // 탈출 조건
        break;
     } // 반복실행할 문장구현
   sum += counter;   // sum = sum + counter 

   console.log('counter:', counter);
   console.log('sum',sum);
   counter++ ;
} // classical for