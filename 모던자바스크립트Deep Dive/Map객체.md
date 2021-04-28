# Map객체

난이도: 보통
상태: 자바스크립트
작성일시: 2021년 3월 27일 오후 12:51
진행: DONE

Map 객체는 Map 생성자 함수로 생성한다.

Map은 키와 값으로 이루어진 요소로 구성되어야 한다

```jsx
const map1 = new Map([['key1','value1'],['key2','value2']]);
console.log(map1); //Map(2){"key1"=>"value1", "key2"=>"value2"}
```

중복된 키는 Map객체에 요소로 저장되지 않는다

```jsx
const map1 = new Map([['key1','value1'],['key1','value2']]);
console.log(map1);//Map(1){"key1"=>"value1"}
```

객체에 요소를 추가할때는 Map.prototype.set 메서드를 사용한다

```jsx
const map = new Map();
map.set('key1','value1');
console.log(map); //Map(1) {"key1"=>"value1"}
```

Map객체는 키 타입에 제한이 없다. 따라서 객체를 포함한 모든 값을 키로 사용할 수 있다.

```jsx
const map = new Map();
const lee = {name:'Lee'};
const kim = {name:'kim'};

map.set(lee,'developer')
    .set(kim,'cook')
console.log(map);
//Map(2) {{…} => "developer", {…} => "cook"}
// [[Entries]]
// 0: {Object => "developer"}
// key: {name: "Lee"}
// value: "developer"
// 1: {Object => "cook"}
// key: {name: "kim"}
// value: "cook"
```

순회

```jsx
const map = new Map();
const lee = {name:'Lee'};
const kim = {name:'kim'};

map.set(lee,'developer')
    .set(kim,'cook')

map.forEach((v,k,map)=>
    console.log(v,k)
)
//developer {name: "Lee"}
//cook {name: "kim"}

for(const entry of map){
    console.log(entry);
}
//[{name:"lee"}, "developer"]
//[{name:"kim"}, "cook"]
```

스프레드 문법과 배열 디스트럭쳐링 할당의 대상이 될 수 있다

```jsx
for(const entry of map){
    console.log(entry);
}

console.log([...map]);
//(2) [Array(2), Array(2)]
// 0: (2) [{…}, "developer"]
// 1: (2) [{…}, "cook"]
const [a,b] = map;
console.log(a,b);
//[{…}, "developer"]
// 0: {name: "Lee"}
// 1: "developer"

// (2) [{…}, "cook"]
// 0: {name: "kim"}
// 1: "cook"
```

객체 반환 메서드 keys() , values() , entires() 를 제공하기도 한다

```jsx
for(const key of map.keys()){
    console.log(key);
}

for(const value of map.values()){
    console.log(value);
}

for(const entry of map.entries()){
    console.log(entry);
}
```