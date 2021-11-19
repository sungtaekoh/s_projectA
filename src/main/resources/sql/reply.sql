create table reply(
id varchar(20),
title varchar(50),
content varchar(300),
write_group number(10),
write_date date default sysdate,
constraint fk_test1 foreign key(write_group) references mvc_board(write_no) on delete cascade,
constraint fk_test2 foreign key(id) references membership(id) on delete cascade
);
