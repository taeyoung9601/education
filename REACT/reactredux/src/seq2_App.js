//react-redux 패키지가 제종하는 2개의 훅 함수를 이용해서,
//리덕스 스토어의 상태에 접근(사용)하고자하는
//상태를 변경하기도하고, 읽기도 하게됩니다.
import { useSelector,useDispatch,connect} from "react-redux";

console.clear();
console.groupCollapsed('src/seq2_App.js'); console.groupEnd();

function App(props) {
  console.groupCollapsed('App(',props,') invoked.'); console.groupEnd();

  const {count,state2,state3,dispatch,onClickMinus,onClickPlus,onClickReset} = props;
  console.log(`count:${count}, state2:${state2},state3:${state3}, onClickMinus: ${onClickMinus}, onClickPlus: ${onClickPlus}`)

  const tempCount = useSelector(select => select.count);  // if 2nd. try

  return (
    <div className="App">

      <h1>Count : {(count)? count:tempCount} </h1>

      {/* <button onClick={()=> dispatch({type:'+', payload:void 0})}>+</button>
      <button onClick={()=> dispatch({type:'-', payload:void 0})}>-</button> */}

      {(onClickPlus && onClickMinus && onClickReset)?
        <>
        <button onClick={onClickPlus}>+</button>
        <button onClick={onClickMinus}>-</button>
        <button onClick={onClickReset}>*</button>
        </>
      :
        <>
        <button onClick={()=> dispatch({type:'+', payload:void 0})}>+</button>
        <button onClick={()=> dispatch({type:'-', payload:void 0})}>-</button>
        <button onClick={()=> dispatch({type:'*', payload:void 0})}>-</button>
        </>
      }


      {/* <button onClick={onClickPlus}>+</button>
      <button onClick={onClickMinus}>-</button>    */}

    </div>
  );
} 
// console.log('1.App:',App);


// const WrappedComponent = connect(mapStateToProps, mapDispatchToProps)(originalComponent);
// connect 함수를 통해, Redux Store에 직접 연결된 리액트 컴포넌트(App)를 안에 
// 가지고 있는 또 다른 리액트 컴포넌트를 반환합니다.
// const App2 = connect(null,null)(App);  // return Wrapped Component for App
// console.log('2.App:',App2);       


// export default connect(mapStateToProps, mapDispatchToProps)(YourComponent);
// (1) mapStateToProps: map Redux store state to props 
//      리덕스 스토어의 상태를 props 속성으로 매핑하는 함수
// (2) mapDispatchToProps: map dispatch to props
//      dispatch를 props 속성으로 매핑하는 객체

const mapStateToProps = (state) => {
  console.debug('mapStateToProps(',state,') invoked.');
  // return {
  //   count: state.count
  // };
  return state;  //전체 속성을 가져오고싶을때
}



const mapDispatchToProps = (dispatch) => {
  console.debug('mapDispatchToProps(',dispatch,') invoked.');

  return{
    onClickPlus: ()=> dispatch({type:'+', payload: void 0}),
    onClickMinus: ()=> dispatch({type:'-', payload: void 0}),
    onClickReset: ()=> dispatch({type:'*', payload: void 0})
  };
}


// export default connect(mapStateToProps,null)(App);   //1st. try
// export default connect(null,mapDispatchToProps)(App);   //2nd. try
export default connect(mapStateToProps,mapDispatchToProps)(App);

/**
    // -----------------------------------
    // mapStateToProps : (state) => {connect 함수로 직접 연결된 리액트 컴포넌트의 props 속성 }
    //  Ex) const mapStateToProps = (state) => ({
    //            count: state.count,
    //        });

    // *Important: `mapStateToProps()` in connect(App) must return a plain object.
    const mapStateToProps = (state) => ({
        // state를 props로 매핑
    });

    // -----------------------------------
    //  Ex) const mapDispatchToProps = (dispatch) => ({
    //            onIncrement : () => dispatch( action ),
    //            onDecrement: () => dispatch( action ),
    //        });

    // *Important: `mapDispatchToProps()` in connect(App) must return a plain object.
    const mapDispatchToProps = (dispatch) => ({
        // dispatch 함수를 props로 매핑
    });

    export default connect(mapStateToProps, mapDispatchToProps)(YourComponent);
*/
