var buttonTag = document.querySelector('Button');
console.log(buttonTag);
buttonTag.addEventListener('click', handleClickEvent);

function handleClickEvent(event) {
    console.log('handleClickEvent(',event,')invoked.');            
}