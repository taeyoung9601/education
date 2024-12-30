SELECT
    DISTINCT department_id       -- NULL 포함
FROM
    employees
WHERE
    department_id IS NOT NULL;



-- 부서별 GROUP BY ( MAX 함수 )
SELECT
    department_id   AS 부서번호,        -- 그룹생성 단순컬럼
    avg(salary)     AS 평균월급,         -- 각 그룹마다 적용될 그룹함수
    min(salary)     AS 최소월급,        -- 각 그룹마다 적용될 그룹함수2
    max(salary)     AS 최대월급         -- 각 그룹마다 적용될 그룹함수1
FROM
    employees
GROUP BY
    department_id        -- OK
    -- 1                    -- X
    -- 부서번호              -- X
ORDER BY
    1 ASC;
    -- 최소월급 ASC, 최대월급 DESC;
    -- department_id ASC;
    -- 부서번호 ASC;

SELECT
    to_char( hire_date , 'YYYY' )   AS 년,      -- 다중그룹생성 표현식1
    to_char( hire_date , 'MM')      AS 월 ,     -- 다중그룹생성 표현식2
    sum(salary)                                 -- 각 그룹마다 적용될 그룹함수
FROM
    employees
GROUP BY
    to_char( hire_date , 'YYYY'),                -- 다중그룹생성 표현식1
    to_char( hire_date , 'MM')                   -- 다중그룹생성 표현식2
ORDER BY
    년 ASC;


SELECT
    department_id,
    sum(salary)                      -- 4th.
FROM
    employees                       -- 1st.
GROUP BY
    department_id                   -- 2nd.
HAVING
    sum(salary) >= 90000            -- 3rd.
ORDER BY
    1 ASC;                          -- 5th.


SELECT
    department_id,
    count(employee_id)
FROM
    employees
GROUP BY
    department_id                   -- NULL 그룹도 생성됨을 기억할 것!!!
HAVING
    count(salary) >= 6              -- 1st. filtering (for groups).
-- HAVING
--     salary >= 3000                  -- XX: 각 그룹에 대해, 단순컬럼들만 사용불가
-- HAVING
        --GROPU BY의 기준이 되는 단순 컬럼은 당연히 나올 수 있을 뿐만 아니라(마치 ㅡ SELECT절 연산과 같이), 지금 같은 경우는 그룹의 특성을
        --나타내는 단순 컬럼이기때문에
--     department_id IN (10, 20)       -- OK: GROUP BY절에 나열된 단순컬럼들은 사용가능
-- HAVING
--     department_id > 10              -- OK: GROUP BY절에 나열된 단순컬럼들은 사용가능
-- HAVING
--     department_id IS NULL           -- OK: NULL 그룹도 있음을 기억할 것!!
ORDER BY
    1 ASC;


-- 각 부서별, 월급여 총계 구하기
SELECT
    department_id,
    sum(salary)
FROM
    employees
WHERE
    salary >= 3000              -- 1st. filtering (for records).
GROUP BY
    department_id
HAVING
    sum(salary) >= 90000        -- 2nd. filtering (for groups).
ORDER BY
    1 ASC;