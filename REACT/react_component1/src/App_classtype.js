import React,{Component} from 'react';

console.groupCollapsed('src/App.js'); 
console.log('1.Component:', Component);
console.log('2.React', React)
console.groupEnd();

// Declare a React Component as a class type.

//A란 클래스가 B란 클래스를 상속할 땐, extends 키워드로 
// 아래와 같이 상속 받는다:
// class A extends B{}

// Step1. React.Component class를 상속(extends)받아야, 우리가 만든
//        클래스가 리액트 컴포넌트가 될 수 있습니다.
class App extends Component{

  //Step2. Constructor(생성자)선언(단, 매개변수를 1개 반드시 선언)
  //       'props'란 매개변수는 이거 다음에 배웁니다.
    constructor(props){
      console.groupCollapsed('Step1.constructor(',props,') invoked.');console.groupEnd();
      //Step3. 부모객체의 필드가 초기화 될 수 있도록,
      //        부모클래스에 선언된 생성자 호출
      // super키워드 : 자식객체보다 무조건 먼저 생성되는 부모 객체의
      //              주소를 저장(마치, 참조변수처럼)
      super(props); // super(전달한 변수); => 부모생성자 호출 문법

    }

    //Step3. 이 단계부터 나오는 모든 메소드는 이미 리액트에서 정해놓은 대로 만드는 메소드이고, 이들 메소드의 목적은, 
    //       이 리액트 컴포넌트 클래스로부터 생성될 컴포넌트 객체의 생명주기(=lifecycle)별로, 자동 호출될
    //       callback 선언
    render(){ // 1
      console.groupCollapsed('Step2,step-3.render() invoked.'); console.groupEnd();
      
      //이 메소드에서 return 하는 값은, 우리가 배운 JSX 문법에 따라
      // 브라우저에 렌더링될 태그들을 만들어 반환하는 값입니다.
      return "Tae";
    }

    componentDidMount(){ // 2
      console.groupCollapsed('Step3.componentDidMount() invoked.'); console.groupEnd();

      //강제로 re-rendering 시키는 메소드
      // this.forceUpdate(); //상속받은 메소드 호출
      this.setState(()=>({count:4}));
    }

    shouldComponentUpdate(nextProps,nextState,nestContext){ // 3
      console.groupCollapsed('Step2.shouldComponentUpdate(',nextProps,nextState,nestContext,') invoked.');
      console.groupEnd();

      return true;
    }

    componentDidUpdate(prevProps,prevState,snapshot){ // 4
      console.groupCollapsed('Step5.componentDidUpdate(',prevProps,prevState,snapshot,') invoked.'); console.groupEnd();
    }

    componentWillUnmount(){  // 5
      console.groupCollapsed('Step-1.componentWillUnmount() invoked.'); console.groupEnd();
    }

    componentDidCatch(error,errorInfo){ // 6
      console.groupCollapsed('Stepx.componentDidCatch(',error,errorInfo,') invoked.'); console.groupEnd();
    }

    getSnapshotBeforeUpdate(prevProps,prevState){ //7
      console.groupCollapsed('Step4.getSnapshotBeforeUpdate(',prevProps,prevState,') invoked.'); console.groupEnd();
      
      //스냅샷 값을 반환
      return null;
    }

}

export default App;