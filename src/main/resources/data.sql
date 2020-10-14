--Datos para carga inicial de la base de datos
delete from Producto;
delete from Pedido;
delete from Incidencia;
delete from OrdenTrabajo;
delete from Almacenero;
insert into Producto(id,nombre,descripcion,precio) values 
	(100,'Producto A','A',21.22),
	(101,'Producto B','B',21.22),
	(102,'Producto C','C',21.22),
	(103,'Producto D','D',21.22),
	(104,'Producto E','E',21.22);


