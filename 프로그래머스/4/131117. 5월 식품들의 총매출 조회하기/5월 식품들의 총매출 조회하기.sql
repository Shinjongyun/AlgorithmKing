-- 코드를 입력하세요
SELECT p.product_id, p.product_name, sum(amount)*p.price as total_sales
from food_product p
join food_order f on p.product_id = f.product_id
where month(f.produce_date) = 5
group by p.product_id 
order by total_sales desc, p.product_id asc