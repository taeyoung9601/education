SELECT
    *
FROM
    dept;


SHOW AUTOCOMMIT;
       -- 오토커밋 설정 보여주기
SET AUTOCOMMIT ON;

BEGIN  -- to begin a transaction

    -- 컬럼선언부를 명시한 INSERT 문
    INSERT INTO dept (deptno, dname, loc)
    VALUES (50, '개발', '서울');


    -- 컬럼선언부를 생략한 INSERT 문
    -- 이때에는 테이블의 스키마에 나온 모든 컬럼에 대해서,
    -- 스키마에 나온 컬럼 순서대로 값을 반드시 넣어주어야 합니다.
    INSERT INTO dept
    VALUES (60, '인사', '경기');


    -- 묵시적으로 널(null) 값 저장
    --  1) 묵시적 방법(Implicitly)
    INSERT INTO dept (deptno, dname)
    VALUES (70, '인사');


    -- 명시적으로 널(null) 값 저장:
    --  2) 명시적 방법(Explicitly)
    INSERT INTO dept (loc, deptno, dname )
    VALUES (NULL, 80, '인사');

    -- ROLLBACK;
    COMMIT;

END;    -- to end a transaction


INSERT INTO dept (deptno,dname,loc)
VALUES (11,'인사'); --ORA-00947: not enough values


-- 2) INTO 절에서 컬럼목록을 생략한 경우 : 테이블 스키마에 나온 컬럼의 순서대로 모든 컬럼에 값을 넣어줘야함.
-- 하지만, 컬럼선언부가 있을땐 값이 누락된 곳은 NULL이 들어감
INSERT INTO dept
VALUES (12,'인사'); --ORA-00947: not enough values


-- 3) 순서에 맞지않을 때
INSERT INTO dept(deptno,dname,loc)
VALUES ('개발', 13, '인사');  --ORA-01722: invalid number






DROP TABLE mydept PURGE;


CREATE TABLE mydept
AS
SELECT * FROM dept
WHERE 1 = 2;        -- 같을 수가 없다 => 기존 테이블의 스키마만 복사(데이터 제외) 

DESC mydept;

SELECT 
    *
FROM
    mydept;


CREATE TABLE mydept2
AS
SELECT 
    * 
FROM
    dept;

SELECT 
    * 
FROM 
    dept;


INSERT INTO mydept
SELECT
    deptno,
    dname,
    loc
FROM
    dept;

SHOW autocommit;
set autocommit off; 

INSERT INTO mydept
VALUES (77,'임시', '안산');

DELETE FROM mydept
WHERE deptno = 77;

COMMIT;




CREATE TABLE myemp_hire AS
SELECT
    empno,
    ename,
    hiredate,
    sal
FROM
    emp
WHERE
    1 =2;

DESC myemp_hire;

-- ------------------------------------------------------

DROP TABLE myemp_mgr;


CREATE TABLE myemp_mgr AS      -- 2nd. Table creation.
SELECT
    empno,
    ename,
    mgr
FROM
    emp
WHERE
    1 = 2;        -- 기존 테이블의 스키마만 복사(데이터 제외)


DESC myemp_mgr;

-- ------------------------------------------------------
SET AUTOCOMMIT ON;
SHOW AUTOCOMMIT;

INSERT ALL
    INTO myemp_hire VALUES (empno, ename, hiredate, sal)
    INTO myemp_mgr  VALUES (empno, ename, mgr)

    SELECT
        empno,
        ename,
        hiredate,
        sal,
        mgr
    FROM
        emp;

-- ------------------------------------------------------
SELECT
    *
FROM
    myemp_hire;


SELECT
    *
FROM
    myemp_mgr;


-- ------------------------------------------------------
DROP TABLE myemp_hire2;


CREATE TABLE myemp_hire2 AS   -- 1st. Table creation.
SELECT
    empno,
    ename,
    hiredate,
    sal
FROM
    emp
WHERE
    1 = 2;        -- 기존 테이블의 스키마만 복사(데이터 제외)


DESC myemp_hire2;

-- ------------------------------------------------------

DROP TABLE myemp_mgr2;


CREATE TABLE myemp_mgr2 AS    -- 2nd. Table creation.
SELECT
    empno,
    ename,
    mgr
FROM
    emp
WHERE
    1 = 2;        -- 기존 테이블의 스키마만 복사(데이터 제외)


DESC myemp_mgr2;


-- ------------------------------------------------------


INSERT ALL
    WHEN sal > 3000 THEN
        INTO myemp_hire2  VALUES (empno, ename, hiredate, sal)

    WHEN mgr = 7698 THEN
        INTO myemp_mgr2   VALUES (empno,ename,mgr)

    SELECT
        empno,
        ename,
        hiredate,
        sal,
        mgr
    FROM
        emp;

-- ------------------------------------------------------

SELECT
    *
FROM
    myemp_hire2;


SELECT
    *
FROM
    myemp_mgr2;

---------------------------------------------------------------

CREATE TABLE myemp_hire3 AS
SELECT
    empno,
    ename,
    hiredate,
    sal
FROM
    emp
WHERE
    1 =2;

DESC myemp_hire3;

------------------------------------

CREATE TABLE myemp_mgr3 AS
SELECT
    empno,
    ename,
    mgr
FROM
    emp
WHERE
    1 = 2;

DESC myemp_mgr3;



---------------------------------------
INSERT FIRST
    WHEN sal = 800 THEN
        INTO myemp_hire3 VALUES (empno, ename, hiredate, sal)

    WHEN sal < 2500 THEN
        INTO myemp_mgr3 VALUES (empno, ename, mgr)

    SELECT
        empno,
        ename,
        hiredate,
        sal,
        mgr
    FROM
        emp;

