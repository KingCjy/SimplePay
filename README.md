#### /etc/Hosts

`127.0.0.1 local.simple.com`<br>
`127.0.0.1 local.simple-user.com`<br>
`127.0.0.1 local.simple-memo.com`

###DB

#### simple-memo

```sql
create table User (
	`id` int(11) not null auto_increment,
	`email` varchar(255) not null,
	`name` varchar(20) not null,
	
	primary key (`id`)
) default charset=utf8mb4;


create table Memo (
	`id` int(11) not null auto_increment,
	
	`contents` text not null default '',
	
	`user_id`int(11) not null,
	
	`createdDateTime`datetime not null default current_timestamp,
	`updatedDateTime`datetime not null default current_timestamp on update current_timestamp,
	
	primary key (`id`)
)	default charset =utf8mb4;

```