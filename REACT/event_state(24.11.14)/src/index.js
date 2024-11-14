import ReactDOM from 'react-dom/client';

import './index.css';
import App from './App';

console.groupCollapsed('src/index.js'); console.groupEnd();

const root = ReactDOM.createRoot(document.querySelector('#root'));

const style1 ={
    color : '1',
};
const style2 ={
    color : 'green',
};
const style3 ={
    color : 'blue',
};

root.render(
    <>  
    <App imageId={300} imageWidth = {200} imageHeight ={100}style={style1}/>
    <App imageId= {500} imageWidth ={300} imageHeight ={150}style={style2}/>
    <App imageId={700} imageWidth = {300} imageHeight ={200}style={style3}/>
    </>
);

