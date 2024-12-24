SELECT
    employee_id,
    last_name,
    job_id,
    salary      --default : ASC (오름차순 정렬)
FROM
    employees
    ORDER BY
    salary ;

SELECT
    employee_id,
    last_name,
    job_id,
    salary +200 AS "월급"     --default : ASC (오름차순 정렬)
FROM
    employees
ORDER BY
    월급 DESC ;



SELECT
    employee_id,
    last_name,
    job_id,
    salary +200 AS "월급"     --default : DESC (내림차순 정렬)
FROM
    employees
ORDER BY
    월급 * -1 DESC ;


-- oracle에선 인덱스 번호가 1 부터 시작
SELECT
    employee_id,
    last_name,
    job_id,
    salary    --default : DESC (내림차순 정렬)
FROM
    employees
ORDER BY
    4 DESC ;


SELECT
    employee_id,
    last_name,
    job_id,
    salary    --default : DESC (내림차순 정렬)
FROM
    employees
ORDER BY
    last_name ASC ;


SELECT
    employee_id,
    last_name AS 이름,
    job_id,
    salary    
FROM
    employees
ORDER BY
    이름 ASC ;


--날짜 데이터 정렬

SELECT
    employee_id,
    last_name,
    salary,
    hire_date AS 입사일    
FROM
    employees
ORDER BY
    -- 입사일 ASC ;
    4 ASC;

ALTER SESSION SET NLS_DATE_FORMAT = 'yyyy/MM/dd hh24:mi:ss';
-- SELECT SYSDATE FROM DUAL; // 확인용도

SELECT
    employee_id,
    last_name,
    job_id,
    salary    --default : DESC (내림차순 정렬)
FROM
    employees
ORDER BY
    last_name ASC,
    salary;


SELECT
    employee_id,
    last_name,
    hire_date,
    salary    
FROM
    employees
ORDER BY
    -- salary DESC,   -- 첫 정렬 이후
    -- hire_date;     -- 같은 값에 대해서 비교 후 정렬
    3,
    4;


-- 가장 큰 값 null이 우선 , null 값 끼리의 정렬은 데이터테이블의 순서대로

SELECT
    employee_id,
    last_name,
    commission_pct
FROM
    employees
ORDER BY
    commission_pct ASC;