-- 코드를 입력하세요
SELECT a.mcdp_cd as 진료과코드, count(*) as 5월예약건수
from appointment a
where a.apnt_ymd between '2022-05-01' and '2022-05-31'
group by a.mcdp_cd
order by count(*) asc, a.mcdp_cd asc