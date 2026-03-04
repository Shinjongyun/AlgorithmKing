-- 코드를 작성해주세요
SELECT SUM(g.SCORE) AS SCORE,
       e.EMP_NO,
       e.EMP_NAME,
       e.POSITION,
       e.EMAIL
FROM HR_EMPLOYEES e
JOIN HR_GRADE g ON g.EMP_NO = e.EMP_NO
WHERE g.YEAR = 2022
GROUP BY e.EMP_NO, e.EMP_NAME, e.POSITION, e.EMAIL
HAVING SUM(g.SCORE) = (
    SELECT MAX(total_score)
    FROM (
        SELECT SUM(SCORE) AS total_score
        FROM HR_GRADE
        WHERE YEAR = 2022
        GROUP BY EMP_NO
    ) t
);
