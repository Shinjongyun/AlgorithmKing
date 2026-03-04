-- 코드를 입력하세요
select year(s.sales_date) as year, month(s.sales_date) as month, u.gender, count(distinct u.user_id) as users
from user_info u
join online_sale s on u.user_id = s.user_id
WHERE u.gender IS NOT NULL
group by year(s.sales_date), month(s.sales_date), u.gender
order by year(s.sales_date) asc, month(s.sales_date) asc, u.gender asc