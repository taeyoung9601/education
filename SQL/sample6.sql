SELECT
    job_id,
    last_name,
    salary
FROM
    employees
WHERE
    -- NOT salary < 20000;
    -- (NOT salary < 20000);
    -- NOT (salary < 20000);
    -- salary NOT IN (9000,8000,6000);
    NOT salary IN (9000,8000,6000)
ORDER BY
    3 DESC;


SELECT
    job_id,
    last_name,
    salary
FROM
    employees
WHERE
    -- last_name NOT LIKE 'J%';
    NOT last_name LIKE 'J%';


SELECT
    job_id,
    last_name,
    salary
FROM
    employees
WHERE
    -- salary NOT BETWEEN 2400 AND 200000;
    NOT (salary BETWEEN 2400 AND 200000);


SELECT
    last_name,
    job_id,
    salary,
    commission_pct
FROM
    employees
WHERE
    -- commission_pct = NULL;  : XX
    -- commission_pct IS NULL;
    nvl(commission_pct,-1) = -1;







