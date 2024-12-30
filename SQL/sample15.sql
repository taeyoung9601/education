DESC employees;


SELECT
    department_id,
    last_name
FROM
    employees
ORDER BY
    department_id;

DESC departments;   

SELECT
    department_id,
    department_name
FROM
    departments
ORDER BY
    1 ASC;


SELECT
    last_name,
    department_id
FROM
    employees
WHERE
    last_name = 'Whalen';

SELECT
    department_name
FROM
    departments
WHERE
    department_id = 10;


SELECT
    department_name
FROM
    departments
WHERE department_id = (
    SELECT
        department_id
    FROM
        employees
    WHERE
        last_name = 'Whalen');