console.groupCollapsed('src/component/index.js'); console.groupEnd();

// Important1: JSX component name must be in (1) PascalCase or (2) SCREAMING_SNAKE_CASE. (***)
// Important2: this `index.js` can re-export the component which is exported by default, 
//                       using `export { default [ as alias ] } from <path/to/componentName>` syntax. (***)

export {default as Sub1} from './Sub1';
export {default as Sub2} from './Sub2';
export {default as Sub3} from './Sub3';
