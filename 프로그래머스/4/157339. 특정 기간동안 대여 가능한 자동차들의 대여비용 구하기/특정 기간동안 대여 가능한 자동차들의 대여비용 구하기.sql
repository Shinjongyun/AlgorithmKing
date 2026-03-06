-- 코드를 입력하세요
SELECT 
    c.car_id,
    c.car_type,
    FLOOR(c.daily_fee * 30 * (100 - d.discount_rate) / 100) AS fee
FROM car_rental_company_car c
JOIN car_rental_company_discount_plan d
    ON c.car_type = d.car_type
LEFT JOIN car_rental_company_rental_history h
    ON c.car_id = h.car_id
   AND h.start_date <= '2022-11-30'
   AND h.end_date >= '2022-11-01'
WHERE (c.car_type = '세단' OR c.car_type = 'SUV')
  AND d.duration_type = '30일 이상'
  AND h.car_id IS NULL
  AND FLOOR(c.daily_fee * 30 * (100 - d.discount_rate) / 100) >= 500000
  AND FLOOR(c.daily_fee * 30 * (100 - d.discount_rate) / 100) < 2000000
ORDER BY fee DESC, c.car_type ASC, c.car_id DESC;
    