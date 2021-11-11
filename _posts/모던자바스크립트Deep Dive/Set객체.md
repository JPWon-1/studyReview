# Set객체

Set객체는 배열과 다르게

1. 동일한 값을 중복하여 포함할 수 없다.
2. 요소 순서에 의미가 없다
3. 인덱스로 요소에 접근할 수 없다

이러한 Set의 특성은 수학적 집합의 특성과 일치한다. Set 은 수학적 집합을 구현하기 위한 자료구조다. 따라서 Set을 통해 교집합, 합집합, 차집합, 여집합 등을 구현할 수 있다.

중복을 허용하지 않는 객체 특성을 활용하여 배열에서 중복된 요소를 제거할 수 있다.

```jsx
const uniq1 = array => array.filter((v,i,self)=>self.indexOf(v)===i);
console.log(uniq1([2,1,2,3,4,3,4,2])); //[2,1,3,4]

const uniq2 = array=>[...new Set(array)];
console.log(uniq2([2,1,2,3,4,3,4,2])); //[2,1,3,4]
```

Set 순회

```jsx
const set = new Set([1,2,3]);

set.forEach((v1,v2,set)=>
    console.log(v1,v2,set)
)//v1,v2는 같은 값이다. set은 자기 자신을 반환한다.

for(const value of set){
    console.log(value);
}//set은 이터러블 객체이기에 for of 로 순회할 수 있다.
```

교집합

```jsx
Set.prototype.intersection = function(set){
    const result = new Set();
    for(const value of set){
        //2개의 set의 요소가 공통되는 요소이면 교집합의 대상이다.
        if(this.has(value)) result.add(value);
    }
    return result;
}
const setA = new Set([1,2,3,4]);
const setB = new Set([2,4]);

console.log(setA.intersection(setB));//{2,4}
console.log(setB.intersection(setA));//{2,4}
```

```jsx
Set.prototype.intersection = function(set){
    return new Set([...this].filter(v=>set.has(v)));
}

const setA = new Set([1,2,3,4]);
const setB = new Set([2,4]);

console.log(setA.intersection(setB));//{2,4}
console.log(setB.intersection(setA));//{2,4}
```

합집합

```jsx
Set.prototype.union = function(set){
    const result = new Set(this);
    for(const value of set){
        result.add(value);
    }
    return result;
};
const setA = new Set([1,2,3,4]);
const setB = new Set([2,4]);

console.log(setA.union(setB));
console.log(setB.union(setA));
```

```jsx
Set.prototype.union = function(set){
    return new Set([...this,...set]);
};
const setA = new Set([1,2,3,4]);
const setB = new Set([2,4]);

console.log(setA.union(setB));
console.log(setB.union(setA));
```

차집합

```jsx
Set.prototype.difference = function(set){
    const result = new Set(this);
    for(const value of set){
        result.delete(value);
    }
    return result;
}
const setA = new Set([1,2,3,4]);
const setB = new Set([2,4]);
console.log(setA.difference(setB)); //Set(2) {1,3}
console.log(setB.difference(setA)); //Set(0) {}
```

```jsx
Set.prototype.difference = function(set){
    return new Set([...this].filter(v=>!set.has(v)))
}
const setA = new Set([1,2,3,4]);
const setB = new Set([2,4]);
console.log(setA.difference(setB)); //Set(2) {1,3}
console.log(setB.difference(setA)); //Set(0) {}
```

부분집합과 상위집합

```jsx
//this가 subset의 상위 집합인지 확인한다.
Set.prototype.isSuperset = function(subset){
    for(const value of subset){
        if(!this.has(value)) return false;
    }
    return true;
};
const setA = new Set([1,2,3,4]);
const setB = new Set([2,4]);

console.log(setA.isSuperset(setB));//true
console.log(setB.isSuperset(setA));//false
```

```jsx
Set.prototype.isSuperset = function(subset){
    const supersetArr = [...this];
    return [...subset].every(v=>supersetArr.includes(v));
};
const setA = new Set([1,2,3,4]);
const setB = new Set([2,4]);

console.log(setA.isSuperset(setB));//true
console.log(setB.isSuperset(setA));//false
```