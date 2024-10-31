id = prompt('아이디 입력');

if(id =='admin') {
    password=prompt('비밀번호 입력');
    
    if(password ==='123456') {
        location.href="http://naver.com";
    } else { 
        location.href="http://daum.net";
    }
} else {
    location.href="http://youtube.com";
}