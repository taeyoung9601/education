import { useReducer} from "react";


console.groupCollapsed("src/App.js"); console.groupEnd();


function App(props) {
  console.groupCollapsed('App(',props,') invoked.');console.groupEnd();

  const initialState = {count:0};

  //은행의 회계직원: 주문(=액션)받은대로, 장부(=상태)변경하는자
  function countreducer(prevState,action){  // 관례 : 상태변수명 + Reducer
    console.groupCollapsed('countreducer(prevState:',prevState,',action:',action,') invoked.'); console.groupEnd();

    switch (action.type) {
      case'UP': return {...prevState,count :prevState.count+1};
      case'RESET': return {count :0};
      case'DOWN': return {...prevState,count :prevState.count-1};
      default:    //action이 잘못들어옴 -> 이전상태 그대로 반환하는게 이성적
        return prevState;
    }

  }

  //위임함수명 = 상태변수명 + Dispatch
  //useReducer hook이 반환한 상태는 불변(immutable== read-only,읽기전용) 객체이다!
  const [count, countDispatch] = useReducer(countreducer, initialState);
  

  console.log('1.count:',count);
  console.log('2.countDispatch', countDispatch);



  const {count: currentCount} = count;
  console.log('3.currentCount:',currentCount);

  return (
    <div className="App">

      <h1>Count: {currentCount}</h1>
        
        {/* 액션의 형태 : {type: 액션타입 , payload: 기타데이터저장속성} */}
        <button onClick={()=>countDispatch({type:'UP', payload : undefined})}>+</button>
        <button onClick={()=>countDispatch({type:'RESET', payload : undefined})}>0</button>
        <button onClick={()=>countDispatch({type:'DOWN', payload : undefined})}>-</button>

    </div>
  );
}

export default App;
