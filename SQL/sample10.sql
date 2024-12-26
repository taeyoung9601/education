SELECT
    round (456.789, -1)
FROM
    dual;

SELECT
    round (456.789, 2)
FROM
    dual;    

SELECT
    round (456.789)
FROM
    dual;


SELECT
    trunc (456.789, 2)
FROM
    dual;

SELECT
    trunc (456.789, -1)
FROM
    dual;

SELECT
    trunc (456.789)
FROM
    dual;


SELECT
    mod(10,3)
    -- mod(10,0)  10반환
FROM
    dual;


SELECT
    employee_id,
    last_name,
    salary
FROM
    employees
WHERE
    mod(employee_id,2)!=0;


SELECT
    ceil(10.6),
    ceil(-10.6)
FROM
    dual;

SELECT
    floor(10.6),
    floor(-10.6)
FROM
    dual;

SELECT
    sign(100),
    sign(-20),
    sign(0)
FROM
    dual;


SELECT
    employee_id,
    last_name,
    salary
FROM
    employees
WHERE
    sign(salary - 15000 ) = -1;

-- sign 함수는, 비교연산자를 대체할 수 있음(>,<,=,>=,<=)

