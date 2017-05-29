# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table company (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_company primary key (id))
;

create table ticket (
  id                        bigint not null,
  name                      varchar(255),
  category                  varchar(255),
  price                     integer,
  introduced                timestamp,
  discontinued              timestamp,
  company_id                bigint,
  image_url                 varchar(255),
  constraint pk_ticket primary key (id))
;

create sequence company_seq;

create sequence ticket_seq;

alter table ticket add constraint fk_ticket_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
create index ix_ticket_company_1 on ticket (company_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists company;

drop table if exists ticket;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists company_seq;

drop sequence if exists ticket_seq;

