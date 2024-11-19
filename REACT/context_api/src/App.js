import { createContext, useContext } from "react";
import {Sub1} from './component'

import './App.css';

console.groupCollapsed('src/App.js');console.groupEnd();

//Step1. Global State Context (=React Context) Creation with createContext() function.
// const initialState = {
//   border: '10px solid green'
// };
const context = createContext();


const MyApp = (props) => {
  console.groupCollapsed('MyApp(',props,')invoked.');  console.groupEnd();

  return (
    <context.Provider>

    <div className="root">
        <h1>MyApp</h1>

        <Sub1/>
    </div>

    </context.Provider>
  );
}

export default MyApp;
export {context as ctx};