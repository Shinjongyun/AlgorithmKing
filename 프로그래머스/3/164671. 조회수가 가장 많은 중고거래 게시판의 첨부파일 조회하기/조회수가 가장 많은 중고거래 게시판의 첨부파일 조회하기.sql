-- 코드를 입력하세요
SELECT concat('/home/grep/src/',b.board_id,'/', f.file_id, f.file_name, f.file_ext) as FILE_PATH
from used_goods_board b 
join used_goods_file f on b.board_id = f.board_id
join (select max(p.views) as max_view from used_goods_board p) m 
on m.max_view = b.views
order by f.file_id desc