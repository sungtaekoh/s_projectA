create table membership(
id varchar2(20) primary key,
pw varchar2(100),
addr varchar2(300)
);
insert into membership values('aaa','aaa','산골짜기');
insert into membership values('bbb','bbb','별동별');
commit;
alter table membership add 
   session_id varchar2(100) default 'nan' not null;
alter table membership add limit_time date;
