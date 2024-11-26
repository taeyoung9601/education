//react-redux 패키지가 제종하는 2개의 훅 함수를 이용해서,
//리덕스 스토어의 상태에 접근(사용)하고자하는
//상태를 변경하기도하고, 읽기도 하게됩니다.
import { useSelector,useDispatch } from "react-redux";

console.clear();
console.groupCollapsed('src/App.js'); console.groupEnd();

function App(props) {
  console.groupCollapsed('App(',props,') invoked.'); console.groupEnd();

  //Step1. Get dispatch function with 'useDispatch' in react-redux
  const dispatch = useDispatch();
  console.log('1.dispatch:', dispatch);

  //Step2. Get current state with 'useSeletor' in react-redux.
  const count = useSelector((state)=>{
    console.debug('state:', state);

    return state.count;
  });
    console.log('2.count:', count);

  return (
    <div className="App">

      <h1>Count : {count}</h1>

      <button onClick={()=> dispatch({type:'+',payload: void 0})}>+</button>
      <button onClick={()=> dispatch({type:'-',payload: void 0})}>-</button>

    </div>
  );
}

export default App;
