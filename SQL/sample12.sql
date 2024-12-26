SELECT
    last_name,
    salary
FROM
    employees
WHERE
    salary = '17000';

SELECT
    to_char(current_date),
    to_char(current_date, 'YYYY/MM/DD, (AM) DY HH24:MI:SS')
FROM
    dual;

SELECT
    last_name,
    hire_date,
    salary
FROM
    employees
WHERE
    to_char(hire_date, 'MM') = '09';


SELECT
    to_char(sysdate,'YYYY "년" MM "월" DD "일" ') 날짜
FROM
    dual;

show parameter nls;

SELECT
    to_char(1234,'99999') AS "99999",
    to_char(1234,'099999') AS "099999",
    to_char(1234, '$99999')   AS "$99999",
    to_char(1234, '99,999')   AS "99,999",
    to_char(1234, 'B99999.99') AS "B99999.99",
    to_char(1234, 'L99999')   AS "L99999"
FROM
    dual;


SELECT
    to_number('123') + 100,         -- 강제형변환 (Casting)   : *Recommended*
    '456' + 100,                    -- 자동형변환 (Promotion) :  Decommended
    to_char(123) || '456',          -- 강제형변환 (Casting)   : *Recommended*
    123 || '456'                    -- 자동형변환 (Promotion) :  Decommended
FROM
    dual;

SELECT
    sysdate
FROM
    dual;


SELECT
    to_date('20170802181030', 'YYYYMMDDHH24MISS')
FROM
    dual;


SELECT
    current_date,
    current_date - to_date('20170101', 'YYYYMMDD')
FROM
    dual;
