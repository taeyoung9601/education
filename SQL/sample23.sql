SELECT *
FROM mydept;

INSERT INTO mydept
SELECT * FROM dept;

DELETE FROM mydept;

UPDATE mydept
SET
    dname = '영업',
    loc = '경기'
WHERE
    deptno = 40;


UPDATE mydept
SET
    dname = (SELECT dname FROM dept WHERE deptno = 10),
    loc = (SELECT loc FROM dept WHERE deptno = 20)
WHERE
    deptno = (SELECT 40 FROM dual);



----------------------------------------------
DESC mydept;

BEGIN
    
    DELETE FROM mydept
    WHERE deptno = 30;

    -- ROLLBACK;
    COMMIT;

END;


SELECT
    *
FROM
    mydept;


----------------------------------
BEGIN       -- To start a transaction.

    DELETE FROM mydept
    
    -- 2. 다중컬럼 조건식 지정 (Pairwise 방식): IN, = , !=
    WHERE (loc, dname) = (

        SELECT
            loc,
            dname
        FROM
            dept
        WHERE
            deptno = 20

    );

    -- 1. 단일컬럼 조건식 지정 (Un-pairwise 방식)
    -- WHERE loc = (

    --     SELECT
    --         loc
    --     FROM
    --         dept
    --     WHERE
    --         deptno = 20

    -- );


    ROLLBACK;

END;        -- To end a transaction.


SELECT
    *
FROM
    mydept;


------------------------------------------------------
DROP TABLE pt_01;

CREATE TABLE pt_01 (  -- 1월 판매 내역
    판매번호 VARCHAR2(8),
    제품번호 NUMBER,
    수량 NUMBER,
    금액 NUMBER
); --1월


DROP TABLE pt_02;

CREATE TABLE pt_02 (  -- 2월 판매 내역
    판매번호 VARCHAR2(8),
    제품번호 NUMBER,
    수량 NUMBER,
    금액 NUMBER
)

-- "병합된" 모든 판매내역을 저장하는 테이블
CREATE TABLE p_total (
    판매번호 VARCHAR2(8),
    제품번호 NUMBER,
    수량 NUMBER,
    금액 NUMBER
);  -- p_total


DESC pt_01;

DESC pt_02;

DESC p_total;


BEGIN       -- To start a transaction.

    INSERT INTO pt_01 VALUES ( '20170101', 1000, 10, 500 );
    INSERT INTO pt_01 VALUES ( '20170102', 1001, 10, 400 );
    INSERT INTO pt_01 VALUES ( '20170103', 1002, 10, 300 );

    INSERT INTO pt_02 VALUES ( '20170201', 1003,  5, 500 );
    INSERT INTO pt_02 VALUES ( '20170202', 1004,  5, 400 );
    INSERT INTO pt_02 VALUES ( '20170203', 1005,  5, 300 );

    COMMIT;

END;  

-----------------------

MERGE INTO p_total total

    USING pt_01 p01
    ON (total.판매번호 = p01.판매번호)

    WHEN MATCHED THEN

        UPDATE SET
            total.제품번호 = p01.제품번호
            -- total.가격 = p01.가격,
            -- total.수량 = p01.수량
    
    WHEN NOT MATCHED THEN

        INSERT
        VALUES (p01.판매번호, p01.제품번호, p01.수량, p01.금액);

--------------------------------------------------------------

SELECT
    *
FROM
    pt_02;


SELECT
    *
FROM
    p_total;