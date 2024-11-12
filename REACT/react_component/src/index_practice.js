console.groupCollapsed('src/index.js'); console.groupEnd();

//ES6+ class syntax

//클래스 정의: 객체의 설계도이다!! (비유: 붕어빵 금형틀)
// 객체의 정의 : 클래스(객체의 설계도)에서 찍어낸(생성한) 인스턴스
//            (instance == Object)

//클래스를 선언하기 위해 준비된 언어상 키워드 목록:
//(1)class (2)constructor (3)get (4)set (5)static (6)#

//(1)뼈대코드(sub codes)
// class 클래스명{
//  (2) 생성자 선언
//  constructor(매개변수선언부){//필드(=속성)선언 및 초기화 블록
//    가.필드(속성)선언: this.필드명(=속성명)
//       this.필드명 = 매개변수;
//    나.필드(속성)초기화: 매개변수로 받은 인자 값으로 초기화
//    다.필드(속성)의 이름과 , 매개변수의 이름이 같을 필요는 전혀 없으나,
//       관례상 필드(속성)의 이름과 매개변수의 이름을 똑같이 선언합니다.
//  } // 생성자

//    (3)  메소드 선언 : 함수형태로 구현
//      문법 : 메소드명(매개변수 선언부){
//              return 문으로 값을 반환할 수 있습니다.
//          }
//
//     (4)Getter / Setter 메소드 선언
//          가. Getter 메소드: 속성값을 반환하는 역할의 메소드
//          나. Setter 메소드: 속성값을 변경하는 역할의 메소드
//
//       문법 : get 속성명{}                 // Getter 메소드
//       문법 : set 속성명(매개변수선언부){}  // Setter 메소드
//
// } // 클래스


class 사람{
  //속성 선언: 이 속성을 OOP언어에서는 "필드(field)"라고 부릅니다.

  // 일반 함수이든, 클래스의 생성자 함수이든
  // 모든 종류의 매개변수 선언부에 선언된 매개변수는
  // 정상적인 외부인자를 받지 못했을 경우를 대비해서,
  // 기본값(Default Value)을 지정할 수 있다!
  
  //(2)생성자 선언
  constructor(name111, age222, height = 170){// 생성자
    this.name = name111;  // name 필드 선언 및 초기화 => 필드 정의
    this.age = age222;  // age 필드 선언 및 초기화 => 필드 정의
    this.height = height;
  }
  //(3)메소드 선언
  걷다(거리=0){
    console.debug('걷다(',거리,')호출됨.');
  }

  //(3)메소드 두번째 선언 : 클래스 설계시, 메소드는 함수로 구현불가
 // this.뛰다: function(거리){}

 // (4)Getter / Setter 메소드
  get name(){
    console.debug('get name()이 호출됨.');
    return this.name;
  }

}

//           new 생성자(인자값들..);  <-- 이건 문법입니다!
const 강사 = new 사람("Tae", 26);
console.log('강사:', 강사);

강사.걷다(100);
강사.걷다();  //거리 매개변수에 값을 안줌.
const name = 강사.name;
console.log('name', name);