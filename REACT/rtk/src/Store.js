import {createSlice,configureStore} from '@reduxjs/toolkit';

console.groupCollapsed('src/Store.js'); console.groupEnd();

//Using @reduxjs/toolkit package, create a Redux store with several slices.

//Step1. Create some slices. (2 slices: count.age)

//1-1. create a slice for count
const initialStateCount = {count:0};
const countSlice = createSlice({
    name: "countSlice",
    initialState: initialStateCount,
    reducers:{
        up:(state) => {state.count +=1},
        down:(state) => {state.count -=1;},
        reset:(state) => {state.count =0 ;},

        //우리가 알듯이, 객체의 속성 값이
    }
})


console.log('1.countSlice:', countSlice);
console.log('2.name:', countSlice.name);
console.log('3.initialState:', countSlice.getInitialState());
console.log('4.caseReducers:', countSlice.caseReducers);
console.log('5.actions:', countSlice.actions);
console.log('5-1.up():', countSlice.actions.up());
console.log('5-2.down():', countSlice.actions.down());
console.log('5-3.reset():', countSlice.actions.reset());
console.log('6.reducer:', countSlice.reducer);
console.log('7.reducerPath:', countSlice.reducerPath);


//(1). Get all action creators
const {up, down, reset} = countSlice.actions;
//(2) Get a sing reducer function which have all sub-reducers
const countReducer = countSlice.reducer;


// ====다음에 두번째 나의 나의 상태를 저장하는 슬라이스 생성
//     위의 countSlice와 거의 대동소이하게..

const initialStateAge = {age:26};
const ageSlice = createSlice({
    name: "ageSlice",
    initialState: initialStateAge,
    reducers: {
        increase:(prevState) => {++ prevState.age},
        decrease:(prevState) => {-- prevState.age},
        reset : (prevState) => {prevState.age = initialStateAge.age}
    }
})

const{increase:increaseAge,decrease:decreaseAge,reset:resetAge} = ageSlice.actions;
const ageReducer = ageSlice.reducer;
const ageReducerPath = ageSlice.reducerPath;

//Create a Redux store with several slices.
const store = configureStore({
    // reducer: countReducer,   // 1st. try: this reducer is called 'Root reducer'.
    reducer: {                  // 2nd. try: If reducer prop have several reducers of some slices.
       
        // countReducer: countReducer // 리듀서 함수명이 상태 객체를 가지는 속성명
        countSlice: countReducer,
        ageSlice: ageReducer,

    }
});

store.subscribe(()=>{
    console.groupCollapsed('Redux store changed. state:', store.getState()); console.groupEnd();
});



export default store;
export {countSlice, ageSlice};
export {up,down,reset};   // Export action creators of countSlice
export { increaseAge,decreaseAge,resetAge};