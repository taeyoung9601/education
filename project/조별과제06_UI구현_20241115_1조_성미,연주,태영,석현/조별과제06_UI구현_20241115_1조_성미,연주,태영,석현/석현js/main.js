// 1. Isotope JS plugin 을 이용한, `floated` elements 의 재배치 수행. (***)
// 2. DOM이 모두 완성된 후에, 이 isotope plugin 이 요소들을 재배치 해야 함. <------- (***)

window.addEventListener('load', e => {
    console.clear();
    console.log('1. window load event triggered...');

    // ------- //

    const grid = new Isotope("section", {   // (1) 배치할 요소를 감싸는 `부모요소명`.
        itemSelector: "article",            // (2) `배치요소명`.
        columnWidth: "article",             // (3) `너비`를 구할 요소명.
        transitionDuration: "0.5s"          // (4) 화면 재배치시, 배치요소들이 움직이는 `속도`.
    }); // grid

    console.log('2. grid:', grid);

    // ------- //

    const isotopeBtns = document.querySelectorAll("main ul li");
    console.log('3. isotopeBtns:', isotopeBtns);

    for(let isotopeBtn of isotopeBtns) {
        
        isotopeBtn.addEventListener('click', e => {
            console.clear();
            console.log('>>> isotopeBtn click event triggered...');

            e.preventDefault();

            const clickedBtnHref = e.currentTarget.querySelector("a").getAttribute("href");
            console.log(`\t+ clickedBtnHref: ${clickedBtnHref}`);

            // -- isotope -------------------------

            grid.arrange({
                filter: clickedBtnHref
            });
            
            // -- isotope -------------------------

            for(let btn of isotopeBtns) {
                btn.classList.remove("on");
            } // for-of (enhanced for)

            e.currentTarget.classList.add("on");

        }); // .addEventListener

    } // for-of (enhanced for)

});

/* ============================================ */




