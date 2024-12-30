SELECT
    decode(                         -- if
        salary,                     --   column
            1000, salary * 0.1,     -- if (salary = 1000):
            2000, salary * 0.2,     -- elif (salary  = 2000):
            3000, salary * 0.3,     -- elif (salary = 3000):
            salary*0.4              -- else:
    ) AS incentive
FROM
    employees;


SELECT 
    last_name,
    salary,
    decode(
        salary, 
        24000, salary * 0.3,
        17000, salary * 0.2, 
        salary
    ) AS incentive
FROM
    employees
ORDER BY
    2 desc;


SELECT
    count(*) AS "총인원수",
    sum( decode( to_char(hire_date, 'YYYY'), 2001, 1, 0) ) AS "2001",
    sum( decode( to_char(hire_date, 'YYYY'), 2002, 1, 0) ) AS "2002",
    sum( decode( to_char(hire_date, 'YYYY'), 2003, 1, 0) ) AS "2003",
    sum( decode( to_char(hire_date, 'YYYY'), 2004, 1, 0) ) AS "2004",
    sum( decode( to_char(hire_date, 'YYYY'), 2005, 1, 0) ) AS "2005",
    sum( decode( to_char(hire_date, 'YYYY'), 2006, 1, 0) ) AS "2006",
    sum( decode( to_char(hire_date, 'YYYY'), 2007, 1, 0) ) AS "2007",
    sum( decode( to_char(hire_date, 'YYYY'), 2008, 1, 0) ) AS "2008"
FROM
    employees;


SELECT sum(salary) AS 월총급여지출액
FROM employees;   

SELECT
    CASE salary
        WHEN 1000 THEN salary * 0.1
        WHEN 2000 THEN salary * 0.2
        WHEN 3000 THEN salary * 0.3
        ELSE salary * 0.4
    END AS "인센티브"
FROM
    employees;


SELECT
    salary,
    CASE 
        WHEN salary > 1000 THEN salary * 0.1
        WHEN salary > 2000 THEN salary * 0.2
        WHEN salary > 3000 THEN salary * 0.3
        ELSE salary * 0.4
    END AS "인센티브"
FROM
    employees;

SELECT
    last_name,
    salary,

    CASE salary
        WHEN 24000 THEN salary * 0.3
        WHEN 17000 THEN salary * 0.2
        ELSE salary
    END AS 보너스
FROM
    employees
ORDER BY
    2 desc;


SELECT
    last_name,
    salary,

    CASE
        WHEN salary BETWEEN 2000 AND 25000 THEN '상'
        -- WHEN salary BETWEEN 10000 AND 20001 THEN '중'
        WHEN salary BETWEEN 5000 AND 9000 THEN '중'
        ELSE '하'
    END AS 등급
FROM
    employees
ORDER BY
    2 desc;


SELECT
    last_name,
    salary,

    CASE
        WHEN salary IN (24000, 17000 , 14000) THEN '상'
        WHEN salary IN (13500, 13000) THEN '중'
        ELSE '하'
    END AS 등급
FROM
    employees
ORDER BY
    2 desc;


SELECT
    sum(salary),            -- 총월인건비
    sum(ALL salary),        -- Ditto.
    sum(DISTINCT salary)    -- 중복된 급여는 제외하고
FROM
    employees;

SELECT
    sum(salary),

    avg(salary),
    avg(ALL salary),
    avg(DISTINCT salary)
FROM
    employees;


SELECT
    max(salary),
    min(salary)
FROM
    employees;


SELECT
    min( hire_date ),
    max( hire_date)
FROM
    employees;


SELECT
    min( last_name ),
    max( last_name)
FROM
    employees;



SELECT
    count(*),
    count(last_name),
    count(commission_pct)
FROM
    employees;


SELECT
    count(job_id),
    count(DISTINCT job_id)
FROM
    employees;


-- 해당 테이블의 전체 레코드 개수 구하기 (*주의필요*)
SELECT
    count(*),               -- Decommended
    count(commission_pct),  -- *Causion (removed all NULLs)
    count(employee_id)      -- *Recommended (Primary Key = Unique + Not NULL)
FROM
    employees;

