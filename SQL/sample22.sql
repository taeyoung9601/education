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

