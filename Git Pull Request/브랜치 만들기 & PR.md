# 브랜치 만들기 & PR

`**코드 리뷰 및 안전한 커밋을 위해 브랜치 & PR 만드는 법을 공유 합니다**`
브랜치를 만들고 PR을 하는 방식으로 하는 이유로는
1. 다수의 구성원이 베이스 브랜치(Master)를 중심으로 작업을 하는데, 모두가 이 저장소에 코드를 바로 올리게 되면 conflict가 발생할 확률이 큽니다.
이를 사전에 방지하기 위해 각자의 브랜치를 만들고 작업을 합니다.

2. PR을 만들어서 리뷰를 요청하게 되면 팀 구성원이 확인해주는 과정을 거치면서 코드의 품질 향상은 물론 코드를 한번 더 검증하는 과정을 거치게 됩니다.
이를 통해 더 안전하고 유연한 프로젝트 관리를 할 수 있기 때문에 Branch작업과 PR의 코드 리뷰 방법을 공유하고자 문서를 만듭니다.

3. 브랜치 별로 작업을 분리할 수 있습니다.
예를들어 A의 작업을 하다가 A와 연관이 전혀 없는 B의 작업을 할때는 새로운 B브랜치를 만들고 이동하면 이전 브랜치에서 하던 작업과 분리하여 작업을 이어 나갈 수 있는 장점이 있습니다. 또한 급하게 수정할 사항이 생겼을 경우에는 hot fix 방식으로 master브랜치에서 작업하고 바로 반영하는 방법으로 작업을 수행할 수 있습니다. 

# !!!!!브랜치 생성하기!!!!!

총 3가지 방법으로 진행하는 방법을 소개합니다

1. Terminal
2. Git desktop
3. Eclipse

## ※ Terminal에서 진행하는 방법을 소개합니다.

  1. 브랜치 생성하기
~~~
 git checkout -b new브랜치이름
~~~
위의 명령어는 브랜치 생성과 동시에 해당 브랜치로 이동하게 됩니다.

**생성을 안하고 이동만 하고 싶으실 경우
~~~
 git checkout  new브랜치이름
~~~
**생성 되어 있는 브랜치들 확인하려면?
~~~
 git branch
~~~
위의 작업이 끝나셨다면 local 에 브랜치가 만들어집니다.

local 브랜치를 모두에게 공유하고자 하면 원격저장소(remote)에도 브랜치를 생성해주어야 합니다.

local 브랜치를 origin remote 에 push 하여 원격 저장소를 만들어주세요
~~~
 git push origin new브랜치이름
~~~
  
 2. 마스터 브랜치에서 파일들 가져오기 ( 머지 컨플릭트를 방지하기 위해 동기화 시켜줍니다 )
~~~
 git pull origin master 
~~~
위 명령어의 의미는 origin repository master branch에서 변경 사항을 받아서 merge 하는 것입니다.

base를 master인 상태에서 branch를 만드셨다면 처음에는 필요하지 않을수도 있습니다.

하지만 시간이 지나 다른 작업들이 올라오고 본인이 작업하던 파일과 충돌이 생기는 문제가 생길수도 있으니 pull from master를 해주시면서 merge conflict를 해결해주시면 좋겠습니다.

 
  3. 브랜치에서 변경 사항을 커밋합니다

브랜치에서 작업내용의 변경사항이 생겼고 이를 저장하고 remote에까지 저장하고 싶으시다면

커밋과 푸쉬를 해주셔야 합니다.
~~~
 git commit -a -m "modify :  OO 변경"

 git push  / git push —set-upstream origin new브랜치이름
~~~
 4.  Pull Request를 작성합니다. 

## ※ 위의 내용들을 git desktop에서 진행하는 모습을 첨부합니다

<img src="브랜치 만들기 & PR\Untitled.png">

상단의 Current branch를 누르시면 Branch를 이동할수도 있고, 새로 만들수도 있습니다. 이 곳에서 New Branch를 눌러주세요

<img src="브랜치 만들기 & PR\Untitled 1.png">
Branch의 이름을 만들어주시고 Create branch를 누르셔서 생성을 local 브랜치를 생성 완료합니다.

<img src="브랜치 만들기 & PR\Untitled 2.png">

Publish branch를 통하여 remote 브랜치를 생성하여 줍니다.

<img src="브랜치 만들기 & PR\Untitled 3.png">

변경사항을 저장하고 Pull Request를 클릭하셔서 github.com으로 이동합니다

<img src="브랜치 만들기 & PR\Untitled 4.png">

PR의 제목과 변경사항 및 등 참고 내용을 작성하신 후 PR을 작성하시면 됩니다.
Reviewer를 지정하시면 해당 사용자에게 메세지가 전달되어 더 빠른 리뷰를 받아보실 수 있습니다.

---

## ※ 위의 내용들을 Eclipse에서 진행하는 과정을 정리한 블로그 사이트입니다.

[eclipse + git - branch 생성 및 master branch 와 Merge(병합)](https://devhj.tistory.com/10)

# !!!!!Pull request 생성하기!!!!!

1. 브랜치 생성 작업이 잘 진행 되었다면, 이제 [github.com](http://github.com) 으로 이동하여 저장소로 이동합니다.
2. Pull request 탭으로 이동하여 작업을 진행해줍니다.

<img src="브랜치 만들기 & PR\Untitled 5.png">

 3.  Pull request를 생성하여 줍니다

<img src="브랜치 만들기 & PR\Untitled 6.png">

!<img src="브랜치 만들기 & PR\Untitled 7.png">

 4.  PR의 제목과 변경사항 및 등 참고 내용을 작성하신 후 PR을 작성하시면 됩니다.
Reviewer를 지정하시면 해당 사용자에게 메세지가 전달되어 더 빠른 리뷰를 받아보실 수 있습니다.

### 깃헙 사용에 유용한 지식을 알려주는 강의 및 사이트를 공유 합니다.

[지옥에서 온 Git - 수업소개](https://youtu.be/hFJZwOfme6w)

[누구나 쉽게 이해할 수 있는 Git 입문~버전 관리를 완벽하게 이용해보자~ | Backlog](https://backlog.com/git-tutorial/kr/)

틀린점은 알려주세요 수정하겠습니다.
궁금하신점은 ...................................
검색하시면 더 간단하고 세련되게 정리 된 글들을 많이 찾을 수 있으실겁니다 😓