import { useReducer } from 'react';


console.groupCollapsed('src/App.js'); console.groupEnd();

function App(props) {
  console.group('App(', props, ') invoked.');
  console.groupEnd();


  const initalState = { count: 0 };

  // 은행의 회계직원: 주문(=액션)받은 대로, 장부(=상태) 변경하는자
  function countReducer(prevState, action) { //함수명 관례 : 상태변수명 + Reducer
    console.group('countReducer(prevState:', prevState, ', action:', action, ') invoked.');

    /*
      아래에서 반환한 값이, 새로운 상태값(newState)이 된다!!!
      주의!
      prevState이 기본 타입값이라면 어떤 값이든 반환하면, 그 자체로 상태변경이 되지만 
      prevState이 객체인경우(즉, 참조타입인 경우)에는 다르게 처리해야 상태변경으로 인정된다!
      또한, reducer 함수에 전달된 이전상태(prevState)는 불변이다!!!
      Step1. 깊은복제(deep copy)하라! -> Step2. 복제된 객체를 변경해서 반환
    */
    switch (action.type) {
      case 'up':
        return { ...prevState, count: prevState.count + 1 };
      case 'down':
        return { ...prevState, count: prevState.count - 1 };
      case 'reset':
        return { count: 0 };
      default:
        return prevState;
    }
  }

  //위임함수명 = 상태변수명 + Dispatch => f dispatchReducerAction(..)
  //useReducer hook이 반환한 상태는 불변(immutable == read-only, 읽기전용)객체이다!!!
  const [count, countDispatch] = useReducer(countReducer, initalState);

  console.log('1. count: ', count);
  console.log('2. countDispatch: ', countDispatch);

  //    { 구조분해변수: 구조분해변수의 newName(alias문법) } = count(구조분해할 객체);
  const { count: currentCount } = count;
  console.log('3. currentCount: ', currentCount);

  console.groupEnd();
  return (
    <div className="App">
      <h1>Count : {currentCount}</h1>


      {/* 실전에서, 액션은 아래처럼 기본타입(숫자/문자/불린) 으로 만들지 않고, 아래의 속성을 가진 "객체"로 만듭니다. */}
      {/* 액션의 형태: { type: 액션타입, payload: 기타데이터저장속성 } */}
      <button onClick={() => { console.log('- Click.'); countDispatch({ type: 'down', payload: undefined }); }}> 감소 </button>
      <button onClick={() => { console.log('0 Click.'); countDispatch({ type: 'reset', payload: undefined }); }}> 초기화 </button>
      <button onClick={() => { console.log('+ Click.'); countDispatch({ type: 'up', payload: undefined }); }}> 증가 </button>
    </div>
  );
}

export default App;
