console.clear();

const section = document.querySelector("section");
const articles = document.querySelectorAll("article");

console.log('1. section:', section);
console.log('2. articles:', articles);

const length = articles.length - 1;
const degree = 30;

let index = 0;

for(let article of articles) {

    let pic = article.querySelector('.pic');

    // 8개의 각 패널을 45도 회전시키고, 각 패널을 세로축(Y축) 방향으로 위로 Viewport높이만큼 이동시킴. (***)
    article.style.transform = `rotate(${degree * index}deg) translateY(-100vh)`;

    // 8개의 각 패널의 배경이미지 각각 설정
    pic.style.backgroundImage = `url(img/별자리_${index+1}.jpg)`;

    let play    = article.querySelector('.play');
    let pause   = article.querySelector('.pause');
    pause.classList.add('display_none');

    play.addEventListener('click', e => {
        console.log('click play');
        console.log('e.currentTarget: ', e.currentTarget);

        // console.log('e.currentTarget.previousSibling: ', e.currentTarget.previousSibling); //이전 text 출력 : \n
        // console.log('e.currentTarget.previousElementSibling: ', e.currentTarget.previousElementSibling); // 이전 형제tag 출력 : li .pause
        // console.log('e.currentTarget.nextSibling: ', e.currentTarget.nextSibling); //다음 text 출력 : \n
        // console.log('e.currentTarget.nextElementSibling: ', e.currentTarget.nextElementSibling); // 다음 형제tag 출력 : null - 다음 형제 없음.       
        // console.log('e.currentTarget.parentNode: ', e.currentTarget.parentNode); //부모tag출력
        // console.log('e.currentTarget.parentElement: ', e.currentTarget.parentElement); //상동
        // console.log('e.currentTarget.childNodes: ', e.currentTarget.childNodes);
        // console.log('e.currentTarget.children: ', e.currentTarget.children);

        //.closest('article') : 클릭 타겍 가까운 곡에 위치한 article
        //.classList.contains('on') : on class가 포함되어 있는지 확인
        let isActive = e.currentTarget.closest('article').classList.contains('on');
        
        console.log('isActive: ', e.currentTarget.closest('article').classList.contains('on'));

        if(isActive) {
            article.querySelector('.pic').classList.add('on');
            article.querySelector('.txt audio').play(); 
    
            // 버튼 상태 변경: play -> pause
            e.currentTarget.classList.add('display_none');
            e.currentTarget.previousElementSibling.classList.remove('display_none');
        }
    });

    pause.addEventListener('click', e => {
        console.log('click pause');
        console.log('e.currentTarget: ', e.currentTarget);
    
        let isActive = e.currentTarget.closest('article').classList.contains('on');
    
        if (isActive) {
            article.querySelector('.pic').classList.remove('on');
            article.querySelector('.txt audio').pause();
    
            // 버튼 상태 변경: pause -> play
            e.currentTarget.classList.add('display_none');
            e.currentTarget.nextElementSibling.classList.remove('display_none');
        }
    });

    ++index;
}//end for



const btnPrev = document.querySelector('.btnPrev');
const btnNext = document.querySelector('.btnNext');

function activation(index, list) {
    console.log(`activation(${index}, ${list}) invoked.`);
    for(let el of list){
        el.querySelector('.play').classList.remove('display_none');
        el.querySelector('.pause').classList.add('display_none');
        el.classList.remove('on');//article .on 삭제
    }
    list[index].classList.add('on');
} //end activation


const audios = document.querySelectorAll('audio');//오디오 다 찾음..

//뮤직 이미지 정지
function initMusic() {
    console.log('initMusic() ivoked');

    for(let audio of audios) {
        audio.pause();
        audio.load();

        //audio 상위 이전 형제(.pic) .on class 삭제, 이미지 회전 정지
        audio.parentElement.previousElementSibling.classList.remove('on');
    } // for-of (enhanced for)
    
} // initMusic

let active = 0;
let number = 0;

btnPrev.addEventListener('click', e => {
    console.log('click btnPrev');

    initMusic();

    number++;
    section.style.transform = `rotate(${degree * number}deg)`;

    // active 값 변경 (순차적으로 이동)
    active = (active === 0) ? articles.length - 1 : active - 1;

    // 해당 article 활성화
    activation(active, articles);
});

btnNext.addEventListener('click', e => {
    console.log('click btnNext');

    initMusic();

    number--;
    section.style.transform = `rotate(${degree * number}deg)`;

    // active 값 변경 (순차적으로 이동)
    console.log('active'); 
    active = (active === articles.length - 1) ? 0 : active + 1;

    // 해당 article 활성화
    activation(active, articles);
});
