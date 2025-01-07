SELECT
    last_name,
    salary
FROM
    employees
WHERE
    salary IN(
        SELECT 
            salary
        FROM 
            employees
        WHERE 
            last_name IN ('Whalen', 'Fay')
    );


SELECT
    last_name,
    department_id,
    salary
FROM
    employees
WHERE
    department_id IN (
        SELECT
            department_id
        FROM
            employees
        WHERE
            salary > 13000
    );


SELECT
    salary
FROM
    employees
WHERE
    job_id = 'IT_PROG'
ORDER BY
    1 ASC;


SELECT
    last_name,
    department_id,
    salary
FROM
    employees
WHERE
    salary < ALL ( 
        SELECT 
            salary
        FROM
            employees
        WHERE
            job_id = 'IT_PROG' 
    );


SELECT
    last_name,
    department_id,
    salary
FROM
    employees
WHERE
    salary > ALL ( 
        SELECT 
            salary
        FROM
            employees
        WHERE
            job_id = 'IT_PROG' 
    );


SELECT
    last_name,
    department_id,
    salary
FROM
    employees
WHERE
    salary > ANY (
        SELECT
            salary
        FROM
            employees
        WHERE
            job_id = 'IT_PROG'
    );


SELECT
    last_name,
    department_id,
    salary
FROM
    employees
WHERE
    salary < ANY (
        SELECT
            salary
        FROM
            employees
        WHERE
            job_id = 'IT_PROG'
    );


SELECT
    last_name,
    department_id,
    salary,
    commission_pct
FROM
    employees
WHERE
    EXISTS (
        SELECT
            employee_id
            -- 1
        FROM
            employees
        WHERE
            -- commission_pct IS NOT NULL
            last_name Like 'A%'
    );

SELECT
    last_name,
    department_id,
    salary
FROM
    employees
WHERE
    EXISTS (
        SELECT
            1
        FROM
            employees
        WHERE
            salary > 500000
    );