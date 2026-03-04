-- 코드를 입력하세요
SELECT a.author_id, a.author_name, b.category, sum(bs.sales * b.price) as total_sales
from book b
join author a on a.author_id = b.author_id
join book_sales bs on bs.book_id = b.book_id
where bs.sales_date >= '2022-01-01'
and bs.sales_date < '2022-02-01'
group by a.author_id, b.category
order by a.author_id asc, b.category desc 