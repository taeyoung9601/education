import { useSelector, useDispatch } from "react-redux";
import {up,down,reset, increaseAge, decreaseAge,resetAge} from './Store'


console.groupCollapsed('src/App.js'); console.groupEnd();

function App(props) {
  console.groupCollapsed('App(',props,')invoked.'); console.groupEnd();

  const dispatch = useDispatch();
  
  const count = useSelector((state) => {
    return state.countSlice.count;
  })

  const age = useSelector((state) => {
    return state.ageSlice.age;
  })


  return (
    
    <div className="App">

      <h1>Count: {count} </h1>

      <div>
        <button onClick = {()=>{dispatch(up());}}>UP</button>
        <button onClick = {()=>{dispatch(down());}}>DOWN</button>
        <button onClick = {()=>{dispatch(reset());}}>RESET</button>  
      </div>


      <h1>Age: {age} </h1>

        <div>
          <button onClick = {()=>{dispatch(increaseAge());}}>Increase</button>
          <button onClick = {()=>{dispatch(decreaseAge());}}>Decrease</button>
          <button onClick = {()=>{dispatch(resetAge());}}>Reset</button>  
        </div>

    </div>
    
  );
}

export default App;
