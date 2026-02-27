-- 코드를 작성해주세요
SELECT f.id, n.fish_name, f.length
FROM fish_info f
JOIN fish_name_info n ON n.fish_type = f.fish_type
WHERE (f.fish_type, f.length) IN (
    SELECT fish_type, MAX(length)
    FROM fish_info
    GROUP BY fish_type
)
ORDER BY f.id ASC;






