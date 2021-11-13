```jsx
<script>
    const get = url => {
      const xhr = new XMLHttpRequest();
      xhr.open('GET', url);
      xhr.send();
      xhr.onload = () => {
        if (xhr.status === 200) {
          console.log(JSON.parse(xhr.response));
        } else {
          console.error("Error", xhr.status, xhr.statusText);
        }
      }
    }

    const response = get('https://jsonplaceholder.typicode.com/posts/1');
    console.log(response)//undefined
  </script>
```
위의 예제에서 response의 값은 undefined이다.

이유는 get함수가 호출되면 XMLHttpRequest 객체를 생성하고, HTTP요청을 초기환 후 HTTP요청을 전송한다.

그리고 xhr.onload 이벤트 핸들러 프로퍼티에 이벤트 핸들러를 바인딩하고 종료한다. 이때 get 함수에 명시적인 반환문이 없으므로 get 함수는 undefined를 반환한다

xhr.onload 이벤트 핸들러 프로퍼티에 바인딩한 이벤트 핸들러의 반환문은 get함수의 반환문이 아니다.

get함수는 반환문이 생략되었으므로 암묵적으로 undefined를 반환한다.

함수의 반환값은 명시적으로 호출한 다음에 캐치할 수 있으므로 onload 이벤트 핸들러를 get 함수가 호출할 수 있다면 이벤트 핸들러의 반환 값을 get 함수가 캐치하여 다시 반환할수도 있겠지만 onload 이벤트 핸들러는 get 함수가 호출하지 않기 때문에 그럴 수도 없다.
따라서 이벤트 핸들러의 반환값은 캐치할 수 없다.

```jsx
<script>
    let todos ;

    const get = url => {
      const xhr = new XMLHttpRequest();
      xhr.open('GET', url);
      xhr.send();
      xhr.onload = () => {
        if (xhr.status === 200) {
          todos = JSON.parse(xhr.response);
        } else {
          console.error("Error", xhr.status, xhr.statusText);
        }
      }
    }

    get('https://jsonplaceholder.typicode.com/posts/1');
    console.log(todos)//undefined
  </script>
```
상위에 todos라는 변수를 할당하고 onload 내부에서 값을 넣어주어도 결과는 undefined이다.

xhr.onload 이벤트 핸들러 프로퍼티에 바인딩한 이벤트 핸들러는 언제나 console.log가 종료한 이후에 호추된다. 따라서 console.log 시점에서는 아직 전역 변수 todos에 서버의 응답 결과가 할당되지 않았기에 undefined가 나타난다.

---
xhr.onload 이벤트 핸들러가 실행되려면 콜스택의 태스크들이 다 실행되고나서 빈 상태여야지만 태스크 큐에 저장되어 있떤 onload 이벤트가 그제서야 콜스택으로 푸시되고 실행된다.

즉, xhr.onload 이벤트 핸들러에서 상위 스코프의 변수에 서버의 응답 결과를 할당하기 이전에 onsole.log가 먼저 호출되어 undefined가 출력된다.

---
이처럼 비동기 함수는 비동기 처리 결과를 외부에 반환할 수 없고, 상위 스코프의 변수에 할당할 수도 없다.

따라서 비동기 함수의 처리 결과(서버의 응답 등)에 대한 후속 처리는 비동기 함수 내부에서 수행해야 한다.

이때 비동기 함수를 범용적으로 사용하기 위해 비동기 함수에 비동기 처리 결과에 대한 후속 처리를 수행하는 콜백 함수를 전달하는 것이 일반적이다. 필요에 따라 비동기 처리가 성공하면 호출될 콜백 함수와 비동기 처리가 실패하면 호출될 콜백 함수를 전달할 수 있다.

```jsx
<script>
    let todos ;

    const get = (url, successCallback, failureCallback ) => {
      const xhr = new XMLHttpRequest();
      xhr.open('GET', url);
      xhr.send();
      xhr.onload = () => {
        if (xhr.status === 200) {
          successCallback(xhr.response);
        } else {
          failureCallback(xhr.status);
        }
      }
    }

    get('https://jsonplaceholder.typicode.com/posts/1',console.log,console.error);
  </script>
  ```

  이처럼 콜백 함수에 인수로 전달하면서 호출하여 응답에 대한 후속처리를 할 수 있다.

  하지만 비동기 함수가 비동기 처리를 가지고 또 다시 비동기 함수를 호출해야한다면 콜백 함수 호출이 중첩되어 복잡도가 높아지는 현상이 발생하는데  이를 **콜백 헬** 이라고 한다.
  ```jsx
  <script>
    let todos;

    const get = (url, callback) => {
      const xhr = new XMLHttpRequest();
      xhr.open('GET', url);
      xhr.send();
      xhr.onload = () => {
        if (xhr.status === 200) {
          callback(JSON.parse(xhr.response));
        } else {
          console.error(xhr.status);
        }
      }
    }
    const url = 'https://jsonplaceholder.typicode.com';
    get(`${url}/posts/1`, ({ userId }) => {
      console.log(userId)
      get(`${url}/users/${userId}`, userInfo => {
        console.log(userInfo);
      })
    });
  </script>
  ```
이처럼 콜백함수를 계속해서 호출해내면 가독성이 안좋아지고 실수를 유발하는 원인이 된다.

또한 예외처리가 곤란하기에 이를 극복하기 위해 ES6에서는 Promise가 도입되었다.

# PROMISE
```jsx
<script>
    const promiseGet = url => {
      return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open('GET',url);
        xhr.send();

        xhr.onload = () =>{
          if(xhr.status === 200){
            resolve(JSON.parse(xhr.response));
          }else{
            reject(new Error(xhr.status));
          }
        }
      })
    }
    promiseGet('https://jsonplaceholder.typicode.com/posts/1');
  </script>
```
프로미스는 비동기 처리가 수행되어 성공할 경우 resolve 함수를 호출하고 프로미스를 fulfilled 상태로 변경한다.

처리가 실패할 경우 rejet 함수를 호출하고 프로미스를 rejected 상태로 변경한다

```jsx
promiseGet('...')
    .then()
    .then()
    .catch()
```
위와 같이 프로미스 체이닝을 통해 연속적으로 호출이 가능하다.

프로미스를 사용하면 프로미스 체이닝을 통해 비동기 처리 결과를 전달받아 후속 처리를 하므로 비동기 처리를 콜백 패턴에서 발생하던 콜백 헬이 발생하지는 않지만 콜백 패턴을 사용하므로 가독성이 좋지 않을 수 있다.

이럴 때 사용하는 것이 ES8에서 도입된 async/await이다.
이를 사용하면 프로미스의 후속 처리 메서드 없이 마치 동기 처리처럼 프로미스가 처리 결과를 반환하도록 구현할 수 있다.

```jsx
<script>
    const url = 'https://jsonplaceholder.typicode.com';

    (async() =>{
      const { userId } = await promiseGet(`${url}/posts/1`);
      const userInfo = await promiseGet(`${url}/users/${userId}`);
      console.log(userInfo)
    })();
  </script>
  ```
  async / await 도 프로미스를 기반으로 동작한다.

  # fetch
  ```jsx
 <script>
    const request = {
      get(url){
        return fetch(url);
      },
      post(url,payload){
        return fetch(url,{
          method:'POST',
          headers:{'content-Type':'application/json'},
          body:JSON.stringify(payload)
        });
      },
      patch(url,payload){
        return fetch(url,{
          method:'PATCH',
          headers:{'content-Type':'application/json'},
          body:JSON.stringify(payload)
        });
      },
      delete(url){
        return fetch(url,{method:'DELETE'});
      }
    }

    //get 요청
    request.get('https://jsonplaceholder.typicode.com/todos/1')
      .then(response => response.json())
      .then(todos=>console.log(todos))
      .catch(err=>console.log(err))

    //post 요청
    request.post('https://jsonplaceholder.typicode.com/todos',{
      userId:1,
      title:'JavaScript',
      completed:false
    }).then(response => response.json())
      .then(todos=>console.log(todos))
      .catch(err => console.error(err));

    //patch 요청
    request.patch('https://jsonplaceholder.typicode.com/todos/1',{
      completed:true
    }).then(response => response.json())
      .then(todos=>console.log(todos))
      .catch(err => console.error(err));

  </script>
  ```
위와 같이 코딩했을 때 코드가 간결하고 알아보기 쉬운 것 같다! 
나도 프로젝트에서 적용해봐야겠다.