-- 코드를 입력하세요
select 
    m.member_name,
    r.review_text,
    date_format(r.review_date, '%Y-%m-%d') as review_date
from rest_review r
join member_profile m on m.member_id = r.member_id
JOIN (
    SELECT member_id
    FROM rest_review
    GROUP BY member_id
    HAVING COUNT(*) = (
        SELECT MAX(cnt)
        FROM (
            SELECT COUNT(*) AS cnt
            FROM rest_review
            GROUP BY member_id
        ) t
    )
) x  ON r.member_id = x.member_id
order by r.review_date asc, r.review_text asc
