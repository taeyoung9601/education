--(1) 문자열 + 숫자
SELECT
    last_name || salary AS "이름 월급"
FROM
    employees;

--(2) 문자열 + 날짜

SELECT
    last_name || hire_date AS "별칭"
FROM
    employees;

--(3) 문자 + 리터럴(문자)
DESCRIBE employees;
DESC employees;

SELECT
    last_name || '사원'
FROM
    employees;


SELECT
    last_name||'의 직업은'||job_id||'입니다.' AS "사원별 직무"
FROM
    employees;


SELECT
    last_name || '의 직무은' || job_id
FROM
    employees
WHERE                    
    last_name = 'Smith'; 


SELECT 
    DISTINCT job_id
FROM
    employees;

-- SELECT
--     DISTINCT last_name,first_name
SELECT DISTINCT *    -- => 모든 컬럼이 같아야 중복
FROM 
    employees;