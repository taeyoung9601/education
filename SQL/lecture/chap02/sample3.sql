-- sample3.sql


-- ******************************************************
-- SELECT 문의 기본구조와 각 절의 실행순서
-- ******************************************************
--  - Clauses -                 - 실행순서 -
--
-- SELECT clause                    (5)
-- FROM clause                      (1)
-- WHERE clause                     (2)
-- GROUP BY clause                  (3)
-- HAVING clause                    (4)
-- ORDER BY clause                  (6)
-- ******************************************************


-- ------------------------------------------------------
--        *** SELECT 문의 기본문법 구조 ***
-- ------------------------------------------------------
-- SELECT [DISTINCT] { *, column [AS] [alias], ... }
-- FROM <테이블명>
-- ------------------------------------------------------

-- ------------------------------------------------------
-- 1. Concatenation Operator: ||
-- ------------------------------------------------------
-- SELECT column1 || column2
-- FROM table;
-- ------------------------------------------------------
-- (1) 문자열 + 숫자
SELECT 
    last_name || salary AS "이름 월급"
FROM
    employees;


-- (2) 문자열 + 날짜
SELECT
    -- 별칭에 구둣점 문자가 포함되어 있거나
    -- 고의적으로 대/소문자를 유지하고 싶을때에는
    -- 이중인용부호로 감싸서 별칭을 만드는데,
    -- 별칭뿐만아니라, 테이블명/컬럼명 모두 같습니다.
    -- 참고로 DB는 모든 식별자(개발자가 지은 이름)를
    -- 대문자로 유지합니다.
    last_name || hire_date AS "별칭"
FROM
    employees;


-- ------------------------------------------------------
-- 2. Concatenation Operator: ||
-- ------------------------------------------------------
-- SELECT column || literal
-- FROM table;
-- ------------------------------------------------------
-- (3) 문자열 + 리터럴(문자열)
DESCRIBE employees;
DESC employees;

SELECT
    last_name || '사원'
FROM
    employees;


-- ------------------------------------------------------
-- 3. Concatenation Operator: ||
-- ------------------------------------------------------
-- SELECT column1 || literal || column2 || literal || column3
-- FROM table;
-- ------------------------------------------------------
SELECT
    last_name || '의 직업은 ' || job_id || ' 입니다.' AS "사원별 직급"
FROM 
    employees;


SELECT
    last_name || ' 의 직업은 ' || job_id
FROM
    employees
WHERE
    last_name = 'Smith';    -- = : 동등비교연산자




