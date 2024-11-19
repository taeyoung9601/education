import { useContext ,createContext} from "react";
import {ctx} from "../App"
import {Sub3} from ".";


console.groupCollapsed('src/component/Sub2.js'); console.groupEnd();


const Sub2 = (props) => {
    console.groupCollapsed('Sub2(',props,')invoked.'); console.groupEnd();

    const currentState = {
        border: '10px outset red',
    }

    return(
        <ctx.Provider value={currentState}>

        <div style={currentState}>
            <h1>Sub2</h1>
            <Sub3/>
        </div>

        </ctx.Provider>
    );
};

export default Sub2;