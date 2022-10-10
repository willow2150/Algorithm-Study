# Algorithm-Study

 > 알고리즘 스터디를 위한 저장소입니다.

 - 기간: 2022.08.01 ~
 - 사용 언어: Java
 - 문제 출처: [Baekjoon Online Judge](https://www.acmicpc.net/)



## Study Rule

 - Review는 주 1회 이상 실시하기
 - 일정의 조정이 필요할 경우, 사전에 팀원의 양해를 얻고 해당 주차 내 다른 날로 변경 가능
 - 타인의 코드와 나의 코드를 비교하고, 개선점을 서로 논의해보기
 - 더 나은 풀이 방법, 나만의 노하우가 있다면 서로 알려주기
 - 다른 사람도 쉽게 이해할 수 있는 코드 작성하기
 - 해설을 요청받으면 최대한 자세하게 알려주기
 - 뒤처지더라도 미안해하지 말기. 대신 더 열심히 하기
 - GitHub를 통해 코드를 공유하기. Review 전에 숙지하기



## Pull & Commit Rule

 **Pull Request**

 - OnlineJudge_week member

   > Ex) Baekjoon_week9 willow2150


 **Commit Message**

 - OnlineJudge_problem member - Add 1st Solution

   > Ex) Baekjoon_1182 willow2150 - Add 2nd Solution

   - 문제의 n번째 솔루션 코드를 추가 했을 때의 커밋 메시지입니다.


 - OnlineJudge_problem member - Refactor 2nd Solution
   
   > Ex) Baekjoon_1182 willow2150 - Refactor 3rd Solution

   - 문제의 n번째 솔루션 코드를 리팩토링 했을 때의 커밋 메시지입니다.


 - OnlineJudge_problem member - Fix 3rd Solution
   
   > Ex) Baekjoon_1182 willow2150 - Fix 4th Solution

   - 문제의 n번째 솔루션 코드의 오류를 수정 했을 때의 커밋 메시지입니다.



## Repository 구조

```bash

Repository
 ├─ README.md
 │
 ├─ Online Judge1
 │        │
 │        ├─ member1
 │        │     │
 │        │     ├── week1
 │        │     │     ├──problemNumber.java
 │        │     │     ├── ...
 │        │     │
 │        │     ├── week2
 │        │     │     ├──problemNumber.java
 │        │     │     ├── ...
 │        │     │
 │        │     ├── ...
 │        │    
 │        ├─ member2
 │        │     │
 │        │     ├── week1
 │        │     │     ├──problemNumber.java
 │        │     │     ├── ...
 │        │     │
 │        │     ├── week2
 │        │     │     ├──problemNumber.java
 │        │     │     ├── ...
 │        │     │
 │        │     ├── ...
 │        │    
 │        ├─ ...
 │            
 ├─ Online Judge2
 │        │
 │        ├─ member1
 │        │     │
 
``` 



## 주차 별 진행표

|주차|기간|문제 1|문제 2|문제 3|문제 4|문제 5|문제 6|문제 7|번외 1|번외 2|
|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|
|1|22.08.01~22.08.07|[부분수열의 합](https://www.acmicpc.net/problem/1182)|[이차원 배열과 연산](https://www.acmicpc.net/problem/17140)|[화학식량](https://www.acmicpc.net/problem/2257)|[암호 만들기](https://www.acmicpc.net/problem/1759)|[유레카 이론](https://www.acmicpc.net/problem/10448)|[십자뒤집기](https://www.acmicpc.net/problem/10472)|[클레어와 팰린드롬](https://www.acmicpc.net/problem/17502)|||
|2|22.08.08~22.08.14|[요세푸스 문제](https://www.acmicpc.net/problem/1158)|[트리 순회](https://www.acmicpc.net/problem/1991)|[영역 구하기](https://www.acmicpc.net/problem/2583)|[나이트의 이동](https://www.acmicpc.net/problem/7562)|[절댓값 힙](https://www.acmicpc.net/problem/11286)|[어두운 굴다리](https://www.acmicpc.net/problem/17266)|[최소 회의실 개수](https://www.acmicpc.net/problem/19598)|[뱀](https://www.acmicpc.net/problem/3190)|[연료 채우기](https://www.acmicpc.net/problem/1826)|
|3|22.08.15~22.08.21|[다음수 구하기](https://www.acmicpc.net/problem/2697)|[신입 사원](https://www.acmicpc.net/problem/1946)|[과자 나눠주기](https://www.acmicpc.net/problem/16401)|[합이 0](https://www.acmicpc.net/problem/3151)|[트리 복구](https://www.acmicpc.net/problem/6597)|[스도쿠](https://www.acmicpc.net/problem/2239)|[맥주 마시면서 걸어가기](https://www.acmicpc.net/problem/9205)|
|4|22.08.22~22.08.28|[전력난](https://www.acmicpc.net/problem/6497)|[해킹](https://www.acmicpc.net/problem/10282)|[파티](https://www.acmicpc.net/problem/1238)|[운동](https://www.acmicpc.net/problem/1956)|[톱니바퀴](https://www.acmicpc.net/problem/14891)|[상어 초등학교](https://www.acmicpc.net/problem/21608)|[마법사 상어와 비바라기](https://www.acmicpc.net/problem/21610)|
|5|22.09.01~22.09.04|[오델로](https://www.acmicpc.net/problem/15671)|[컨베이어 벨트 위의 로봇](https://www.acmicpc.net/problem/20055)|
|6|22.09.05~22.09.11|[봄버맨](https://www.acmicpc.net/problem/16918)|[단어 만들기](https://www.acmicpc.net/problem/1148)|[빗물](https://www.acmicpc.net/problem/14719)|[0 만들기](https://www.acmicpc.net/problem/7490)|
|7|22.09.12~22.09.18|[추월](https://www.acmicpc.net/problem/2002)|[음악프로그램](https://www.acmicpc.net/problem/2623)|[순열의 순서](https://www.acmicpc.net/problem/1722)|[스타트 택시](https://www.acmicpc.net/problem/19238)|
|8|22.09.19~22.09.25|[계란으로 계란치기](https://www.acmicpc.net/problem/16987)|[나무 위의 빗물](https://www.acmicpc.net/problem/17073)|[미로 탈출](https://www.acmicpc.net/problem/14923)|[움직이는 미로 탈출](https://www.acmicpc.net/problem/16954)|
|9|22.09.26~22.09.30|[사다리 타기](https://www.acmicpc.net/problem/2469)|[🎵니가 싫어 싫어 너무 싫어 싫어 오지 마 내게 찝쩍대지마🎵 - 1](https://www.acmicpc.net/problem/20440)|
|10|22.10.01~22.10.09|[점프](https://www.acmicpc.net/problem/1890)|[작업](https://www.acmicpc.net/problem/2056)|[사다리 조작](https://www.acmicpc.net/problem/15684)|[치즈](https://www.acmicpc.net/problem/2636)|
|11|22.10.10~22.10.16|[가스관](https://www.acmicpc.net/problem/2931)|[소문난 칠공주](https://www.acmicpc.net/problem/1941)|[회사 문화 1](https://www.acmicpc.net/problem/14267)|[회전 초밥](https://www.acmicpc.net/problem/2531)|
