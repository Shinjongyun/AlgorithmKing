-- 코드를 입력하세요
SELECT 
    YEAR(o.sales_date) AS YEAR,
    MONTH(o.sales_date) AS MONTH,
    COUNT(DISTINCT i.user_id) AS PURCHASED_USERS,
    ROUND(
        COUNT(DISTINCT i.user_id) / (
            SELECT COUNT(*)
            FROM user_info
            WHERE YEAR(joined) = 2021
        ),
        1
    ) AS PURCHASED_RATIO
FROM user_info i
JOIN online_sale o
    ON i.user_id = o.user_id
WHERE YEAR(i.joined) = 2021
GROUP BY YEAR(o.sales_date), MONTH(o.sales_date)
ORDER BY YEAR, MONTH;