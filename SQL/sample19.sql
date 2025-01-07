-- SELECT #1: Whalen 의 월급조회(4400)
SELECT
    salary
FROM
    employees
WHERE
    last_name = 'Whalen';


-- SELECT #2: Whalen 보다 월급을 많이 받는 사원 조회
SELECT
    last_name,
    salary
FROM
    employees
WHERE
    salary >= 4400;   -- Whalen의 월급지정


SELECT  -- 메인쿼리
    last_name,
    salary
FROM
    employees
WHERE
    -- 2nd. Whalen 보다 많은 월급여를 받는 사원조회
    salary >= (
        -- 서브쿼리(= 비상관 부속질의):
        --  a. 단독 수행에 지장없음
        --  b. Whalen의 월급여가 메인쿼리에 전달됨
        SELECT
            salary
        FROM
            employees
        WHERE
            last_name = 'Whalen'  -- 1st. Whalen 의 월급여 획득
    );



SELECT
    last_name,
    salary,
    ( SELECT avg(salary) FROM employees ) AS 평균급여
FROM
    employees
WHERE
    -- 메인쿼리: 단일행 서브쿼리가 사용되었으므로, 단일값과 비교가능한, 비교연산자 사용가능.
    salary >= ( 
        -- 단일 행 비상관 서브쿼리: 모든 사원의 평균 월급여 반환
        -- 메인쿼리로 결과값 전달
        SELECT
            avg(salary) 
        FROM
            employees
    );


SELECT
    last_name,
    salary
FROM
    employees
WHERE
    salary = (
        
        SELECT
            max(salary)
        FROM
            employees
        WHERE
            department_id = 100
    );


SELECT
    department_id,
    min(salary),
    (SELECT
        max(salary)
    FROM
        employees
    WHERE
        department_id = 100) AS "MaxSalaryOf100Dept"
FROM
    employees
GROUP BY
    department_id
HAVING
    min(salary) > (
        SELECT
            max(salary)
        FROM
            employees
        WHERE
            department_id = 100
    );


SELECT
    last_name,
    hire_date
FROM
    employees
WHERE
    -- 메인쿼리 1차 필터링: 단일행 서브쿼리가 사용되었으므로, 단일 값과 비교가능한, 비교연산자 사용가능
    hire_date > (
        -- 단일 행 비상관 서브쿼리: Whalen의 채용일자 반환
        -- 메인쿼리로 결과값 전달
        SELECT
            hire_date
        FROM
            employees
        WHERE
            last_name =  'Whalen'
    );

    