import {
  legacy_createStore as createStore
} from 'redux';


console.groupCollapsed('src/index.js'); console.groupEnd();

//redux package's usage:
//Step1. Create a store which manage all states in app.
//Step2. Create all necessary action generators that create all required action objects
//       to manage global states. (ex: {type:..., payload:...})
//Step3. Create a reducer function and above => combineReducers function
//Step4. Update state with pre-defined action generators & Redux store's dispatch method
//      (There are no any dispatch functions, because that the Redux store have already dispatch method.)


//Step1 Create a store which manage all states in app.
const initialState = {count:0};

function counterReducer(prevState=initialState, action){
  console.groupCollapsed('counterReducer(',prevState,',', action,') invoked.'); console.groupEnd();

  // Return a new State
  // Important: 1. if your state is primitive type data, update state with value.
                // 2. If your state is reference type(that is, object)Data, update state with deep copied obj
  switch(action.type){
    case'INCREASE': return {...prevState, count: prevState.count +1} ;
    case'DECREASE': return {...prevState, count: prevState.count -1};
    case'RESET':  return initialState;
    default :return prevState;
  }
}

// 1-2. Define a Redux store with preloadedState(==initial State)
// const preloadedState = {count:0}; 

// const stroe = createStore(counterReducer);  // 1st method: No preloadedState provided
const store = createStore(counterReducer, initialState);  // 2nd. method: preloadedState == initial State
console.log('1.store:', store);  //{dispatch: ƒ, subscribe: ƒ, getState: ƒ, replaceReducer: ƒ, @@observable: ƒ}
console.log('2.store.getState:', store.getState());


//1-3. Set a listener to monitor the changes of the Redux store.
store.subscribe(()=>{
  console.groupCollapsed('Redux store changed. state:', store.getState());console.groupEnd();
});    // Whenever store changed




//Step2Create all necessary action generators that create all required action objects
//       to manage global states. (ex: {type:..., payload:...})

//2-1 Declare an action generator(() => {type,payload?}) for increasing count
const increaseActionGenerator = () => ({type:'INCREASE', payload:undefined});
const decreaseActionGenerator = () => ({type:'DECREASE', payload:undefined});
const resetActionGenerator = () => ({type:'RESET', payload:undefined});



//Step3 Create a reducer function and above => combineReducers function(skipped)
//      'combineReducers' function return only one reducer function which combine several reducer



//Step4 Update state with pre-defined action generators & Redux store's dispatch method

// Try1. Increase count state 3-times subsequently.
const increaseAction = increaseActionGenerator();
console.log('3.increaseAction', increaseAction);

// for(let counter =1; counter <=3; ++counter)
// for( let _ in [1,2,3])
  for( let _ of [1,2,3]){
  store.dispatch(increaseAction);
}


store.dispatch(resetActionGenerator());
store.dispatch(resetActionGenerator());

for(let _ of [1,2,3,4,5]){
  store.dispatch(decreaseActionGenerator());
}

