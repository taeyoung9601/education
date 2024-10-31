        var counter =0;  // 카운트변수
        var sum =0;      // 3의 배수 합계
        var 순차번호 = 1;  // 순차번호
        var multiple = 3; // 배수 조건
        var sum2 = 0;  // 100이하의 3의 배수가 아닌 수 더한 값
        

        while(counter < 100) {               // 100번 반복
            var remainder= 순차번호 % multiple;                 // 순차 번호를 3으로 나눈 나머지 값
            var 몫 = Math.floor(순차번호 / multiple);
            if(remainder == 0){                        // 3으로 나눈 나머지 값이 0을 찾는 과정
                sum += 순차번호;
                console.log("식 : 3 x", 몫, "3의배수: ", 순차번호);
            }

            if(remainder != 0){
                sum2 += 순차번호;
                console.log("식: 3 x", 몫, "+" , remainder,"3의 배수가 아닌 값", 순차번호);
            }

            순차번호++;
            counter++;
            var sum3 = sum+ sum2    ;                     // 100이하의 수 더한 값
        }
        console.log('100이하의 3의배수 합',sum);
        console.log('100이하의 3의배수가 아닌 수의 합' , sum2);
        console.log('100이하의 수를 더한 값', sum3);


// 1. 100이하의 3의 배수의 합을 구해라 
// 2. 100이하의 3의 배수를 구해라
// 3. 다 더해라