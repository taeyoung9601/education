--Date output format: RR/MM//DD only in the oracle SQL Developer
--ALTER SESSION SET NLS_DATE_FORMAT = 'RR/MM/DD';
ALTER SESSION SET NLS_DATE_FORMAT = 'yyyy/MM/dd hh24:mi:ss';
-- ALTER SESSION SET NLS_DATE_FORMAT = 'RR/MM/DD';

SELECT
    employee_id,
    last_name,
    job_id,
    salary
FROM
    employees
WHERE
    -- salary >= 10000;
    last_name ='King';

SELECT
    employee_id,
    last_name,
    salary,
    hire_date
FROM
    employees
WHERE
    -- hire_date > '07/12/31'; --자동 형변환
    --강제형변환 함수 to_date() , 가독성 확보
    --데이터베이스에서는 가능하면, 강제 형 변환 선호
    hire_date > to_date('07/12/31', 'RR/MM/DD');


SELECT
    employee_id,
    last_name,
    salary,
    hire_date
FROM
    employees
WHERE
    -- salary BETWEEN 7000 AND 8000;
    -- hire_date BETWEEN '07/01/01' AND '08/12/31';
    -- hire_date 
    -- BETWEEN to_date('07/12/31', 'RR/MM/DD')
    -- AND to_date('08/12/31', 'RR/MM/DD');
    employee_id IN (100,200,300);


SELECT
    employee_id,
    last_name
FROM
    employees
WHERE
    employee_id = 100
    OR employee_id = 200
    OR employee_id = 300;


SELECT
    employee_id,
    first_name,
    last_name,
    hire_date
FROM
    employees
WHERE
    -- last_name IN ('King', 'Abel', 'Jones');
    -- hire_date IN ('01/01/13','07/02/07');
    hire_date IN (
    to_date ('01/01/13', 'RR/MM/DD'),
    to_date ('07/02/07', 'RR/MM/DD')
    );


SELECT
    employee_id,
    last_name
FROM
    employees
WHERE
    -- last_name LIKE 'J%';
    -- last_name LIKE '%ai%';
    last_name LIKE '%in';


SELECT
    last_name,
    job_id
FROM
    employees
WHERE
    -- last_name LIKE '_b%';
    -- last_name LIKE '_____d';
    -- last_name LIKE '%d';
    -- last_name LIKE '%';
    -- last_name Like '%_%';
    --탈출 문자 (Escape Character):
    --특수한 의미를 가지는 기호의 기능
    -- job_id LIKE '%$_%' ESCAPE '$';
    -- last_name LIKE '%$_%' ESCAPE '$';
    job_id LIKE '%E___%' ESCAPE 'E';


SELECT
    job_id,
    salary
FROM
    employees
WHERE
    job_id = 'IT_PROG'
    AND salary >= 5000;

SELECT
    --날짜 -> 문자 데이터로 강제 형변환 함수(to_char)
    --함수는 소문자로 한다!!
    -- 아래의 sysdate도 함수, current_date 도 함수입니다.
    to_char(sysdate,'yyyy/MM/dd hh24:mi:ss') AS now_sys,
    to_char(current_date,'yyyy/MM/dd hh24:mi:ss') AS now_current
FROM
    dual;
