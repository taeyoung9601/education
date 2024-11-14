import {Component,} from 'react';
import PropTypes from 'prop-types';
import './App.css';
// import {myName} from './temp';


// 별도설치된 패키지에서 임포트 하실 때에는,
// from 뒤에 모듈명이 오는게 아니라(왜?모르니까..),
// 패키지명을 지정하시면 됩니다.
import Swal from 'sweetalert2';  //ES방식의 import/export


console.group('src/App.js'); 
console.log('PropTypes',PropTypes); //Object
console.groupEnd();


// const react = require('react');
// console.log('1.react:',react);

// const temp = require('react');  //CommonJS 방식
// console.log('2.myName:',myName);


function App(props) {
  console.groupCollapsed('App(',props,')invoked.');console.groupEnd();

  const {imageId,imageWidth,imageHeight,style} = props;


  function onClick(){
    console.debug('onClick()invoked.');

    // 'success'|'error'|'warning'|'info'|'question'
    Swal.fire(
      'Clicked',
      `<img src="https://picsum.photos/id/${imageId}/${imageWidth}/${imageHeight}" alt="">`,
      'warning'
    );
  };

  return (
    // Important:All properties of a tag in JSX must stick to the rule, Calmel.
    <button onClick={onClick} style={style}>Click Me</button>
  );
}

App.propTypes = {  //new attribute -> new field
  imageId : PropTypes.number.isRequired,
  imageWidth : PropTypes.number,
  imageHeight : PropTypes.number,
  style : PropTypes.shape({
    color:PropTypes.string.isRequired,

  }),
}

//클래스 컴포넌트 구현 시, 최소한 7개의 생명주기 callback 
//중에 1개의 콜백이 무조건 선언되어야 합니다. : render callback


// class App extends Component{

//   constructor(props){
//     console.groupCollapsed('constructor(',props,') invoked.'); console.groupEnd();
//     super(props);
//     //props 객체는 읽기 전용 객체 -> 수정불가한 객체이다
//     //Immutable Object
//     // props.myName='Tae';
//     // props.myAge = '26';
//   }

//   render(){
//     console.groupCollapsed('render() invoked.'); console.groupEnd();

//     const {imageId} = this.props;


//        function onClick (){
//          console.debug('onClick()invoked.');
    
//          // 'success'|'error'|'warning'|'info'|'question'
//         Swal.fire(
//          'Clicked',
//            `<img src="https://picsum.photos/id/${imageId}/300/200" alt="">`,
//            'warning'
//          );
//        };
    
//        return (
//         //  Important:All properties of a tag in JSX must stick to the rule, Calmel.
//          <button onClick={onClick}>Click Me</button>
//         );
//   }

// }


export default App;

