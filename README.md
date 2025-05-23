# 우아한테크코스 출석

아래 요구 사항을 충족하는 출석 시스템을 개발힌다.

# 구현할 기능 목록

## 출석 시스템

- [x] 출석 확인은 크루가 캠퍼스에 들어온 후 시스템에 출석 데이터가 저장된 시간을 기준으로 한다.
- [x] 시간은 24시간 형식만 사용한다.
    - [x] 예를 들어, "22:30"은 오후 10시 30분을 의미한다.
- [x] 교육 시간과 출석 시간을 비교하여 출석, 지각, 결석을 기록한다.
    - [x] 월요일은 13:00~18:00, 화요일~금요일은 10:00~18:00이다.
    - [x] 해당 요일의 시작 시각으로부터 5분 초과는 지각으로 간주한다.
    - [x] 해당 요일의 시작 시각으로부터 30분 초과는 결석으로 간주한다.
    - [ ] 등교하지 않아 출석 기록이 없는 날은 결석으로 간주한다.
- [x] 결석 횟수에 따라 크루원의 현재 상태를 결정한다.
    - [x] 지각 3회는 결석 1회로 간주한다.
    - [x] 경고 대상자: 결석 2회 이상
    - [x] 면담 대상자: 결석 3회 이상
    - [x] 제적 대상자: 결석 5회 초과
- [x] 캠퍼스 운영 시간이 아닌 경우 예외를 발생시킨다.
  - [x] 캠퍼스 운영 시간은 매일 08:00~23:00이다.
- [x] 주말 및 공휴일에 출석하는 경우 예외를 발생시킨다.
- [ ] 프로그램은 사용자가 종료할 때까지 종료되지 않으며, 해당 기능을 수행한 후 초기 화면으로 돌아간다.

### 출석 확인

- [x] 닉네임과 등교 시간을 입력하면 출석할 수 있다.
- [x] 출석 후 출석 기록을 확인할 수 있다.
- [x] 이미 출석한 경우, 다시 출석할 수 없으며 수정 기능을 이용하도록 안내한다.

### 출석 수정

- [x] 출석 확인을 수정할 수 있다.
    - [x] 닉네임, 수정하려는 날짜, 등교 시간을 입력하여 기록을 수정할 수 있다.
- [x] 수정 후에는 변경 전과 변경 후의 출석 기록을 확인할 수 있다.

### 크루별 출석 기록 확인

- [x] 닉네임을 입력하면 전날까지의 크루 출석 기록을 확인할 수 있다.

### 제적 위험자 확인

- [ ] 전날까지의 크루 출석 기록을 바탕으로 제적 위험자를 파악한다.
- [ ] 제적 위험자는 제적 대상자, 면담 대상자, 경고 대상자순으로 출력한다.
    - [ ] 대상 항목별 정렬 순서는 지각을 결석으로 간주하여 내림차순한다.
    - [ ] 출석 상태가 같으면 닉네임으로 오름차순 정렬한다.

## 입출력

### 입력

- [x] 프로그램을 시작하면 csv파일을 통해 구현에 필요한 정보를 조회한다.
    - [x] 닉네임과 출석 일시를 파싱하여 저장한다.
    - [x] 파일의 내용은 수정할 수 없다.

### 출력

- [x] 날짜와 입력 가능한 기능 목록을 출력한다.
- [x] 출석 기록을 출력한다.
- [x] 수정 기능을 이용하도록 안내하는 문구를 출력한다.
- [x] 수정 기록을 출력한다.
- [x] 크루별 출석 기록을 출력한다.
    - [x] 출석 기록을 출력한다.
    - [x] 출석, 지각, 결석 횟수를 출력한다.
    - [x] 경고, 면담 대상자인지를 출력한다.

### 예외 처리

- [x] 사용자가 잘못된 값을 입력할 경우 "[ERROR]"로 시작하는 메시지와 함께 애플리케이션은 종료되어야 한다.
    - [x] 잘못된 값을 입력받은 경우 IllegalArgumentException을 발생시킨다.
