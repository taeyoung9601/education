import ReactDOM from 'react-dom/client';
import {Provider} from 'react-redux'

import App from './App';
import {default as ReduxStore}  from './Store'

console.groupCollapsed('src/index.js'); console.groupEnd();

const root = ReactDOM.createRoot(document.querySelector('#root'));
root.render(
  <Provider store={ReduxStore}>
    <App />
  </Provider>
);