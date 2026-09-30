-- 코드를 작성해주세요

select YEAR(c.DIFFERENTIATION_DATE) AS YEAR,
    M.maxSIZE - c.SIZE_OF_COLONY AS YEAR_DEV,
    c.ID
from ecoli_data c 
join (select year(differentiation_date) as year, max(size_of_colony) as maxSize from ecoli_data
                 group by year) m 
      ON m.year = YEAR(c.differentiation_date)
ORDER BY
    YEAR ASC,
    YEAR_DEV ASC;