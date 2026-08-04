-- 코드를 작성해주세요
SELECT
    T.SCORE,
    T.EMP_NO,
    T.EMP_NAME,
    T.POSITION,
    T.EMAIL
FROM (
    SELECT
        SUM(G.SCORE) AS SCORE,
        E.EMP_NO,
        E.EMP_NAME,
        E.POSITION,
        E.EMAIL
    FROM HR_EMPLOYEES E
    JOIN HR_GRADE G
        ON E.EMP_NO = G.EMP_NO
    GROUP BY
        E.EMP_NO,
        E.EMP_NAME,
        E.POSITION,
        E.EMAIL
) T
JOIN (
    SELECT
        MAX(S.TOTAL_SUM) AS MS
    FROM (
        SELECT
            EMP_NO,
            SUM(SCORE) AS TOTAL_SUM
        FROM HR_GRADE
        GROUP BY EMP_NO
    ) S
) M
    ON T.SCORE = M.MS;