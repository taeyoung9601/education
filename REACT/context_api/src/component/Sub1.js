import { useContext, createContext } from "react";
import {ctx} from "../App"
import {Sub2} from ".";


console.groupCollapsed('src/component/Sub1.js'); console.groupEnd();


const Sub1 = (props) => {
    console.groupCollapsed('Sub1(',props,')invoked.'); console.groupEnd();

    const currentState = {
        border: '5px dotted brown',
    };

    return(
        <ctx.Provider value={currentState}>

        <div id='sub1'style={currentState}>
            <h1>Sub1</h1>
            <Sub2/>
        </div>

        </ctx.Provider>
    );
};

export default Sub1;