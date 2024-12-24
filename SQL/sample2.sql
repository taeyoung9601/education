--SELECT 문의 기본 구조와 각 절의 실행 순서
-- Clauses - 실행순서-

--SELECT clause (5)("필수"): SELECT 컬럼들 -> 출력할 컬럼들 선택
--FROM clause (1)("필수"): FROM<테이블 명> -> 행 데이터 공급
--                 ("옵션"): 오픈 소스 DB -> 보통 옵션
--WHERE clause (2)("옵션"): WHERE<조건식> -> 1차 필터링 (행을 필터링)
--GROUP BY clause (3)("옵션"): GROUP BY <그룹 핑기준> -> 헤쳐모여! ->
--HAVING clause (4) ("옵션"): HAVING<조건식> -> 2차 필터링 (소그룹들)
--ORDER BY clause (6) ("옵션"): ORDER BY<정렬 기준> -> 최종 정렬 수행


--SELECT(PROJECTION) 문의 기본 문법 구조
--SELECT[DISTINCT]{*|column [AS] [alias],...} FROM<테이블명>

--1. To project all columns of the table

--SELCCT * 
--FROM table;

SELECT * 
FROM employees;

SELECT *
FROM departments;

--2. To project only the specified columns of the table
--SELECT column1[,column2,...,columnN]
--FROM table;

SELECT
    employee_id,
    last_name,
    hire_date,
    salary
FROM
    employees;


--SELECT column1 -/+/* column2 FROM table

SELECT
    salary,
    salary + 100
FROM
    employees;

SELECT
    salary,
    salary - 100
FROM
    employees;

SELECT
    salary,
    salary * 100
FROM
    employees;

SELECT
    salary,
    salary / 100
FROM
    employees;

SELECT
    last_name,
    salary,
    salary * 12
FROM
    employees;


--4.About SYS.DUAL table
--SYS account ouwns this DUAL tabel.
--If you don't need a table, the DUAL table needed

SELECT
    245 * 567
FROM
    dual; --참고로, MySQL/Mariadb/Postgresql 에서는 생략 가능!

-- 원래 다른 계정 소유의 테이블을 사용하려면,
-- 아래와 같이, <소유자 계정명>,<DB객체>형식으로 사용해야 합니다.
-- 물론, 이것도 해당 소유자가 해당 DB 객체에 대한 접근을 해제해 놓았을
-- 때죠.. SYS 계정은 dual 테이블에 대한 접근 제어를 모두 풀어 놓았습니다.
DESC sys.dual;

--SYS 계정이 dual 테이블을 public 으로 동의어(synomys)를 생성하였고
--모든 계정이 사용할 수 있도록 public synonym을 생성하였기 때문에
--DB객체의 이름만으로도 접근 가능
DESC dual;

SELECT 
*
FROM
    dual;

SELECT
*
FROM
    sys.dual;



    -- Clauses - 실행순서-

--SELECT clause (5)
--FROM clause (1)           
--WHERE clause (2)
--GROUP BY clause (3)
--HAVING clause (4) 
--ORDER BY clause (6)


--***SELECT 문의 기본 문법 구조
--SELECT[DISTINCT]{*|column [AS] [alias],...}
--FROM<테이블명>


--1. 특정 컬럼에 alias(별칭) 만들기
--SELECT 

SELECT
    last_name AS "사원 이름",
    salary AS "사원 월급",
    salary * 12 AS 연봉
FROM
    employees;


--3. NULL 값 이해하기
-- 분석 입장에서는, "결측치(Missing Value)"라고 함.
-- 이러한 결측치가 발생하는 경우는,
-- (1) 실험 측정시, 측정이 안되는 경우
-- (2) 실험 측정 장치의 오동작
-- (3) 관찰로 얻어지는 경우라면, 관찰이 안 된 경우
-- (4) 관찰자(사람)의 실수 등 다양함


SELECT
    employee_id ,
    last_name ,
    job_id,
    commission_pct
FROM
    employees;

--결측치~ 

SELECT
    last_name AS 이름,
    salary AS 월급,
    commission_pct AS 수수료,
    salary * 12 + nvl(commission_pct,0) AS 연봉
FROM
    employees;

