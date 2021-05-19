# 함수 호출방식과 this바인딩

난이도: 어려움
상태: 자바스크립트
설명: 자바스크립트 함수 호출 방식과 this 바인딩
작성일시: 2021년 5월 19일 오전 11:46
진행: DOING

this바인딩은 함수 호출 방식, 즉 함수가 어떻게 호출되었는지에 따라 동적으로 결정 된다.

`렉시컬 스코프와 this바인딩은 결정 시기가 다르다`
함수의 상위 스코프를 결정하는 방식인 렉시컬 스코프는 함수 정의가 평가되어 함수 객체가 생성되는 시점에 상위 스코프를 결정한다. 하지만 this바인딩은 함수 호출 시점에 결정된다.

함수를 호출하는 방식은 다음과 같다

[<1. 일반 함수 호출>](1일반함수호출.md)

[<2. 메서드 호출>](2메서드로호출.md)

[<3.생성자 함수 호출>](3생성자함수로호출.md)

[<4.Function.prototype.apply/call/bind 메서드에 의한 간접 호출>](4protoTypeApplyBind.md)

```python
// this 바인딩은 함수 호출 방식에 따라 동적으로 결정된다.

const foo = function () { 
    console.dir(this);
}
// 동일한 함수도 다양한 방식으로 호출할 수 있다.
// 1. 일반 함수 호출
// foo함수를 일반적인 방식으로 호출
// foo 함수 내부의 this는 전역 객체 window를 가리킨다.
foo();//window

// 2.메서드 호출
// foo 함수를 프로퍼티 값으로 할당하여 호출
// foo 함수 내부의 this는 메서드를 호출한 객체 obj를 가리킨다
const obj = { foo };
obj.foo();//obj

// 3.생성자 함수 호출
// foo 함수를 new 연산자와 함께 생성자 함수로 호출
// foo 함수 내부의 this는 생성자 함수가 생성한 인스턴스를 가리킨다.
new foo();//foo{}

// 4.Function.prototype.apply/call/bind 메서드에 의한 간접 호출
// foo 함수 내부의 this는 인수에 의해 결정된다.
const bar = { name: 'bar' };
foo.call(bar);   // bar
foo.apply(bar);  // bar
foo.bind(bar)(); // bar
```



