# Date를 읽을 수 있는 형태로

난이도: 보통
상태: 자바스크립트
설명: Date 함수
작성일시: 2021년 3월 15일 오후 11:28
진행: DONE

```jsx
const today = new Date('2021/03/15/11:23')
today.toString(); // Mon Mar 15 2021 11:23:00 GMT+0900 (대한민국 표준시)
today.toDateString(); // Mon Mar 15 2021
today.toTimeString(); // 11:23:00 GMT+0900 (대한민국 표준시)
today.toISOString(); // 2021-03-15T02:23:00.000Z
today.toUTCString(); // Mon, 15 Mar 2021 02:23:00 GMT

today.toISOString().slice(0,10); // 2021-03-15
today.toISOString().slice(0,10).replace(/-/g,''); //20210315
```

toLocaleString은 로컬 기준으로 시간을 적용한다. 

```jsx
const today = new Date('2021/03/15/11:23')
today.toLocaleString(); // 2021. 3. 15. 오전 11:23:00
today.toLocaleString('ko-KR');
today.toLocaleString('en-US');
today.toLocaleString('ja-JP');
등 괄호 안에 지역을 써서 그 지역의 로컬 데이트를 가져올 수 있다.

toLocaleTimeString을 사용하면 인수로 전달한 로컬 기준으로 시간을 표현한 문자열을 반환한다.
```