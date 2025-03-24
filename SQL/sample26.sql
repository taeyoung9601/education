SELECT
    deptno,
    dname,
    loc
FROM
    SCOTT.dept;


CREATE TABLE scott.employee(
    empno NUMBER(4),
    ename VARCHAR2(20),
    hiredate DATE,
    sal NUMBER(7,2)
);

DESC employee;

CREATE TABLE employee2(
    empno NUMBER(4),
    ename VARCHAR2(20),
    hiredate DATE DEFAULT current_date,
    sal NUMBER(7,2),
    insert_ts DATE DEFAULT current_date,
    update_ts DATE
); -- DEFAULT 옵션 사용

DESC employee2;

INSERT INTO employee2(empno,ename,sal) 
VALUES (10,'홍길동', 3000);

SELECT
    *
FROM
    EMPLOYEE2;



CREATE TABLE department(
    deptno NUMBER(2) CONSTRAINT department_deptno_pk PRIMARY KEY,
    dname VARCHAR2(15),
    loc VARCHAR2(15)
);

DESC department;

CREATE TABLE department2(
    deptno NUMBER(2),
    dname VARCHAR2(15),
    loc VARCHAR2(15),

    CONSTRAINT department2_deptno_pk PRIMARY KEY (deptno,loc)
);

DEsc department2;

INSERT INTO department2 (deptno, dname, loc)
VALUES(10, '개발1팀', '서울');

INSERT INTO department2 (deptno, dname, loc)
VALUES(10, '개발2팀', '안산');

INSERT INTO department2 (deptno, dname, loc)
VALUES(10, '개발3팀', '안산');



DESC USER_CONSTRAINTS;


SELECT
    *
FROM
    USER_CONSTRAINTS
WHERE
    table_name IN ('DEPARTMENT','DEPARTMENT2');


SELECT
    *
FROM
    USER_CONS_COLUMNS
WHERE
    table_name IN ('DEPARTMENT', 'DEPARTMENT2');




CREATE TABLE department3 (
    deptno NUMBER(2)
        CONSTRAINT department3_deptno_pk PRIMARY KEY,
    dname VARCHAR2(15),
    loc VARCHAR2(15)
);

DESC department3;


CREATE TABLE department4 (
    deptno NUMBER(2),
    dname VARCHAR2(15),
    loc VARCHAR2(15),

    CONSTRAINT department4_deptno_pk PRIMARY KEY(deptno,loc)
);

DESC department4;

INSERT INTO department4 (deptno, dname, loc)
VALUES (10,'인사', '서울');

INSERT INTO department4 (deptno, dname, loc)
VALUES (10, '개발', '경기');

INSERT INTO department4 (deptnom, dname, loc)
VALUES (NULL, '개발', '경기');


SELECT
    *
FROM
    department4;


CREATE TABLE department5(
    deptno NUMBER(2)
        CONSTRAINT department5_deptno_pk PRIMARY KEY,
    dname VARCHAR2(15)
        CONSTRAINT department5_dname_uk UNIQUE,
    loc VARCHAR2(15)
);

DESC department5;

INSERT INTO department5 (deptno, dname, loc)
VALUES (10,'인사','서울');

INSERT INTO department5 (deptno, dname, loc)
VALUES (20, '인사','경기');

INSERT INTO department5 (deptno, dname, loc)
VALUES (30, NULL,'서울');


SELECT
    *
FROM
    department5;


CREATE TABLE department6(
    deptno NUMBER(2)
        CONSTRAINT department6_deptno_pk PRIMARY KEY,
    dname VARCHAR2(15),
    loc VARCHAR2(15),

    CONSTRAINT department6_dname_uk UNIQUE(dname,loc)
);

DESC department6;

INSERT INTO department6 (deptno, dname, loc)
VALUES (10, '개발', '경기');

INSERT INTO department6 (deptno, dname, loc)
VALUES (20, '인사', '경기');

INSERT INTO department6 (deptno, dname, loc)
VALUES (30, NULL, NULL);

INSERT INTO department6 (deptno, dname, loc)
VALUES (40, NULL, NULL);

INSERT INTO department6 (deptno, dname, loc)
VALUES (50, NULL, 1);

INSERT INTO department6 (deptno, dname, loc)
VALUES (60, NULL, 1);


CREATE TABLE department7(
    deptno NUMBER(2) CONSTRAINT department7_deptno_pk PRIMARY KEY,
    dname VARCHAR2(15) CONSTRAINT department7_dname_uk UNIQUE,
    loc VARCHAR2(15) CONSTRAINT department7_loc_pk NOT NULL
);

DESC department7;


INSERT INTO department7 (deptno, dname, loc)
VALUES (10, '개발', '서울');



DROP TABLE department8;

CREATE TABLE department8 (
    deptno  NUMBER(2) ,
    dname   VARCHAR2(15)
        CONSTRAINT 
            department8_dname_ck 
                CHECK( dname IN('개발','인사') ),
                -- CHECK( dname IS NOT NULL ),
                -- CHECK(
                    -- dname IN('개발','인사')
                    -- OR dname IS NOT NULL
                -- ),
    loc     VARCHAR2(15)
);


-- CHECK 제약조건에 부합
INSERT INTO department8 (deptno, dname, loc)
VALUES (10, '개발', '서울');


INSERT INTO department8 (deptno, dname, loc)
VALUES (20, '인사', '경기');


-- CHECK 제약조건에 위배
-- ORA-02290: check constraint (SCOTT.DEPARTMENT8_DNAME_CK) violated
INSERT INTO department8 (deptno, dname, loc)
VALUES (30, '개발부', '서울');

INSERT INTO department8 (deptno, dname, loc)
VALUES (40, NULL, '서울');

INSERT INTO department8 (deptno, dname, loc)
VALUES (50, NULL, '서울');



SELECT 
    *
FROM
    department8;


CREATE TABLE department9 (
    deptno NUMBER(2),
    dname VARCHAR2(15),
    loc VARCHAR2(15),

    CONSTRAINT department9_dname_ck CHECK( dname IN ('개발', '인사')),
    CONSTRAINT department9_loc_ck CHECK ( loc IN ('서울', '경기'))
);


-- CHECK 제약조건에 부합
INSERT INTO department9 (deptno, dname, loc)
VALUES (10, '개발', '서울');


INSERT INTO department9 (deptno, dname, loc)
VALUES (20, '인사', '경기');


-- CHECK 제약조건에 위배
-- ORA-02290: check constraint (SCOTT.DEPARTMENT9_DNAME_CK) violated
INSERT INTO department9 (deptno, dname, loc)
VALUES (30, '개발부', '서울');


SELECT
    *
FROM
    department9;