import { useContext } from "react";
import {ctx} from "../App"


console.groupCollapsed('src/component/Sub3.js'); console.groupEnd();


const Sub3 = (props) => {
    console.groupCollapsed('Sub3(',props,')invoked.'); 
    
    const currentState = useContext(ctx);

    return(
        <div style={currentState}> 
            <h1>Sub3</h1>
        </div>
    );
};

export default Sub3;