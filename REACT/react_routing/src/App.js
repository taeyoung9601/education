//eslint-disalbe-next-line
import {
  Routes,
  Route,
  Link,
  NavLink,
  useParams,
  useLocation,
} from "react-router-dom";

import "./App.css";

console.groupCollapsed("src/App.js");
console.groupEnd();

const Home = (props) => {
  console.groupCollapsed("Home(", props, ") invoked.");
  console.groupEnd();
  return <h1>Home</h1>;
};

const Topics = (props) => {
  console.group("Topics(", props, ")invoked.");

  //react-router-dom 패키지는, 동적 세그먼트 값을
  //받아낼 수 있는 React Hook, 'useParams' 함수를 제공합니다
  const obj = useParams(Topics);
  console.log("1. obj:", obj);

  //아래의 React Hook, 'useLocation'은 <Link/> or <NavLink/> 태그를 통해서
  //

  const location = useLocation();
  console.log("2. location:", location);

  const {
    state: { name, age, hobby ,cellPhone},
  } = location;

  //객체의 구조분해시, 참조변수의 값이 null이 아닐 수도 있으면,
  //아래와 같이, '참조 변수 ||{}'형태로, 논리 연산자 or(또는) 오른쪽에
  // 빈 객체({})를 붙여주시면, 구조 분해시 null,undefined 값으로 발생하는
  // 오류를 해결할 수 가 있습니다.

  const { phone1, phone2, phone3 } = cellPhone||{};

  console.log("3.name", name);
  console.log("4.age:", age);
  console.log("5.hobby:", hobby);
  console.log("6.phone1:", phone1);
  console.log("7.phone2:", phone2);
  console.log("8.phone3:", phone3);
  //state 도 객체 -> name,age,hobby,cellPhone

  console.groupEnd();

  return (
    <>
      <h1>Topics({obj.id})</h1>
      <div>
      <span>1.name:{name}</span>
      <span>2.age:{age}</span>
      <span>3.hobby:{hobby}</span>
      <span>4.phone1:{phone1}</span>
      <span>5.phone2:{phone2}</span>
      <span>6.phone3:{phone3}</span>
      </div>
    </>
    // )
  );
};

const About = (props) => {
  return <h1>About</h1>;
};

const Contact = (props) => {
  return <h1>Contact</h1>;
};

const NotMatched = (props) => {
  return <h1>NotMatched</h1>;
};

function App(props) {
  console.groupCollapsed("App(", props, ")invoked");
  console.groupEnd();

  const topic1 = {
    name: "tae",
    age: 26,
  };
  const topic2 = {
    name: "young",
    age: 27,
    hobby: [1, 2, 3],
  };
  const topic3 = {
    name: "Kim",
    age: 28,
    cellPhone: {
      phone1: "010-0000-0001",
      phone2: "010-0000-0002",
      phone3: "010-0000-0003",
    },
  };

  return (
    <div className="App">
      <h1>React Routing Table</h1>

      <ol>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <NavLink to="nav/t/1" state={topic1}>
            Topics1
          </NavLink>
        </li>
        <li>
          <NavLink to="nav/tt/2" state={topic2}>
            Topics2
          </NavLink>
        </li>
        <li>
          <NavLink to="nav/ttt/3" state={topic3}>
            Topics3
          </NavLink>
        </li>
        <li>
          <NavLink to="nav/contact">Contact</NavLink>
        </li>
        <li>
          <NavLink to="About">About</NavLink>
        </li>
        <li>
          <NavLink to="NotMatched">NotMatched</NavLink>
        </li>
      </ol>

      {/*Routing Container == Routing Table*/}
      <Routes>
        <Route
          path="/"
          element={<Home property1={1 * 100} property2={2 * 100} />}
          caseSensitive={true}
        />
        <Route
          path="nav/:topics/:id"
          element={<Topics />}
          caseSensitive={true}
        />
        <Route path="nav/contact" element={<Contact />} caseSensitive={true} />
        <Route path="About" element={<About />} caseSensitive={true} />
        <Route path="/*" element={<NotMatched />} caseSensitive={true} />
      </Routes>
    </div>
  );
}

export default App;
