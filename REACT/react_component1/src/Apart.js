console.groupCollapsed("src/Apart.js");
console.groupEnd();

function Apartment(props) {
  console.groupCollapsed("Apartment(",props,")invoked.");
  console.groupEnd();

  //ES5: 참조변수명.속성명 형식으로 객체의 속성값 사용
  const name = props.names;
  const desc = props.desc;
  const imgWidth = props.imgWidth;
  const imgHeight = props.imgHeight;

  //ES6+ : 구조분해할당연산
//   const {name,desc,imgWidth,imgHeight} = props;

  return (
    <div>
      <h2>{name}</h2>
      <hr></hr>

      <p>{desc}</p>
      <img
        src="http://picsum.photos/id/1025/600/400"
        alt="dog"
        width= {imgWidth}
        height= {imgHeight}
      />
    </div>
  );
}

export default Apartment;
