CREATE DATABASE taxi;
USE taxi;

create table participant
(
  id       int auto_increment
    primary key,
  name     varchar(20)                                     null,
  password varchar(255)                                    null,
  role     enum ('USER', 'ADMIN', 'DRIVER') default 'USER' not null,
  email    varchar(40)                                     not null,
  phone    varchar(12)                                     not null,
  banned   tinyint(1)                       default 0      not null
);

create table car
(
  driverId int                                    null,
  mark     varchar(20)                            null,
  model    varchar(20)                            null,
  carClass enum ('BUDGET', 'COMFORT', 'BUSINESS') not null,
  id       int auto_increment
    primary key,
  constraint car_ibfk_1
    foreign key (driverId) references participant (id)
);

create index driverId
  on car (driverId);

create table carorder
(
  id                     int auto_increment
    primary key,
  userId                 int                                    null,
  coordinates            bigint                                 null,
  destinationPoint       varchar(40)                            null,
  price                  double                                 null,
  orderComment           varchar(256)                           null,
  carClass               enum ('BUDGET', 'COMFORT', 'BUSINESS') not null,
  completed              tinyint(1)                             null,
  destinationCoordinates bigint                                 null,
  driverId               int                                    null,
  started                tinyint(1)                             not null,
  constraint driverId
    foreign key (driverId) references participant (id),
  constraint participantId
    foreign key (userId) references participant (id)
);

create table driver
(
  participantId int        null,
  active        tinyint(1) null,
  busy          tinyint(1) null,
  coordinates   bigint     null,
  pricePerKm    double     null,
  id            int auto_increment
    primary key,
  constraint driver_ibfk_1
    foreign key (participantId) references participant (id)
);

create index participantId
  on driver (participantId);

create table driverorderlist
(
  orderId  int null,
  id       int auto_increment
    primary key,
  driverId int null,
  constraint driverOrderList_carorder_id_fk
    foreign key (orderId) references carorder (id),
  constraint driverOrderList_participant_id_fk
    foreign key (driverId) references participant (id)
);

create table user
(
  participantId int null,
  discount      int not null,
  id            int auto_increment
    primary key,
  constraint user_ibfk_1
    foreign key (participantId) references participant (id)
);

create index participantId
  on user (participantId);

