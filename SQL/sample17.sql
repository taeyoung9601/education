SELECT
    count(*)            -- 107
FROM
    employees;


SELECT
    count(*)            -- 27
FROM
    departments;


SELECT
    count(*)            -- 2889
    -- *
FROM
    employees CROSS JOIN departments;


SELECT
    last_name,
    department_name
FROM
    employees CROSS JOIN departments;


SELECT
    -- employees(t1) 에 있는 컬럼
    t1.last_name,           -- OK: 테이블 별침(t1)을 통한 컬럼 지정
    -- last_name               -- OK: t1에만 있는 컬럼이므로, 모호성 없음

    -- departments(t2) 에 있는 컬럼
    t2.department_name,     -- OK: 테이블 별침(t2)을 통한 컬럼 지정
    -- department_name         -- OK: t2에만 있는 컬럼이므로, 모호성 없음
    
    -- employees(t1), departments(t2) 모두에 있는 공통컬럼
    t1.manager_id,          -- OK: 테이블 별침(t1)을 통한 컬럼 지정
    -- manager_id,             -- XX: ORA-00918: 열의 정의가 애매합니다

    -- employees(t1), departments(t2) 모두에 있는 공통컬럼
    t2.department_id        -- OK: 테이블 별침(t2)을 통한 컬럼 지정
    -- department_id           -- XX: ORA-00918: 열의 정의가 애매합니다
FROM
    employees t1 CROSS JOIN departments t2;


-- ------------------------------------------------------
-- 1. Oracle Equal Join
-- ------------------------------------------------------
SELECT
    last_name,
    employees.department_id,
    departments.department_id,
    employees.manager_id,
    departments.manager_id,
    department_name
FROM
    employees,
    departments
-- WHERE
--     -- 두 테이블의 공통컬럼 department_id 으로 연결
--     employees.department_id = departments.department_id;    -- 올바른 조인조건
WHERE
    -- 두 테이블의 공통컬럼으로 department_id, manager_id 으로 연결하면,
    -- 의도하지 않은 결과 도출
    employees.department_id = departments.department_id
    AND employees.manager_id = departments.manager_id;


-- ------------------------------------------------------
-- 2. ANSI Natural Join
-- ------------------------------------------------------
DESC employees;
DESC departments;


SELECT
    last_name,
    department_id,      -- 공통컬럼1
    manager_id,         -- 공통컬럼2
    department_name
FROM
    -- 공통컬럼: manager_id, department_id    -> 엉뚱한 결과 도출
    employees NATURAL JOIN departments;


-- -------------------------------------------------------
-- ANSI JOIN 수행시, 
-- FROM절과 SELECT절에 테이블 별칭(Table Alis)를 사용하는 경우
-- -------------------------------------------------------
-- SELECT 절에, 테이블 별칭(table alias)이 적용된, 두 테이블의 컬럼 나열시, 
-- 테이블명.컬럼 형식으로 나열하면 오류발생
-- (테이블 별칭이 적용되었으면, 테이블 별칭 사용가능(옵션))
SELECT
    -- employees.last_name,    -- XX: ORA-00904: "EMPLOYEES"."LASTNAME": 부적합한 식별자
    last_name,              -- OK: 테이블 별칭 없이도 사용가능
    -- t1.last_name,           -- OK: 테이블 별칭 사용가능

    department_name         -- OK: 테이블 별칭 없이도 사용가능
    -- t1.department_name,        -- OK: 테이블 별칭 사용가능
FROM
    -- 공통컬럼: manager_id, department_id    -> 엉뚱한 결과 도출
    employees t1 NATURAL JOIN departments t2;


-- ANSI JOIN 수행시, 
-- FROM절과 SELECT절에 테이블 별칭(Table Alis)를 사용하는 경우
SELECT
    t1.last_name,
    t2.department_name

    manager_id  -- OK: 두 테이블의 공통컬럼 기재시, 테이블 별칭은 제거해야 함

    -- ORA-25155: column used in NATURAL join cannot have qualifier
    -- t2.manager_id              --  XX : 두 테이블의 공통컬럼 기재
FROM
    -- 공통컬럼: manager_id, department_id    -> 엉뚱한 결과 도출
    employees t1 NATURAL JOIN departments t2;



