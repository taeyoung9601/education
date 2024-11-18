import React from 'react';
import ReactDOM from 'react-dom/client';
import {
  //BrowserRouter as Router,
  HashRouter as Router,} from 'react-router-dom';

import './index.css';
import App from './App';


console.groupCollapsed('src/index.js');console.groupEnd();


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Router future={{v7_startTransition:true}}>
    <App />
  </Router>
);