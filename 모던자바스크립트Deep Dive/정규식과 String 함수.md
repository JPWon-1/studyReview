# 정규식과 String 함수

정규 표현식은 일정한 패턴을 가진 문자열의 집합을 표현하기 위해 사용하는 형식언어이다.

**`/reg/i`**  

맨 뒤 i 는 플래그이다

플래그 종류는 

i: ignore case 대소문자 구분 안함

g: global 글로벌 문자열 내에서 일치하는 모든 문자열을 검색한다

m: multi line 문자열의 행이 바뀌더라도 패턴 검색을 계속한다

임의의 문자열 검색

```jsx
const target = "Is this all there is?';
const regExp = /.../g;
target.match(regExp); // ["Is","thi","s a","li ","the"."re "."is?"]

.은 임의의 문자 한개를 의미한다. 내용이 무엇이든 상관없다.
```

반복검색

```jsx
const target = "A AA B BB Aa Bb AAA';
const regExp = /A{1,2}/g;
target.match(regExp); // ["A","AA","A","AA","A"]

const regExp2 = /A{2}/g;
target.match(regExp2); //["AA","AA"]
->'A'가 2번 반복되는 문자열

const regExp3 = /A{2,}/g;
target.match(regExp2); //["AA","AAA"]
-> 'A'가 최소 2번 이상 반복되는 문자열

const regExp4 = /A+/g;
target.match(regExp); //["A","AA","A","AAA"]
-> 'A'가 최소 한번 이상 반복되는 문자열

const target2 = "color colour";
const regExp5 = /colou?r/g;
target2.match(regExp5) // ["color","colour"]
-> 'colo' 다음에 'u'가 최대 한번(0번 포함) 이상 반복되고 'r' 이 이어지는 문자열
```

OR 검색

```jsx
const target = 'A AA B BB Aa Bb';
const regExp = /A|B/g;
target.regExp; //["A","A","A","B","B","B","A","B"]
-> 'A' 또는 'B' 검색

const regExp2 = /A+|B+/g;
target.match(regExp2); // ["A","AA","B","BB","A","B"]
-> 분해되지 않은 단어 레벨로 검색하기 위해서는 +를 사용한다.
이를 간단히 표현하면
const regExp2 = /[AB]+/g; 가 된다. [] 안의 문자는 or 로 동작한다.

ex) 'A'~'Z' 가 한번 이상 반복되는 정규식 => /[A-Z]+/g;
```

TIP

```jsx
정규표현식에서 \d 는 숫자를 의미한다.
\D 는 숫자가 아닌 문자를 의미한다.
\w 는 [A-Za-z0-9_] (알파벳, 숫자, 언더스코어) 를 의미한다.
\W 는 알파벳,숫자,언더스코어 를 제외한 모든 문자를 의미한다

[...] 내의 ^ 는 not 의 의미를 갖는다.
[^0-9] = \D 와 같은 의미이다.

[...] 밖의 ^은 문자열의 시작을 의미한다. [...] 내의 ^와 혼동하면 안되므로 주의해야한다.
const target = "https://google.com";
const regExp = /^https/;
regExp.test(target); // ->true 
-> target이 https 로 시작하는지 검사한다.

http or https로 시작하는지 검사하려면
-> /^https?:\/\//.test(target)
-> /^(http|https):\/\//.test(target) 과도 같은 의미이다.

$는 문자열의 마지막을 의미한다.
const regExp2 = /com%/l
regExp2.test(target); //true
-> target 이 com으로 끝나는지 검사한다.
```

공백

```jsx
\s 는 여러가지 공백문자(스페이스,탭 등)을 의미한다
\s 는 [\t\r\n\v\f] 와 같은 의미이다.
```

아이디로 사용 가능한지 검사

```jsx
const id = 'abc123';
/^[A-Za-z0-9\{4,10}$/.test(id);
->알파벳 대소문자 또는 숫자로 시작하고 끝나며 4~10자리인지 검사한다.
```

메일 주소 형식 검사

```jsx
const email = 'ungmo2.gmail.com';
/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/
.test(email)
```

핸드폰 번호 검사

```jsx
const cellphone = '010-1234-5678';
/^\d{3}-\d{3,4}-\d{4}$/.test(cellphone); //true
```

특수문자 포함 여부

```jsx
const target = 'abc#123';
(/[^A-Za-z0-9]/gi).test(target)
(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\`\"]/gi).test(target)

특수문자 제거할 때는 replace를 사용한다
target.replace(/[^A-Za-z0-9]/gi,'');

공백제거
const str = '     str     ';
str.replace(/\s/g,''); // 'str'
str.replace(/^\s+/g,''); // 'str    '
str.replace(/\s+$/g,''); // '     str'
```