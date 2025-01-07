SELECT
    t1.last_name,
    -- last_name,

    t2.department_name,
    -- department_name,
    
    department_id                -- OK: 공통컬럼

    -- ORA-00904: "DEPARTMENTS"."DEPARTMENT_ID": invalid identifier
    -- 두 테이블의 공통컬럼은 식별자를 가질 수 없음.
    -- departments.department_id    -- XX: 공통컬럼

    -- ORA-25154: column part of USING clause cannot have qualifier
    -- t2.department_id             -- XX: 공통컬럼
FROM
    employees t1 INNER JOIN departments t2
    -- employees t1 JOIN departments t2    -- INNER 단어 생략가능

    USING(department_id);


SELECT
    last_name,
    department_name,

    -- 두 테이블의 공통컬럼은 식별자를 가질 수 없음.
    department_id,          -- 공통컬럼1
    manager_id              -- 공통컬럼2
FROM
    employees t1 INNER JOIN departments t2
    -- employees t1 JOIN departments t2    -- INNER 단어 생략가능
    USING(department_id, manager_id)
WHERE
    department_id = 90;  -- 검색조건

-- ORA-25154: column part of USING clause cannot have qualifier
-- WHERE
--     t2.department_id = 90;  -- 검색조건



SELECT
    t1.last_name,
    -- last_name,
    t2.department_name,
    -- department_name,

    -- ORA-00918: column ambiguously defined
    -- department_id           -- XX: 공통컬럼
    t1.department_id        -- OK: 공통컬럼
    -- t2.department_id        -- OK: 공통컬럼
    -- ORA-00904: "DEPARTMENTS"."DEPARTMENT_ID": invalid identifier
    -- departments.department_id        -- XX: 공통컬럼
FROM
    employees t1 INNER JOIN departments t2
    -- employees t1 JOIN departments t2    -- INNER 키워드 생략가능
    -- 명시적으로 조인조건 지정
    ON t1.department_id = t2.department_id;



-- ON 절에 검색조건 추가 <-------- ************ : Wrong Case
SELECT
    last_name,
    department_name,
    t1.department_id
FROM
    employees t1 
    INNER JOIN 
    departments t2
    -- employees t1 JOIN departments t2            -- INNER 키워드 생략가능
    ON t1.department_id = t2.department_id      -- 조인조건
    AND t1.department_id = 90;                  -- 검색조건 (가독성 저하)


-- ON절을 이용한, Self Join
SELECT
    e.employee_id AS 사원번호, 
    e.last_name AS 사원명,
    m.last_name AS 관리자명
FROM
    employees e INNER JOIN employees m
    -- employees e JOIN employees m       -- INNER 키워드 생략가능
    ON e.manager_id = m.employee_id       -- 1. Join 조건
    AND e.employee_id = 170;   



-- ANSI Join 에서도, 2개 이상의 테이블 조인 가능.
-- ON 절을 추가로 사용하여, 여러 테이블 조인 수행
-- 3개의 테이블 조인
SELECT
    e.last_name AS 사원명,
    d.department_name AS 부서명,
    g.grade_level AS 등급
FROM
    employees e INNER JOIN departments d
    -- employees e JOIN departments d              -- INNER 키워드 생략가능
    ON e.department_id = d.department_id
    
    INNER JOIN job_grades g
    -- JOIN job_grades g                           -- INNER 키워드 생략가능
    ON e.salary BETWEEN g.lowest_sal AND g.highest_sal; -- Non-equal 조인조건


SELECT
    e.last_name AS 사원명,
    d.department_name AS 부서명,
    g.grade_level AS 등급
FROM
    employees e INNER JOIN departments d
    -- employees e
    -- JOIN departments d              -- INNER 키워드 생략가능
    -- ON e.department_id = d.department_id
    USING(department_id)                        -- Equal(= Equi) 조인조건

    INNER JOIN job_grades g
    -- JOIN job_grades g                           -- INNER 키워드 생략가능
    ON e.salary BETWEEN g.lowest_sal AND g.highest_sal; -- Non-equal 조인조건



SELECT
    e.last_name AS 사원명,
    m.last_name AS 관리자명
FROM
    -- 일치하는 데이터가 없는 테이블의 별칭이 e를 가진 LEFT
    -- 이기 때문에, LEFT OUTER JOIN 지정하고, ON 절을 사용하여
    -- 조인조건 지정.

    -- employees e LEFT OUTER JOIN employees m
    -- employees e LEFT JOIN employees m           -- OUTER 키워드 생략가능

    -- employees e RIGHT OUTER JOIN employees m
    -- employees e RIGHT JOIN employees m          -- OUTER 키워드 생략가능      

    employees e FULL OUTER JOIN employees m
    -- employees e FULL JOIN employees m           -- OUTER 키워드 생략가능

    ON e.manager_id = m.employee_id;

