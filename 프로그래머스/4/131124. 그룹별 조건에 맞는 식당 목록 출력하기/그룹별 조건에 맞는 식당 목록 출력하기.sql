-- 코드를 입력하세요
SELECT 
    m.member_name,
    r.review_text,
    DATE_FORMAT(r.review_date, '%Y-%m-%d') AS review_date
FROM member_profile m
JOIN rest_review r
    ON m.member_id = r.member_id
join (
    select member_id
    from rest_review
    group by member_id
    having count(*) = (
        select max(cnt)
        FROM (
            SELECT COUNT(*) AS cnt
            FROM rest_review
            GROUP BY member_id
        ) t )
    ) x 
    on x.member_id = m.member_id 
    ORDER BY r.review_date ASC, r.review_text ASC;