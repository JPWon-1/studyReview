![메서드의 특징](2021-07-18-15-14-34.png)


# get
- 요청 : GET /search?q=hello&hl=ko HTTP/1.1 Host: www.google.com
- 응답 : HTTP/1.1 200 OK
- Content-Type : application/json
- Content-Length:34
- {"username":"young","age":20}

- 리소스 조회
- body를 가지고 있음(그러나 잘 안씀. 필요하면 queryString으로 넘김)
- caching이 가능 함.
- 정말 필요한 경우 post로 메서드 호출 하기도 함.(post는 만능)

# post
- 요청 : POST /members HTTP/1.1
- Content-Type: application/json
- {"username":"hello","age":20}
- 응답 : HTTP/1.1 201 Created Content-Type: application/json Content-Length: 34 Location: /members/100 {"age":.....}
- 메시지 바디를 통해 서버로 요청 데이터 전달.
- 전달된 데이터로 신규 리소스를 등록하거나 프로세스 처리하는데 사용 함
- `POST 메서드는 대상 리소스가 리소스의 고유 한 의미 치계에 따라 요청에 포함 된 표현을 처리하도록 요청`.
    - ex ) HTML 폼
    - 게시판, 뉴스, 메일링 리스트, 블로그 등 메시지 게시
    - 서버가 아직 식별하지 않은 새로운 리소스 생성
    - 기존 자원에 데이터 추가
- 정리 : 이 리소스 URI에 POST요청이 오면 요청 데이터를 어떻게 처리할지 리소스마다 따로 정해야 함 <- 정해진 것이 없다!


# put
- 리소스를 대체한다 ( 있으면 수정, 없으면 새로 만듬)
    - 파일을 엎어버린다고 생각하면 됨
- post와 다른점은 클라이언트가 리소스를 식별한다는 점.
    - ex) PUT/members/`100` HTTP/1.1   {"username":"jp","age":20}
        - 100 이라는 리소스를 알고 URI지정함
    - post -> POST /members HTTP/1.1 {"username":"jp","age":20}
        - 100번인지 200번인지 어디에 만들어질지 모름
        - 메시지 바디를 통해 서버러 요청 데이터 전달
        - 서버는 요청 데이터를 처리
        - 메시지 바디를 통해 들어온 데이터를 처리하는 모든 기능을 수행한다
        - 주로 전달된 데이터로 신규 리소스 등록, 프로세스 처리에 사용 
- 주의! 리소스를 완전히 대체한다
- PUT/members/100 {"age":50} 을 보내면
- members/100 = {"username":"young","age":20}
- members/100 은 {"age":50} 이 되버린다.
- put은 기존 리소스를 아예 갈아치운다고 생각하면 됨
- 명사로 URI를 설계하는 것이 원칙이지만 실무에서는 그럴 수 없는 부분이 생긴다
    - ex) POST /orders/{orderId}/start-delivery  이러한 부분을 컨트롤 URI라고 한다.
# patch
- put이 모든 리소스를 갈아 치운다고 생각하면 되지만
- patch는 부분적으로 변경함.
- PUT/members/100 {"age":50} 을 보내면
- members/100 = {"username":"young","age":20}
- members/100 은 {"username":"young","age":50} 이 되버린다.

# delete
- DELETE /members/100 HTTP/1.1 Host:localhost:8080
- 리소스를 지우는데 사용 함.
## 멱등(idempotent)?
- 한 번 호출하든 두번 호출하든 100번 호출하든 결과가 똑같다.
- GET : 한번 조회하든, 두 번 조회하든 같은 결과가 조회 됨
- PUT : 결과를 대체함. 따라서 같은 요청을 여러밴 해도 최종 결과는 같다.
- DELETE : 결과를 삭제함. 따라서 같은 요청을 여러번 해도 삭제된 결과는 똑같다.
- `POST` : 멱등하지 않다! ex) 결제 -> 2번 호출하면 2번 결제 됨

## 캐시가능(Cacheable)
- 응답 결과 리소스를 캐시해서 사용해도 되는가?
- GET,HEAD,POST,PATCH => 캐시가 가능하다
- 실제로는 GET,HEAD 정도만 사용!
    - 이유: key가 같아야하는데 POST,PATCH 는 key가 같기 쉽지 않음.

