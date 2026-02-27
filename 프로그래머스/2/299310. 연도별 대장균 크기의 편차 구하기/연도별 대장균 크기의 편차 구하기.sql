-- 코드를 작성해주세요
SELECT 
    YEAR(e.differentiation_date) AS year,
    (
        SELECT MAX(size_of_colony)
        FROM ecoli_data
        WHERE YEAR(differentiation_date) = YEAR(e.differentiation_date)
    ) - e.size_of_colony AS year_dev,
    e.id
FROM ecoli_data e
ORDER BY year, year_dev;
