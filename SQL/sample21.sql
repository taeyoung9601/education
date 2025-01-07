SELECT
    last_name,
    department_id,
    salary
FROM
    employees
WHERE
    (department_id, salary) IN (
        SELECT
            department_id,
            max(salary)
        FROM
            employees
        GROUP BY
            department_id  --NULL 그룹도 포함!
    )
ORDER BY
    2 ASC;


--인라인 뷰

SELECT
    d.department_id,
    max(d.department_name) AS 부서명,
    sum(salary) AS 총합,
    avg(salary) AS 평균,
    count(employee_id) AS 인원수
FROM
    (SELECT department_id,salary,employee_id
    FROM employees
    WHERE salary > 5000) e,

    departments d
WHERE
    e.department_id = d.department_id
GROUP BY
    d.department_id
ORDER BY
    1 ASC;
    

SELECT
    e.department_id AS 부서번호,
    d.department_name AS 부서명,
    총인건비,
    평균급여,
    부서원수
FROM
    (
        SELECT
            department_id,
            sum(salary) AS 총인건비,
            avg(salary) AS 평균급여,
            count(employee_id) AS 부서원수
        FROM
            employees
        GROUP BY
            department_id
    ) e,
    departments d
WHERE
    e.department_id = d.department_id
ORDER BY
    1 ASC;