import React from 'react';
import ReactDOM from 'react-dom/client';

import App from'./App';

console.group('src/index.js'); console.groupEnd();

const root = ReactDOM.createRoot(document.querySelector('#Tae'));
root.render(<App/>);