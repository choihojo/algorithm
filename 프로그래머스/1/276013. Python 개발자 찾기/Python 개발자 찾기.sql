-- 코드를 작성해주세요
-- Python 스킬을 가진 개발자의 ID, 이메일, 이름, 성
SELECT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM DEVELOPER_INFOS
WHERE SKILL_1 = 'Python'
OR SKILL_2 = 'Python'
OR SKILL_3 = 'Python'
ORDER BY ID ASC