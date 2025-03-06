import {styled} from 'styled-components';

console.groupCollapsed('src/App.js'); 
console.log('1.styled:', styled);


//문법 1: const 컴포넌트명 = styled.<표준 태그명> `Template String` ;
//문법 2: const 컴포넌트명 = styled.<표준 태그명>`
//문법 3: 문법2로 만든 컴포넌트에 가상 선택자 (예:hover)를 적용하시려면
//        아래와 같이 합니다:
//
// css블록에서  &: hover{}

// ${(props) => console.log(props)}

const StyledButton = styled.button`
  

  background-color : ${ (props) => props.$primary ? 'gray': 'blue'};

  color : white;
  border : none;
  border-radius : 5px;
  cursor : pointer;
  font-size: 16px;
  font-weight: normal;
  padding: 10px 20px;
  margin: 10px;
  transition: background-color 2s ease;

  &: hover{
    background-color: ${(props)=>{
      if(props.children == 'Button1') return 'red';
      else if(props.children == 'Button2') return 'green';
      else return 'purple';
    }};  
  }
`;

console.log('2.StyledButton',StyledButton);

const LargeStyledButton = styled(StyledButton)`
    font-size : 40px;
`;

const StdButton = (props) => {
  console.debug('StdButton(',props,')invoked.'); console.groupEnd();

  //스타일링되지 않은 리액트 컴포넌트가 부모가 되는 경우, 자식 Styled 컴포넌트는 
  // 부모의 props 객체의 속성으로 className=값을 제공하고
  // 이 props.className 값을 아래와 같이 표준 태그의 className 속성 값으로 지정해주시면
  // 부모가 설령 스타일링되지 않았어도, 자식 컴포넌트에 스타일이 적용됩니다.
  return  <button className={props.className}>StdButton</button>
};

const StyledStdButton = styled(StdButton)`
  font-size : 40px;
`;

console.groupEnd();


const App = (props) => {
  console.groupCollapsed('App(',props,')invoked.'); console.groupEnd();

  return (
    <div className="App">

      <button>Standard Button</button>
      <StyledButton $primary>Button1</StyledButton>
      <StyledButton>Button2</StyledButton>
      <p></p>
      <LargeStyledButton>Large Styled Button</LargeStyledButton>
      <p></p>
      <StdButton></StdButton>
      <p></p>
      <StyledStdButton></StyledStdButton>

    </div>
  );
}

export default App;
