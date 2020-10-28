--Datos para carga inicial de la base de datos
delete from Producto;
delete from Pedido;
delete from Incidencia;
delete from OrdenTrabajo;
delete from Almacenero;
delete from Paquete;
insert into Producto(id,nombre,descripcion,precio,pasillo,estanteria,altura) values 
	(100,'Producto A','A',2.22,0,1,3),
	(101,'Producto B','B',21.2,1,1,0),
	(102,'Producto C','C',2,2,2,5),
	(103,'Producto D','D',1.22,5,4,2),
	(104,'Producto E','E',10.22,1,2,2),
	(105,'Producto F','F',5.22,1,4,1),
    (106,'Producto G','G',14.34,1,2,5),
    (107,'Producto H','H',8.5,1,2,7);


