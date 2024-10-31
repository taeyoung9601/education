// var depth = 0;  // 현재 위치
// var day = 1;    // 올라가기 시작 일
// var climb = 17; // 하루마다 올라간 높이
// var descend = 7; // 잠들면서 떨어진 높이
// var 꼭대기 = 137;  // 동굴의 꼭대기 높이
// // var escapeCondition = 139;  // 탈출 조건


// for(; ; ) { 
// depth += climb ;       
// console.log('탈출 걸린 날짜: ', day, '올라온 높이 : ', depth);
// if(depth > 꼭대기) {
//     break;
// }
// depth -= descend ;  depth = depth - descend     = 17 -7
// day++      
// }  





// // 꼭대기 높이에 도달했다고 탈출하지않음 탈출 조건을 정확히 명시
// // 스텝 순서를 구상하기 ==> 문제가 주어졌을 시 나중엔 바로 파악 가능 (알고리즘)
var currentHeight = 0; // 현재 위치
var day = 1;  // 올라가기 시작 일
var dayClimb = 17; // 하루마다 올라간 높이
var dayDescend = 7; // 잠들면서 떨어진 높이
var topHeight = 137; // 꼭대기 높이


console.log("* topHeight(정상 높이) : ", topHeight);
for (  ;  ;  ) { // 무한루프

    // Step1. 올라간다(17미터)
    currentHeight += dayClimb;

    //Step2. 높이체크
    //       if(동굴높이이상되면) break;  // 탈출조건
    console.log("올라간 후 : day : ", day, ' / currentHeight(현재 진행 높이) : ', currentHeight);
    if(currentHeight >= topHeight) {
        break;
    }
    
    //Step3. 미끄러짐
    currentHeight -=dayDescend;

    //Step4. 일수증가
    day++;
    console.log("-- 미끄러진 후 : day : ", day, ' / currentHeight(현재 진행 높이) : ', currentHeight);

}