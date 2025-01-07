-- Catesian Product (카테시안 프로덕트)
SELECT
    count(EMPLOYEE_ID)
FROM
    employees;


SELECT
    count(DEPARTMENT_ID)
FROM
    departments;


SELECT
    107 * 27
FROM
    dual;

SELECT
    -- count(*)
    *
FROM
    employees,
    departments
ORDER BY
    employee_id;


SELECT
    last_name,
    department_name
FROM
    employees,
    departments;


--equal 조인
SELECT
    last_name,

    employees.department_id,
    departments.department_id,

    department_name
    
FROM
    employees,
    departments
WHERE
    -- 중요) CP연산으로 발생된 연결 중에, 99% 잘못된 연결은 버리고 1%의 올바른 연결을 찾아내는 조건식 ( equal)
    employees.department_id = departments.department_id;


SELECT
    last_name,
    department_name,
    -- department_id
    departments.department_id    -- ORA-00918: column ambiguously defined
FROM
    employees,
    departments
WHERE
    employees.department_id = departments.department_id;


SELECT
    emp.last_name,
    dept.department_name,
    -- department_id
    emp.department_id    -- ORA-00918: column ambiguously defined
    
FROM
    employees emp,
    departments dept
WHERE
    emp.department_id = dept.department_id;

SELECT
    salary,
    emp.salary,
    department_name,

    emp.department_id AS emp_depart_id,
    dept.department_id AS dept_depart_id
FROM
    employees emp,
    departments dept
WHERE
    last_name='Whalen'
    AND emp.department_id = dept.department_id;
    -- emp.department_id = dept.department_id  -- 조인조건(Join Condition)
    -- AND last_name='Whalen';                 -- 검색조건(Check Condition)


SELECT
    -- t2.department_name AS 부서명,
    -- count(t1.employee_id) AS 인원수

    department_name AS 부서명,
    count(employee_id) AS 인원수
FROM
    employees t1,
    departments t2
WHERE
    t1.department_id = t2.department_id       -- 조인조건
    AND to_char( hire_date , 'YYYY') <= 2005  -- 검색조건
GROUP BY
    -- t2.department_name;
    department_name;


DROP TABLE job_grades PURGE;    -- 테이블 삭제

CREATE TABLE job_grades (
    grade_level VARCHAR2(3)
        CONSTRAINT job_gra_level_pk PRIMARY KEY,
    lowest_sal NUMBER,
    highest_sal NUMBER
);

INSERT INTO job_grades VALUES('A', 1000, 2999);
INSERT INTO job_grades VALUES('B', 3000, 5999);
INSERT INTO job_grades VALUES('C', 6000, 9999);
INSERT INTO job_grades VALUES('D', 10000, 14999);
INSERT INTO job_grades VALUES('E', 15000, 24999);
INSERT INTO job_grades VALUES('F', 25000, 40000);

COMMIT;

DESC job_grades;

SELECT * FROM job_grades;



-- 2개의 테이블 조인
SELECT
    first_name,
    last_name,
    salary,
    -- grade_level
    g.*
FROM
    employees e,
    job_grades g
WHERE
    e.salary 
    BETWEEN g.lowest_sal AND g.highest_sal
    AND e.last_name = 'King';
    -- AND e.first_name = 'Steven';

    -- e.salary BETWEEN 1000 AND 3000;


-- 3개의 테이블 조인
SELECT
    last_name,
    salary,
    department_name,
    grade_level
FROM
    employees e,
    departments d,
    job_grades g
WHERE
    e.department_id = d.department_id                       -- Equal Join
    AND e.salary BETWEEN g.lowest_sal AND g.highest_sal;    -- Non-equal Join


SELECT
    last_name, 
    employee_id,        -- 이건 나의 사번
    manager_id          -- 이건 나의 관리자의 사번
FROM 
    employees;


SELECT
    manager_id,     -- 관리자 사번
    last_name       -- 나의 이름
FROM
    employees e
ORDER BY
    2 ASC;


SELECT 
    employee_id, 
    last_name
FROM 
    EMPLOYEES e
ORDER BY 
    1 ASC;


DROP TABLE manager PURGE;

CREATE TABLE manager        -- 관리자 테이블
AS
    SELECT
        DISTINCT 
            employee_id,    -- 관리자 사번
            last_name       -- 관리자 이름
    FROM
        employees;


SELECT
    e.employee_id AS 직원사번,
    e.last_name AS 사원명,
    e.manager_id AS 관리자사번1,
    m.employee_id AS 관리자사번2,
    m.last_name AS 관리자명
FROM
    employees e,
    employees m
WHERE
    e.manager_id = m.employee_id; 


SELECT
    -- 사원 테이블의 컬럼들
    e.employee_id AS 사원번호,
    e.last_name AS 사원명,
    e.manager_id AS 관리자번호,

    -- 관리자 테이블의 컬럼들
    m.employee_id AS 사원번호,
    m.last_name AS 관리자명
FROM
    employees e,        -- 사원 정보
    employees m         -- 관리자 정보(가상)
WHERE
    e.manager_id = m.employee_id;



SELECT
    e.employee_id AS 사원번호,
    e.last_name AS 사원명,
    e.manager_id AS 관리자번호,
    m.last_name AS 관리자명

    -- m.*

    -- e.last_name AS 사원명,
    -- m.last_name AS 관리자명
FROM
    employees e,        -- 사원 정보
    employees m         -- 관리자 정보(가상)
WHERE
    e.manager_id = m.employee_id (+);
    -- e.manager_id (+) = m.employee_id;




-- 이 쿼리의 목적은, 
-- 각 사원의 "관리자의 관리자"가 누구인지를 찾아내기위해
-- 셀프조인을 2번 하는 것임.
SELECT
    e.last_name AS 사원명,
    m.last_name AS 관리자명,
    mm.last_name AS "관리자의 관리자명"
FROM
    employees e,
    employees m,
    employees mm
WHERE
    e.manager_id = m.employee_id
    AND m.manager_id = mm.employee_id;


SELECT
    e.last_name AS 사원명,
    m.last_name AS 관리자명,
    mm.last_name AS "관리자의 관리자명"
FROM
    employees e,
    employees m,
    employees mm
WHERE
    -- 사원 중에서, 관리자가 없는 사원까지 포함
    e.manager_id = m.employee_id (+)
    -- 관리자 중에서, 관리자가 없는 관리자까지 포함
    AND m.manager_id = mm.employee_id (+);


