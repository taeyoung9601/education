import Apartment from "./Apart";

console.groupCollapsed("src/App.js");
console.groupEnd();

function App(props) {
  console.groupCollapsed("function App(", props, ")invoked.");
  console.groupEnd();

  return (
    <div>
      <Apartment name="Tae" desc="Description-1" imgWidth="100" imgHeight="100" />
      <Apartment name="Young" desc="Description-2" imgWidth="200" imgHeight="200" />
      <Apartment name="Kim" desc="Description-3" imgWidth="300" imgHeight="300" />
    </div>
  );
}

export default App;