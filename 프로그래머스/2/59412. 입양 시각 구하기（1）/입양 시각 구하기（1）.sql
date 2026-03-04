-- 코드를 입력하세요
SELECT hour(datetime) as hour, count(*) as count
from animal_outs
WHERE HOUR(DATETIME) BETWEEN 9 AND 19
group by hour(datetime)
order by hour asc