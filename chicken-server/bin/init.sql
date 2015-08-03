/* �α��ν� 
 * mysql -u root -p --local-infile
 */
create database chicken default charset utf8;
use chicken;

drop table chicken_tb;

create table chicken_tb(
	row_num int not null primary key,
	bplc_nm text,
	site_whl_addr text,
	rdn_whl_addr text,
	apv_perm_ymd date,			
	trd_state_nm text,
	site_area int,
	site_post_no int,
	wtr_sply_facil_se_nm text,
	man_eip_cnt int,
	yy int,
	mult_usn_upso_yn text, 
	lv_se_nm text,			
	facil_tot_scp text,		
	wm_eip_cnt int,				
	trdp_jubn_se_nm text,		
	snt_cob_nm text,			
	snt_uptae_nm text,		
	tot_ep_num int				
) default charset euckr;


load data local infile "C:\\chicken_list_complete.csv"
into table chicken.chicken_tb
character set euckr
fields terminated by ','
enclosed by '\"'
lines terminated by '\n';

create table coord_tb(
	chicken_row_num int not null,
	lat double,
	lng double,
	primary key(chicken_row_num),
	foreign key(chicken_row_num) references chicken_tb(row_num)
)default charset euckr;