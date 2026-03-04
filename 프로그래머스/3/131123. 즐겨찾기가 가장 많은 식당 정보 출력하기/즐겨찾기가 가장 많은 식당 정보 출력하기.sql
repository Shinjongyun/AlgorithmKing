-- 코드를 입력하세요
SELECT r.food_type, r.rest_id, r.rest_name, r.favorites
FROM rest_info r
WHERE r.favorites = (
    SELECT MAX(r2.favorites)
    FROM rest_info r2
    WHERE r2.food_type = r.food_type
)
ORDER BY r.food_type DESC;