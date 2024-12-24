--1. To change the password of the specified user
-- ALTER USER <사용자명> IDENTIFIED BY <비밀번호>;
ALTER USER hr identified by hr;
ALTER USER hr IDENTIFIED BY oracle;


--2. To lock the specified user
--ALTER USER <사용자명> ACCOUNT LOCK;
ALTER USER hr ACCOUNT LOCK;
ALTER USER hr ACCOUNT UNLOCK;

ALTER USER hr ACCOUNT LOCK IDENTIFIED BY oracle;
ALTER USER hr ACCOUNT UNLOCK IDENTIFIED BY oracle;

ALTER USER hr IDENTIFIED BY oracle ACCOUNT LOCK ;
ALTER USER hr IDENTIFIED BY oracle ACCOUNT UNLOCK;


DESC departments;

DESCRIBE employees;