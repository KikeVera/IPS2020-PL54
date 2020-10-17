--Datos para carga inicial de la base de datos
delete from Producto;
delete from Pedido;
delete from Incidencia;
delete from OrdenTrabajo;
delete from Almacenero;
insert into Producto(id,nombre,descripcion,precio,pasillo,estanteria) values 
	(100,'Producto A','A',2.22,0,1),
	(101,'Producto B','B',21.2,1,1),
	(102,'Producto C','C',2,2,2),
	(103,'Producto D','D',1.22,5,4),
	(104,'Producto E','E',10.22,1,2);


