import ReactDOM from 'react-dom/client';
import {legacy_createStore as createStore} from 'redux';
import { Provider } from 'react-redux';
// import App from './seq1_App';
import App from './seq2_App';

console.groupCollapsed('src/index.js'); console.groupEnd();


//Step1. Define a reducer function
const initialState = {count: 0};

function countReducer(prevState, action){
  console.groupCollapsed('countReducer(',prevState,action,')invoked.'); console.groupEnd();

  switch(action.type){
    case'+': return {...prevState, count: prevState.count+1};
    case'-': return {...prevState, count: prevState.count-1} ;
    case'*': return prevState ;
  }
  return prevState;
}

//Step2.Create a Redux store with reducer function
const preloadedState = {count: 0, state2:'value2', state3:'value3'};
const store = createStore(countReducer, preloadedState);
console.log('1.store:' , store);

//Step3. set a litener to monitor state change in the Redux store
store.subscribe(()=>{
  console.groupCollapsed('Redux store changed.state:', store.getState());
  console.groupEnd();
})



//Step4. Set range to use Redux store with Provider component in 'react-redux'.
const root = ReactDOM.createRoot(document.querySelector('#root'));

root.render(

  <Provider store={store}>
    <App name="Tae" age={26}/>
  </Provider>

);