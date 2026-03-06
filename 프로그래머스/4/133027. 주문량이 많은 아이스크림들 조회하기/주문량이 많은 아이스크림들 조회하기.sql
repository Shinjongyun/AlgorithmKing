-- 코드를 입력하세요
SELECT flavor
FROM (
    SELECT flavor, total_order
    FROM first_half

    UNION ALL

    SELECT flavor, total_order
    FROM july
) t
GROUP BY flavor
ORDER BY SUM(total_order) DESC
LIMIT 3;
      
