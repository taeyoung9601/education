SELECT
    'ORACLE SQL',
    initcap('ORACLE SQL')
FROM
    dual;


SELECT
    email,
    initcap(email)
FROM
    employees; 



SELECT
    'Oracle Sql',
    upper('Oracle Sql')
FROM
    dual;

SELECT
    last_name,
    upper(LAST_NAME)
FROM
    employees;




SELECT
    last_name,
    salary
FROM
    employees
WHERE
    last_name = initcap('KING');


SELECT
    'ORACLE',
    lower ('ORACLE')
FROM
    dual;

SELECT
    last_name,
    lower (last_name)
FROM
    employees;

SELECT
    'Oracle'||'Sql',
    concat('Oracle', 'Sql')
FROM
    dual;

SELECT
    last_name || salary,
    concat(last_name,salary)
FROM
    employees;

SELECT
    'Oracle'||'Sql'||'Third',
    concat(concat('Oracle','Sql'),'Third')
FROM
    dual;


SELECT
    'Oracle',
    length('Oracle'),
    lengthb('Oracle'),
    lengthb('오라클')
FROM
    dual;

SELECT
    last_name,
    length(last_name)
FROM
    employees;


SELECT
    '한글',
    length('한글') AS length,
    lengthb('한글') AS lengthb
FROM
    dual;



SELECT
    unistr('\D55C\AE00'),
    length( unistr('\D55C\AE00')),
    lengthb( unistr('\D55C\AE00'))
FROM
    dual;


SELECT
    instr('MILLER', 'L', 1, 2),
    instr('MILLER', 'X', 1, 2)
FROM
    dual;

SELECT
    substr('123456-1234567', 1, 6),
    substr('123456-1234567', 8),
    substr('123456-1234567', 5, 8) || '*******' as "주민등록번호"
FROM
    dual;


---------------------------------------------------
ALTER SESSION SET NLS_DATE_FORMAT = 'RR/MM/DD';

SELECT
    hire_date AS 입사일,
    substr(hire_date, 1 , 2) AS 입사년도
FROM
    employees;

---------------------------------------------------
ALTER SESSION SET NLS_DATE_FORMAT = 'DD-MON-RR';

SELECT
   hire_date,
   to_char(hire_date) AS 입사일,
                  --  'DD-MON-RR'
   to_char(hire_date, 'RR/MM/DD') AS 일사일2,
   substr( to_char(hire_date), 8 ,2) AS 입사년도
FROM
   employees;



SELECT
   '900303-1234567',
   substr('900303-1234567', 8)
FROM
   dual;


-- 그런데, offset index를 음수를 사용할 수 있다면????ㅠ
SELECT
   '900303-1234567',
   substr('900303-1234567', -7)
FROM
   dual;

SELECT
   replace('JACK and JUE', 'J', 'BL')
FROM
   dual;


SELECT
   lpad('MILLER', 10, '*')
FROM
   dual;

SELECT
   rpad('MILLER', 10, '*')
FROM
   dual;

SELECT
    rpad(
        substr('900303-1234567',1,8) ,14,'*'
    )AS 주민번호
FROM
    dual;


SELECT
    ltrim(' MMMIMLLER ')
FROM
    dual;

SELECT
    ltrim('MILLER','M'),
    length(ltrim('MILLER', 'M'))
FROM
    dual;


SELECT
    trim(BOTH '0' FROM '0001203400')
FROM
    dual;

SELECT
    trim( LEADING '0' FROM '0001203400')
FROM
    dual;

SELECT
    trim( TRAILING '0' FROM '0001203400')
FROM
    dual;

SELECT
   trim(
      TRAILING '0' 
      FROM ( trim( LEADING '0' FROM '0001203400' ) ) 
   )    -- 문자열 양쪽에서 제거
FROM
   dual;
