-- 코드를 작성해주세요
SELECT c.ID
FROM ECOLI_DATA c
JOIN ECOLI_DATA p
    ON c.PARENT_ID = p.ID
JOIN ECOLI_DATA g
    ON p.PARENT_ID = g.ID
where g.parent_id is null
ORDER BY c.ID ASC;



