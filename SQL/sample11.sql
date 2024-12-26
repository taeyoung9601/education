DESC nls_session_parameters;

SELECT 
    *
FROM
    nls_session_parameters;

SELECT
    sysdate
FROM
    dual;

ALTER SESSION SET NLS_DATE_FORMAT = 'RR/MM/DD';


SELECT
    sysdate AS DO_NOT_USE_THIS1,
    systimestamp AS DO_NOT_USE_THIS2,
    current_date,
    current_timestamp
FROM
    dual;

ALTER SESSION SET NLS_DATE_FORMAT='YYYY/MM/DD HH24:MI:SS';
ALTER SESSION SET NLS_TIMESTAMP_FORMAT='YYYY/MM/DD HH24:MI:SS';

SELECT
    current_date,
    current_timestamp
FROM
    dual;

SELECT
    current_date + 1 AS 내일
FROM
    dual;


SELECT
    last_name,
    hire_date,
    current_date - hire_date,
   (current_date - hire_date ) / 365 ,  
   trunc ( (current_date - hire_date) / 365) AS 근속년수
FROM
    employees
ORDER BY 
    3 DESC;

SELECT
    last_name,
    hire_date,
    months_between(current_date, hire_date) AS "근속월수(소숫점 포함)"
FROM
    employees;
    


SELECT
    current_date AS 오늘,
    add_months(current_date, 1) AS "1개월 후 오늘",
    add_months(current_date, -1) AS "1개월 전 오늘"
FROM
    dual;


SELECT
    last_name,
    hire_date,
    next_day(hire_date,'FRI'),
    next_day(hire_date,6)
FROM
    employees
ORDER BY
    3 desc;
    

SELECT
    last_name,
    hire_date,
    last_day(hire_date)
FROM
    employees
ORDER BY
    2 desc;


SELECT
    last_name,
    hire_date,
    next_day(add_months(hire_date, 5), 'SUN')
FROM
    employees
ORDER BY
    2 DESC;


SELECT
    last_name,
    hire_date,
    round(hire_date,'YEAR'),
    round(hire_date,'MONTH')
FROM
    employees;

SELECT
    last_name,
    hire_date,
    trunc(hire_date, 'YEAR'),
    trunc(hire_date, 'MONTH'),
    trunc(current_timestamp, 'MONTH')
FROM
    employees;



--/////////////////////////개인과제////////////
--45살의 한계나이 에 해당 날짜 구하기

SELECT
    (add_months(current_date, 20 * 12))AS 한계나이
FROM
    dual;

--////////////////////////////////////////////

