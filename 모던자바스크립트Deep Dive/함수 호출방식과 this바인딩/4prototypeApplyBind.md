# 4.Function.prototype.apply/call/bind 메서드에 의한 간접 호출

apply, call, bind 메서드는 Function.prototype의 메서드다. 즉 이들 메서드는 모든 함수가 상속받아 사용할 수 있다.

 Function.prototype.apply, [Function.prototype.call](http://function.prototype.call) 메서드는 this로 사용할 객체와 인수 리스트를 인수로 전달받아 함수로 호출한다. apply와 call 메서드의 사용법은 다음과 같다.

```jsx
/**
 * 주어진 this 바인딩과 인수 리스트 배열을 사용하여 함수를 호출한다.
 * @param thisArg - this로 사용할 객체
 * @param argsArray - 함수에게 전달할 인수 리스트의 배열 또는 유사 배열 객체
 * @returns 호출된 함수의 반환값
 */
Function.prototype.apply(thisArg[, argsArray])

/**
 * 주어진 this 바인딩과 , 로 구분된 인수 리스트를 사용하여 함수를 호출한다.
 * @param thisArg - this로 사용할 객체
 * @param arg1, arg2, ...  - 함수에 전달할 인수 리스트
 * @returns 호출된 함수의 반환값
 */
Function.prototype.call(thisArg[, arg1[,arg2[,[...]]]])
```

```jsx
function getThisBinding() {
    return this;
}

//this로 사용할 객체
const thisArg = { a: 1 };
console.log(getThisBinding());// Window

//getThisBinding 함수를 호출하면서 인수로 전달한 객체를 getThisBinding 함수의 this에 바인딩한다
console.log(getThisBinding.apply(thisArg)); // { a: 1}
console.log(getThisBinding.call(thisArg)); // { a: 1}
```

apply와 call 메서드의 본질적인 기능은 함수를 호출하는 것이다.

apply와 call 메서드는 함수를 호출하면서 첫 번째 인수로 전달한 특정 객체를 호출한 함수의 this 에 바인딩한다.

apply와 call은 인수를 전달하는 방식만 다를 뿐 동일하게 동작한다. 위 예제는 인자를 전달하지 않는다

```jsx
function getThisBinding() {
    a = 100;
    console.log(arguments);
    return this;
}
console.log(getThisBinding().a); // 100
//this로 사용할 객체
const thisArg = { a: 1 };

//getThisBinding 함수를 호출하면서 인수로 전달한 객체를 getThisBinding 함수의 this에 바인딩한다.
// apply 메서드는 호출할 함수의 인수를 배열로 묶어 전달한다.
console.log(getThisBinding.apply(thisArg, [1, 2, 3]));// Arguments(3) [1,2,3] , { a: 1 }

// call 메서드는 호출할 함수의 인수를 쉼표로 구분한 리스트 형식으로 전달
console.log(getThisBinding.call(thisArg, 1, 2, 3));// Arguments(3) [1,2,3] , { a: 1 }
```

bind 메서드는 메서드의 this와 메서드 내부의 중첩 함수 또는 콜백 함수의 this가 불일치하는 문제를 해결하기 위해 유용하게 사용한다. 

apply와 call 과는 달리 함수를 호출하지 않고 this로 사용할 객체만 전달한다.

```jsx
function getThisBinding() {
    a = 100;
    return this;
}

const thisArg = { a: 1 }
console.log(getThisBinding.bind(thisArg));//bind 메서드는 함수를 호출하지 않는다.

console.log(getThisBinding().a );// a = 100
//bind 메서드는 함수를 호출하지 않으므로 명시적으로 호출해야 한다.
console.log(getThisBinding.bind(thisArg)().a); // a = 1
```

bind 메서드는 메서드의 this와 메서드 내부의 중첩함수 또는 콜백 함수의 this가 불일치 하는 문제를 해결할 때 유용하게 사용된다!

```jsx
const person = {
    name: 'Lee',
    foo(callback) {
        // 1. 이 시점에서의 this는 foo 를 호출한 객체, 즉 person을 가리킨다.
        console.log(`hi my name is ${this.name}`)
        setTimeout(callback, 100);
    }
}

person.foo(function () {
    // 2. 이 시점에서 this는 전역 객체 window를 가르킨다.!
    console.log(`hi my name is ${this.name}`); // window.name의 기본값은 ''이다.
})
```

위의 예제와 같이 this가 다른 것을 확인 할 수 있다.

이 문제를 해결하기 위해서

bind 메서드를 사용하여 this를 일치시킬 수 있다.

```jsx
const person = {
    name: 'Lee',
    foo(callback) {
        console.log(`hi my name is ${this.name}`)
        setTimeout(callback.bind(this), 100); // this 바인딩을 전달
    }
}

person.foo(function () {
    console.log(`hi my name is ${this.name}`); // Lee => this가 일치 된 것을 확인 할 수 있다.
})
```