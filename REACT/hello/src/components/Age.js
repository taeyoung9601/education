console.groupCollapsed('src/components/Age.js'); console.groupEnd();

//리액트 컴포넌트명은 (1)파스칼 표기법 또는 (2) 스네이크 표기법 사용
// (1)파스칼 표기법: 각 단어의 첫 문자만 대문자로 표기 
// (2)스네이크 표기법: 모든 문자는 대문자로, 단어의 연결
//
// *주의사항 : 문법상으로는 위 2가지 표기법으로 함수명을 지어야하니까
//            실전에서는 거의 100% 함수명은 (1) 파스칼 표기법 따름
function Age() {
    console.groupCollapsed('Age() invoked.'); console.groupEnd();

    const myAge = 26;

    //React JSX 문법에 따라 값을 반환해야 합니다.
    //특히, 자바스크립트 표현식을 JSX에 포함시킬 때에는
    // 반드시 중괄호 블록 안에 표현식 코드를 넣어야 합니다.
    //표현식: 하나의 값을 생성하는 코드
    // (1) 하드코딩한 리터럴(상수, 순수한 값)
    // (2) 값을 저장하고 있는 변수
    // (3) 연산자와 피연산자로 구성된 표현식
    //(4) 값을 반환하는 함수호출구문
    // (5) 값을 반환하는 객체의 메소드 호출 구문
    return <p>Age:{ myAge + 10} </p>;
}

export default Age;