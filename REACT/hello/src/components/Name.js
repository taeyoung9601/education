console.groupCollapsed('src/components/Name.js'); console.groupEnd();

function Name() {
    console.groupCollapsed('Name() invoked.'); console.groupEnd();

    const myName = "Tae" + ", Kim"
    return <p> myName:{myName}</p>
}

export default Name;