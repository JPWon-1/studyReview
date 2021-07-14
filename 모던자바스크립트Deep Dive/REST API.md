# REST API

## REST API의 구성
REST API는 자원(resource), 행위(verb), 표현(representations)의 3가지 요소로 구성된다. REST는 자체 표현 구조로 구성되어 REST API만으로도 HTTP 요청의 내용을 이해할 수 있다.

|구성 요소|내용|표현방법|
|---------|----|--------|
|자원resource|자원|URI(엔드포인트)|
|행위verb|자원에 대한 행위|HTTP 요청 메서드|
|표현representations|자원에 대한 행위의 구체적 내용|페이로드|

## 설계 원칙
1) URI는 리소르를 표현하는데 집중.
2) 행위에 대한 정의는 HTTP 요청 메서드를 통해서.

**#bad**

GET /getTodos/1

GET /todos/show/1

**#good**

GET /todos/1

리소스에 대한 행위는 HTTP 요청 메서드(get,post,put,patch,delete)를 통해 표현하며 URI에 표현하지 않는다.


