console.groupCollapsed('src/components/Weight.js'); console.groupEnd();

function Weight() {
    console.groupCollapsed('Weight() invoked.'); console.groupEnd();

    let weight =  myWeight();
    return <p>weight:{myWeight()}</p>
}

const myWeight = () => 57.2; //myWeight(): number

export default Weight;