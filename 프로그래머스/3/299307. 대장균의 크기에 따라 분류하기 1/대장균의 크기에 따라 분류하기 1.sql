-- 코드를 작성해주세요
select id, 
    CASE WHEN size_of_colony <= 100 THEN 'LOW'
       	WHEN size_of_colony <= 1000 THEN 'MEDIUM'
       	 else 'HIGH' end size
from ecoli_data
order by id asc 



