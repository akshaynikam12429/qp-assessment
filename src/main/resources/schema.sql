create table OrderDetails(
    OrderId bigint not null,
    TotalAmount int not null,
    Items varchar(MAX) not null,
    primary key (OrderId)
);

create table Grocery(
  GroceryId bigint not null,
  Name varchar(255) not null,
  Quantity int not null,
  Price int not null,
  primary key (GroceryId)
);
