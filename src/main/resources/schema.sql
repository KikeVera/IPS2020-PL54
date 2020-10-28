--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
drop table Producto;
drop table Pedido;
drop table Incidencia;
drop table OrdenTrabajo;
drop table Almacenero;
drop table Paquete;
create table Producto (id int primary key not null, nombre varchar(32) not null, descripcion varchar(32), precio decimal(5,2), pasillo int, estanteria int, altura int);
create table Pedido (id int primary key not null, fecha varchar(32) not null, tamaño int,idUsuario varchar(5) not null, productos varchar(8000));
create table Incidencia (idPedido int  not null, descripcion varchar(1000),FOREIGN KEY (idPedido) REFERENCES Pedido(id));
create table Almacenero(idalmacenero int primary key not null);
create table OrdenTrabajo (idot int primary key not null, estado varchar(20), idalmacenero int not null, idpedido int not null, FOREIGN KEY (idalmacenero) REFERENCES Almacenero(idalmacenero), FOREIGN KEY (idpedido) REFERENCES Pedido(id));
create table Paquete (idPaquete varchar(5) primary key not null, idPedido int not null, fecha varchar(32) not null,FOREIGN KEY (idPedido) REFERENCES Pedido(id));

