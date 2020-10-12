--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
drop table Producto;
drop table Pedido;
drop table Incidencia;
drop table OrdenTrabajo;
drop table Almacenero;
create table Producto (id int primary key not null, nombre varchar(32) not null, descripcion varchar(32), precio decimal(4,2));
create table Pedido (id int primary key not null, fecha varchar(32) not null, tamaño int, productos varchar(8000));
create table Incidencia (idPedido int  not null, descripcion varchar(1000));
create table Almacenero(idAlmacenero int primary key not null);
create table OrdenTrabajo (idOT int primary key not null, estado varchar(20), idAlmacenero int not null, idPedido int not null, FOREIGN KEY (idAlmacenero) REFERENCES Almacenero(idAlmacenero), FOREIGN KEY (idPedido) REFERENCES Pedido(id));

