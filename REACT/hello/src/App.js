import Age from'./components/Age';
import Gender from './components/Gender';
import Name from './components/Name';
import Weight from './components/Weight';
import Height from './components/Height';
import HorizontalLine from './components/HorizontalLine';

import './css/App.css'

console.groupCollapsed('src/App.js'); console.groupEnd();

// important: imported JSX component must be in (1) PascalCase or (2) SCREAMING__SNAKE_CASE
//(1)PascalCase: 첫 단어의 첫 문자부터 대문자로 시작 (예: HelloWorld)
//(2)SCREAMING_SNAKE_CASE: 모든단어가 다 100% 대문자로 표기
// Declare React functional Component

// const PI = 3.14159 ;  // 상수명은 관례상 뱀표기법(즉,모든 단어가 100% 대문자)

// //함수명은 PascalCase
// function Tae(){  
//     console.groupCollapsed('Tae() invoked.'); console.groupEnd();

//     const width = 300;  //이미지의 가로너비
//     const height = 300;  // 이미지의 세로높이

//     //문자열 ok
//     // number ok
//     //boolean x (appeared nothing)
//     // object x (appeared nothing)
//     // Thus, functional/class React component must return value as JSX.
//     // JSX : javascipt and XML abbreviation
//     return <div>
//         <h1>Boat</h1>
//         <img src="https://picsum.photos/id/598/300/300" art = {'This is a image.'}></img>
//     </div>
// };

// export default PI;
// //기본이 아닌 일반 공개자원은 아래와 같이, export 키워드 뒤에 중괄호 기호로 공개
// export {Tae};

function App(){  
        console.groupCollapsed('App() invoked.'); console.groupEnd();

        return(
            <>
            <HorizontalLine/>
            <Name/>
            <Age/>
            <Weight/>
            <Height/>
            <Gender/>
            <HorizontalLine/>
            </>
        );

}

export default App;