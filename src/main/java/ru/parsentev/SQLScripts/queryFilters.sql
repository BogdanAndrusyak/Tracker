--select * from items as i where i.name = 'Item1';
--select * from getAllItems as i where i.id > 0;
--select * from getAllItems as i where i.id between 1 and 3;
--select * from getAllItems as i where i.id in(1, 3);
--select * from getAllItems as i where i.name like '%Item%';
select i.name, i.description, c.description as comment from items as i inner join comments as c on i.id = c.item_id;
