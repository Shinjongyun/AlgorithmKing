-- 코드를 작성해주세요
SELECT n.ID, j.FISH_NAME, m.LENGTH
FROM FISH_INFO N
JOIN (select fish_type, max(length) as length from fish_info group by fish_type) m 
on m.fish_type = n.fish_type and m.length = n.length
join (select fish_name, fish_type from fish_name_info) j
on j.fish_type = n.fish_type
order by n.id asc