# Symbol & iterable

javascript에서 지원 안해주는 enum을 흉내낼수도 있고, property값을 은닉할수도 있으며

중복되지 않는 상수를 생성할 수 있다. 하위호환을 위해 만들어진 타입? 이랄까..

사용방법은 아직 더 공부해봐야 할 것 같다.

iterable로 구현한 fibonacci 수열

```jsx
const fibo = function (max) {
    let [pre, cur] = [0, 1];
    return {
        [Symbol.iterator]() {
            return {
                next() {
                    [pre, cur] = [cur, pre + cur];
                    return { value: cur, done: cur >= max };
                },
            };
        },
    };
};

for(const num of fibo(15)){
    console.log(num);
}
```

iterator면서 iterable한 피보나치 수열

```jsx
const fibo = function(max){
    let [pre,cur] = [0,1];
    return{
        [Symbol.iterator](){
            return this;
        },
        next(){
            [pre,cur] = [cur,cur+pre];
            return{value:cur,done:cur>=max};
        }
    }
}

let iter = fibo(10);
for(const num of iter){
    console.log(num);
}
```