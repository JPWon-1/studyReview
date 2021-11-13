# Node 추가

```jsx
const $fruits = document.getElementById('fruits');
const $container = document.createElement('div');
const $fragment = document.createDocumentFragment();
['appple','banana','orange'].forEach(text=>{
    const $li = document.createElement('li');
    const textNode = document.createTextNode(text);
    $li.appendChild(textNode);
    $fragment.appendChild($li)
})
$fruits.appendChild($fragment)
```

fragment와 같이 빈 노드를 추가하는 식으로 불필요한 컨테이너를 생성하지 않고도 객체 추가할 수 있다.

replaceChild 함수를 쓰는 방식으로 하면 새로 파싱할때마다 초기화를 안해줘도 될 것 같다!