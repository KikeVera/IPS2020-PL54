--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
drop table Producto;
drop table Pedido;
drop table Trozo;
drop table Incidencia;
drop table OrdenTrabajo;
drop table Estado;
drop table Almacenero;
drop table Paquete;
drop table Usuario;
drop table Venta;
drop table Categoria;
drop table Subcategoria;
drop table PerteneceSubcategoria;
drop table PerteneceCategoria;
drop table Contiene; 
create table Producto (id int primary key not null, nombre varchar(32) not null, descripcion varchar(32), precioNormal decimal(5,2), precioEmpresa decimal(5,2), pasillo int, estanteria int, altura int, stock int, stockMin int, stockReposicion int);
create table Pedido (id int primary key not null, fecha varchar(32) not null, tamaño int,idUsuario varchar(5) not null, productos varchar(8000), FOREIGN KEY (idUsuario) REFERENCES Usuario(codigoUsauario));
create table Trozo (id varchar(32) primary key not null, tamaño int, productos varchar(8000));
create table Incidencia (idPedido int  not null, descripcion varchar(1000),FOREIGN KEY (idPedido) REFERENCES Pedido(id));
create table Almacenero(idalmacenero int primary key not null);
create table OrdenTrabajo (idot int primary key not null, estado varchar(20), idalmacenero int not null, idpedido varchar(1000) not null , capacidad int,  FOREIGN KEY (idalmacenero) REFERENCES Almacenero(idalmacenero), FOREIGN KEY (idpedido) REFERENCES Pedido(id));
create table Estado (idot int  not null, terminado varchar(100),posibleEmpaquetado varchar(100),maps varchar(8000),FOREIGN KEY (idot) REFERENCES OrdenTrabajo(idot));
create table Paquete (idPaquete varchar(5) primary key not null, idPedido int not null, fecha varchar(32) not null,idAlmacenero int not null,direccion varchar(20) not null,uds int not null,estado varchar(32) ,FOREIGN KEY (idPedido) REFERENCES Pedido(id));
create table Usuario (idUsuario varchar(10) primary key not null, tipo varchar(10) not null, direccion varchar(20) not null);
create table Venta (fecha varchar(32) not null,tipoPago varchar(32),tipoUsuario varchar(32),empresa varchar(32) ,importe decimal(11,2));
create table Categoria (nombreCategoria varchar(20) primary key not null); 
create table Subcategoria (nombreSubcategoria varchar(20) primary key not null, nombreCategoria not null, FOREIGN KEY (nombreCategoria) REFERENCES Categoria(nombreCategoria));
create table PerteneceSubcategoria (idProducto int not null, nombreSubcategoria varchar(20) not null, FOREIGN KEY (idProducto) REFERENCES Producto(id), FOREIGN KEY (nombreSubcategoria) REFERENCES Subcategoria(nombreSubcategoria));
create table PerteneceCategoria (idProducto int not null, nombreCategoria varchar(20) not null, FOREIGN KEY (idProducto) REFERENCES Producto(id), FOREIGN KEY (nombreCategoria) REFERENCES Categoria(nombreCategoria));
create table Contiene (nombreSubcategoriaOrigen varchar(20) not null, nombreSubcategoriaContenida varchar(20) not null, FOREIGN KEY (nombreSubcategoriaOrigen) REFERENCES Subcategoria(nombreSubcategoria),FOREIGN KEY (nombreSubcategoriaContenida) REFERENCES Subcategoria(nombreSubcategoria));
