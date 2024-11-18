
window.addEventListener('load', e => {
    console.clear();
    console.log('1. window load event triggered...');

    const grid = new Isotope ("section", {
        itemSelector: "article", // 배치
        columnWidth: "article", // 너비
        transitionDuration: "0.5s" // 재배치 속도
    }); // grid
    
    console.log('2. grid : ', grid)

    const isotopeBtns = document.querySelectorAll("main ul li")
    console.log ('3. isotopeBtns : ', isotopeBtns)

    // for .. of 공식 이해도 부족
    for (let isotopeBtn of isotopeBtns) {

        isotopeBtn.addEventListener('click', e =>{
            console.clear();
            console.log('>>> isotopeBtn click event triggered...');

            // 이게 뭐죠..
            e.preventDefault();

            // 이건 또 뭐죠..
            const clickedBtnHref = e.currentTarget.querySelector("a").getAttribute("href");
            console.log(`\t+ clsickedBtnHref: ${clickedBtnHref}`);

            // 대괄호,중괄호에 대한 이해 필요
            grid.arrange({filter: clickedBtnHref})

            for(let btn of isotopeBtns) {
                btn.classList.remove("on");
            }

            e.currentTarget.classList.add("on");
        });
    }
})