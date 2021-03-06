# 1. 소켓 프로그래밍

소켓 프로그래밍은 소켓을 이용한 통신 프로그래밍을 뜻한다.

소켓이란? 
프로세스간의 통신에 사용되는 양쪽 끝단(endpoint)을 의미한다. 서로 멀리 떨어진 두 사람이 통신하기 위해서 전화기가 필요한 것처럼, 프로세스간 통신을 위해서는 그 소켓이 필요하다.

## 자바에서의 소켓
자바에서는 java.net패키지를 통해 소켓 프로그래밍을 지원하는데, 소켓통신에 사용되는 프로토콜에 따라 다른 종류의 소켓을 구현하여 제공한다.

# TCP와 UDP

TCP/IP 프로토콜은 이기종 시스템간의 통신을 위한 표준 프로토콜로 프로토콜의 집합이다.

TCP와 UDP 모두 TCP/IP 프로토콜에 포함되어 있으며, OSI 7계층의 전송계층에 해당하는 프로토콜이다.

![TCP/UDP 비교](img/TCPvsUDP.png)

# TCP소켓 프로그래밍

앞서 살펴본 것과 같이 TCP소켓 프로그래밍은 클라이언트와 서버간의 일대일 통신이다.

먼저 서버 프로그램이 실행되어 클라이언트 프로그램의 연결요청을 기다리고 있어야 한다.

서버 프로그램과 클라이언트 프로그램간의 통신과정을 단계별로 보면 다음과 같다.

```
1. 서버 프로그램에서는 서버 소켓을 사용해서 서버 컴퓨터의 특정 포트에서 클라이언트의 연결요청을 처리할 준비를 한다.
2. 클라이언트 프로그램은 접속할 서버의 IP주소와 포트 정보를 가지고 소켓을 생성해서 서버에 연결을 요청한다
3. 서버소켓은 클라이언트의 연결요청을 받으면 서버에 새로운 소켓을 생성해서 클라이언트의 소켓과 연결되도록 한다.
4. 이제 클라이언트의 소켓과 새로 생성된 소켓은 서버소켓과 관계없이 일대일 통신을 한다.
```