SELECT
    last_name,
    salary,
    job_id,
    commission_pct
FROM
    employees
WHERE
-- 순서:(1) 괄호( )
--      (2) 비교 연산자
--      (3) NOT 연산자
--      (4) AND 연산자
--      (5) OR 연산자
   (job_id = 'AC_MGR' 
    OR job_id = 'MK_REP')
    -- job_id IN ('AC_MGR','MK_REP')
    AND commission_pct IS NULL
    AND salary >= 4000
    AND salary <= 9000;
    -- AND salary BETWEEN 4000 AND 9000;