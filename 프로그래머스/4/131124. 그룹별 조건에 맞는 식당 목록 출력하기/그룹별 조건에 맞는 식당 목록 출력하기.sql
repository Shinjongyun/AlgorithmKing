-- 코드를 입력하세요
SELECT 
    m.member_name,
    r.review_text,
    DATE_FORMAT(r.review_date, '%Y-%m-%d') AS review_date
FROM member_profile m
JOIN rest_review r
    ON m.member_id = r.member_id
WHERE r.member_id = (
    SELECT member_id
    FROM rest_review
    GROUP BY member_id
    ORDER BY COUNT(*) DESC
    LIMIT 1
)
ORDER BY r.review_date ASC, r.review_text ASC;