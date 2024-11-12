// React App 의 Entry Point

import React from 'react';
import ReactDOM from 'react-dom/client';

//그냥 expor{}로 공개된 자원을 가져다 사용할 때에도,
// 동일하게 import{export로만 공개된 자원명} from '모듈 파일명';
import App from'./App';

console.group('src/index.js'); console.groupEnd();
// React' DOM => Virtual DOM
const root = ReactDOM.createRoot(document.querySelector('#root'));
root.render(<App/>);