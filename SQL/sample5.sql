SELECT
    employee_id,
    first_name,
    last_name,
    hire_date
FROM
    employees
WHERE
    -- last_name IN ('King', 'Abel', 'Jones');
    hire_date IN ('01/01/13','07/02/07');
    -- hire_date IN (
    -- to_date ('01/01/13', 'RR/MM/DD'),
    -- to_date ('07/02/07', 'RR/MM/DD')
    -- );