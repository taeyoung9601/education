-- sample2.sql


-- ------------------------------------------------------
-- 2. PL/SQL Block
-- ------------------------------------------------------
--  가. 프로그램을 논리적으로 분리할 수 있는 기본단위 -> Block
--  나. PL/SQL은 Block을 기본단위로 처리함
--  다. 블록의 기본구조는 아래의 이미지와 같음:
--      PLSQL_Block.JPG
--  라. 선언부(DECLARE Section)
--      a. PL/SQL Block 에서 사용할 모든 변수/상수 선언 부분
--      b. DECLARE 키워드로 시작
--      c. **생략가능** : BEGIN ~ END 만 남음
--  마. 실행부(Executable Section: BEGIN ~ END)
--      a. 절차적 형식으로 SQL 문장을 실행가능 하도록,
--      b. 절차적 언어의 요소인, 제어문/반복문/함수선언 등
--         처리로직을 기술하는 부분
--      c. BEGIN 키워드로 시작해서, END 키워드로 종료
--  바. 예외처리(Exception Section)
--      a. PL/SQL block 실행중 에러 발생가능 -> "예외"라 함
--      b. 예외(Exception) 발생시, 이를 해결하기 위한 문장
--          --> "예외처리"라 함(***)
--      c. **생략가능**
--  사. 구분(2가지):
--      (1) 이름이 없는 블록 (익명블록, Anonymous block):
--          a. 일반적으로, 1회성으로 사용
--      (2) 이름이 있는 서브프로그램 (Sub-program):
--          a. DB에 저장
--          b. 반복적으로 재사용 가능한 블록(PL/SQL block)
--          c. 다시 2가지로 나눌 수 있음:
--            - 함수(Function): 연산수행 및 결과값 반환 시, 사용
--            - 프로시저(Procedure): 일련의 작업처리 시, 사용
-- ------------------------------------------------------


-- ------------------------------------------------------
-- 2-1. PL/SQL Block: 변수 선언
-- ------------------------------------------------------
--  가. 다른 프로그래밍 언어의 변수와 개념이 같다
--  나. PL/SQL block의 선언부(DECLARE section)에서 선언
--  다. PL/SQL block의 실행부(Executable section)에서 사용
--  라. 변수 선언시, 변수명 뒤에 자료형(데이터 타입) 기술
--  마. 변수의 자료형(데이터 타입)은, 테이블의 컬럼 자료형과
--      일치시키는 것이 일반적임(***)
--  바. 선언된 변수를 "스칼라 변수" 라고 부른다
-- ------------------------------------------------------
-- Basic syntax:  PLSQL_Variable_Declaration.JPG
-- ------------------------------------------------------
--  a. 변수 선언과 동시에, 초기값 설정가능 (변수의 *정의*)
--  b. 변수 선언시, 초기값을 지정하지 않으면, 변수의 자료형과
--     상관없이, NULL 값으로 초기화 됨
--  c. [ CONSTANT ] 키워드 지정한 경우:
--     - 변수가 아닌, 상수(Constant)의 선언이 됨
--     - 한번 초기값을 설정하면, 이후 변경되지 않음
--     - 따라서, 상수 선언시, 초기화 해야 됨(**)
--  d. 변수 선언시, 초기값을 지정하기 위해서는, 일반 프로그램 언어
--     에서는 대입연산자(=)를 사용하지만, PL/SQL에서는, 
--     (:=) 연산자(***)를 사용해야 한다.
--  e. 하나의 변수 선언의 끝은, 세미콜론(;)으로 마감처리한다
-- ------------------------------------------------------


-- ------------------------------------------------------
-- 2-2. PL/SQL Block: 변수 선언 예제 (SCOTT 계정으로)
-- ------------------------------------------------------
-- 사원번호가 7369 인, 사원의 이름과 사번 조회하여, 
-- 변수에 저장하고 출력하는 예제
-- ------------------------------------------------------
DESC emp;


-- ------------------------------------------------------
-- SERVEROUTPUT 환경변수 (Oracle SQL*PLUS 에서)
-- ------------------------------------------------------
--  a. PL/SQL block 를 Oracle SQL*PLUS 에서 실행시, 
--     기본적으로, 변수의 값을 출력하지 않음:
--  b. 이유는, 기본적으로 SERVEROUTPUT 이란 환경변수가 OFF 되어 있음
--  c. 이 환경변수의 갑을 ON 시키면, DBMS_OUTPUT.put_line()으로,
--     변수의 값을 출력시킬 수 있음
--  d. 따라서, PL/SQL block 실행 전에, 반드시 이 환경변수를 ON
--     시키고 실행 해야 함
-- ------------------------------------------------------
-- SET SERVEROUTPUT ON


DECLARE     -- 선언부 (스칼라/참조 변수선언 부분)

    -- SELECT 문으로 조회된 사원번호 저장용 변수
    -- emp.empno 컬럼의 자료형과 일치 (**)
    v_empno  NUMBER(4);         -- 스칼라 변수선언(초기값 없음, NULL로 초기화)

    -- SELECT 문으로 조회된 사원명 저장용 변수
    -- emp.ename 컬럼의 자료형과 일치 (**)
    v_ename  VARCHAR2(10);      -- 스칼라 변수선언(초기값 없음, NULL로 초기화)

BEGIN       -- 실행부(Execution section) 시작

    DBMS_OUTPUT.put_line('1. v_empno:' || v_empno);
    DBMS_OUTPUT.put_line('2. v_ename:' || v_ename);


    SELECT
        empno,
        ename
        -- INTO 절에, 선언부의 각 변수에 일대일로, 조회결과 저장
        INTO v_empno, v_ename
    FROM
        emp
    WHERE
        empno = 7369;


    -- DBMS_OUTPUT 패키지의 put_line() 함수(Function):
    --  a. PL/SQL block 에서, 값을 출력하기 위해 제공되는 함수
    --  b. 문자열 리터털 및 변수 출력
    DBMS_OUTPUT.put_line( '3. 7369 사원의 정보: ' || v_empno || ', ' || v_ename );

END;    -- 실행부 끝, PL/SQL Block 의 끝은, 세미콜론(;)으로 마감처리(**주의**)


-- ------------------------------------------------------
-- 2-2. PL/SQL Block: 참조변수의 %TYPE 자료형 (***)
-- ------------------------------------------------------
--  가. 위의 PL/SQL block 에서 볼 수 있듯이, 변수에는 SELECT문의
--      실해결과가 **컬럼 단위로**, 각 변수에 저장됨
--  나. 따라서, 변수의 자료형과 크기를 지정할 때, 일일이, 
--      테이블의 자료형을 확인(DESCRIBE 명령)하고, 
--      정확히 일치시켜야 함(변수의 자료형과 해당 테이블의 컬럼 자료형)
--  다. 이 부담을 줄이는 방법:
--      변수 선언시, 실제 해당 테이블의 해당 컬럼의 자료형을 그대로
--      참조해서 정의할 수 있도록 ** "%TYPE" ** 자료형 제공
-- ------------------------------------------------------
-- Basic syntax: PLSQL_Variable_TYPE_syntax.JPG
-- ------------------------------------------------------
--  a. %TYPE 형은, 테이블에서 단 하나의 컬럼 자료형과 크기를 참조
--  b. %TYPE 형의 장점:
--      (1) 컬럼의 자료형/크기가 변경되더라도, 동적으로 참조
--      (2) 변수의 자료형 수정을 할 필요가 없음
-- ------------------------------------------------------

-- ------------------------------------------------------
-- %TYPE 자료형을 이용한, 2-1 의 PL/SQL block 재선언
-- ------------------------------------------------------
DECLARE     -- 선언부 (스칼라/참조 변수선언 부분)

    v_empno emp.empno%TYPE; -- 참조변수 선언(초기값 없음, NULL로 초기화)
    v_ename emp.ename%TYPE; -- 참조변수 선언(초기값 없음, NULL로 초기화)

BEGIN       -- 실행부(Execution section) 시작

    DBMS_OUTPUT.put_line('1. v_empno:' || v_empno);
    DBMS_OUTPUT.put_line('2. v_ename:' || v_ename);


    SELECT
        empno,
        ename
        -- INTO 절에, 선언부의 각 변수에 일대일로, 조회결과 저장
        INTO v_empno, v_ename
    FROM
        emp
    WHERE
        empno = 7369;


    -- DBMS_OUTPUT 패키지의 put_line() 함수(Function):
    --  a. PL/SQL block 에서, 값을 출력하기 위해 제공되는 함수
    --  b. 문자열 리터털 및 변수 출력
    DBMS_OUTPUT.put_line( '3. 7369 사원의 정보: ' || v_empno || ', ' || v_ename );

END;    -- 실행부 끝, PL/SQL Block 의 끝은, 세미콜론(;)으로 마감처리(**주의**)


-- ------------------------------------------------------
-- 2-3. PL/SQL Block: 참조변수의 %ROWTYPE 자료형 (***)
-- ------------------------------------------------------
--  가. %TYPE 형은, 테이블의 하나의 컬럼을 참조하는 방법
--  나. So, 참조해야할 테이블의 컬럼이 많은 경우에는, 비효율적
--  다. %TYPE 형의 단점을 극복하는 방법으로 제공
--  라. 테이블의 하나의 컬럼을 참조하는 것이 아니라,
--      해당 테이블의 모든 컬럼의 자료형과 크기를 참조 (***)
-- ------------------------------------------------------
-- Basic syntax: PLSQL_Variable_ROWTYPE_syntax.JPG
-- ------------------------------------------------------
--  a. %ROWTYPE 앞에, 테이블명 지정
--  b. 실제 테이블의 컬럼을 참조할 때: 
--      "변수명.컬렴명" 형식 사용하여, 개별적으로 컬럼 참조
--  c. 해당 테이블의 컬럼의 개수나 자료형을 몰라도, 사용가능
--  d. PL/SQL block 실행 중에, 기본 테이블의 컬럼 개수나
--     자료형을 동적으로 변경가능(***)
-- ------------------------------------------------------

-- ------------------------------------------------------
-- %ROWTYPE 자료형을 이용한, 2-2 의 PL/SQL block 재선언
-- ------------------------------------------------------
DESC emp;


DECLARE     -- 선언부 (스칼라/참조 변수선언 부분)

    v_emp emp%ROWTYPE; -- 참조변수 선언(초기값 없음, NULL로 초기화)

BEGIN       -- 실행부(Execution section) 시작

    -- Error: ORA-06550: line 6, column 26:
    -- PLS-00306: wrong number or types of arguments in call to '||'
    -- ORA-06550: line 6, column 5:
    -- PL/SQL: Statement ignored

    -- DBMS_OUTPUT.put_line('1. v_emp:' || v_emp);      -- XX
    -- DBMS_OUTPUT.put_line(v_emp);                     -- XX


    -- SELECT 절에서는, 
    --  a. emp 테이블의 모든 컬럼을 순서대로 지정하던가,
    --  b. * (wildcard) 사용해야 됨

    -- SELECT *
    --     -- INTO 절에, 선언부의 각 변수에 일대일로, 조회결과 저장
    --     INTO v_emp
    SELECT
        empno,
        ename,
        sal -- PL/SQL: ORA-00913: too many values
        -- job  -- PL/SQL: ORA-00947: not enough values

        -- INTO 절에, 선언부의 각 변수에 일대일로, 조회결과 저장
        INTO v_emp.empno, v_emp.ename, v_emp.sal
    FROM
        emp
    WHERE
        empno = 7369;


    -- 실제 테이블 컬럼의 참조는, "변수명.컬렴명" 형식 사용, 개별 컬럼 참조
    -- chr(13): ANSI 13 code, char(10): ANSI 10 code
    DBMS_OUTPUT.put_line(
        '1. v_emp.empno: '  || v_emp.empno || ', ' || chr(13) || chr(10) ||
        '2. v_emp.ename: '  || v_emp.ename || ', ' || chr(13) || chr(10) ||
        '3. v_emp.sal: '    || v_emp.sal
    );  -- put_line

END;    -- 실행부 끝, PL/SQL Block 의 끝은, 세미콜론(;)으로 마감처리(**주의**)


-- ------------------------------------------------------
-- 2-4. PL/SQL Block: 참조변수의 사용
-- ------------------------------------------------------
--  가. 데이터베이스는 항상 유동적임을 명심할 것
--  나. So, 테이블 컬럼과 연관된 PL/SQL block 변수 선언시,
--      스칼라 변수를 사용하는 것보다,
--      참조변수(%TYPE 형, %ROWTYPE 형) 사용을 권장(**)
-- ------------------------------------------------------

