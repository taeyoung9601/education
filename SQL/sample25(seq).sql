CREATE SEQUENCE dept_deptno_seq
    START WITH 10       -- 10부터 번호시작
    INCREMENT BY 10     -- 증가치가 10
    MAXVALUE 100        -- 최대값 100
    MINVALUE 5         -- 최소값 5(CYCLE시, 재시작 번호)
    CYCLE               -- 최대값 도달시, MINVALUE 부터 재시작
    NOCACHE ;           -- 최대 20개의 숫자를 메모리에 미리 생성하지 않음!

DROP SEQUENCE dept_deptno_seq;

-- Error: DESCRIBE SEQUENCE is not available
DESC dept_deptno_seq;   -- 테이블 객체에만 사용가능


SELECT
    dept_deptno_seq.NEXTVAL,
    dept_deptno_seq.CURRVAL
FROM
    dual;
