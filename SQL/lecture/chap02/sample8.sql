-- sample8.sql


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
-- WHERE <predicates>
-- ------------------------------------------------------

-- ------------------------------------------------------
-- 1. Logical Operators (논리연산자)
-- ------------------------------------------------------
--  (1) AND (그리고) : 두 조건을 모두 만족하는 경우 TRUE!
--  (2) OR  (또는)  : 두 조건중, 한가지만 만족해도 TRUE!
--  (3) NOT (부정)  : 지정된 조건이 아닌 데이터를 검색
-- ------------------------------------------------------
SELECT
    last_name,
    job_id,
    salary
FROM
    employees
WHERE
    job_id = 'IT_PROG'
    AND salary >= 5000;

-- ------------------------------------------------------

SELECT
    last_name,
    job_id,
    salary
FROM
    employees
WHERE
    job_id = 'IT_PROG'
    OR salary >= 5000;


SELECT
    to_char(sysdate, 'yyyy/MM/dd hh24:mi:ss') AS now_sys,
    to_char(current_date, 'yyyy/MM/dd hh24:mi:ss') AS now_curr
FROM
    dual;


SELECT
    commission_pct,
    nvl2(commission_pct, 100, 200) AS "Commision ?"
FROM
    employees;

SELECT
    DISTINCT department_id, job_id
FROM
    employees
ORDER BY
    department_id ASC;    