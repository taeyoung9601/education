class Person{

    constructor(name,age,height = 170){
        this.myName = name;   // only for the field that have setter and/or getter.
        this.age = age;
        this.height = height;
    }

    walk(meter=0){
        console.debug('walk(',meter,') invoked.');
    }

    //Usage: 참조변수명.get 키워드뒤에 지정한 이름
    get name(){ 
        console.info('get name() invoked');
        // return this.name;  // Getter 무한호출 -> 해결책: 다른이름으로 필드 선언
        return this.myName;
    }
    
    set name(name){
        console.info('set name(',name,')invoked.');
        // this.name = name;  // Setter 무한호출
        this.myName = name;
    }
}

///////////
                // 생성자 호출됨 by new 연산자
const person = new Person("Tae", 26);
console.log('person:', person);

person.name="young";

console.log('person.name:', person.name);  // OK  : Getter 메소드 호출
console.log('person.myName:', person.myName);  // Ok : 직접 필드

person.walk(100);
person.walk();

// person.name='Trinity';
// console.log('person.name:',person.name);

// 1st. test : getter/setter 메소드는 직접호출 가능한가?
// person.get name(); // XX
// person.set name(); // XX
// person.getname(); // XX
// person.setname(); // XX