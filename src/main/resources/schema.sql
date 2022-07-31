
create table customer_order (
  customer_id bigint(20) not null,
  order_id bigint(20) not null,
  order_amount decimal(19,3) not null,
  primary key(customer_id, order_id)
);
