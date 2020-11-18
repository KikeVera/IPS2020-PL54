--Datos para carga inicial de la base de datos
delete from Producto;
delete from Pedido;
delete from Trozo;
delete from Incidencia;
delete from OrdenTrabajo;
delete from Almacenero;
delete from Paquete;
delete from Usuario;
delete from Venta;
delete from Estado;
insert into Categoria(nombreCategoria) values 
	('Informática'),
	('Oficina'),
	('Pruebas');
insert into Subcategoria(nombreSubcategoria,nombreCategoria) values 
	('Periféricos','Informática'),
	('Cableado','Informática'),
	('Impresoras','Oficina'),
	('Bolígrafos','Oficina'),
	('Audio','Informatica');
insert into Contiene(nombreSubcategoriaOrigen,nombreSubcategoriaContenida) values 
	('Periféricos','Audio');
insert into Pertenece(idProducto,nombreSubcategoria) values 
	(100,'Impresoras'),
	(101,'Bolígrafos'),
	(102,'Audio'),
	(103,'Cableado'),
	(104,'Bolígrafos'),
	(105,'Audio'),
	(106,'Pruebas'),
	(107,'Pruebas');
insert into Producto(id,nombre,descripcion,precioNormal,precioEmpresa,pasillo,estanteria,altura,nombreCategoria,stock,stockMin,stockReposicion) values 
	(100,'Impresora HP Laserjet','Imprime cualquier documento a la mejor calidad.',122.22,110.12,0,1,3,'Oficina',4,1,10),
	(101,'Pack de 6 bolígrafos BIC tinta azul','Bolígrafos bic de tinta azul a estrenar.',3.2,2.75,1,1,0,'Oficina',4,1,10),
	(102,'Micrófono Yeti Nano','Con 3 condensadores.',100.99,99.03,2,2,5,'Informatica',4,1,10),
	(103,'Cable USB','3 metros y de color negro.',10.30,5.25,5,4,2,'Informatica',4,1,10),
	(104,'Bolígrafo borrable rojo','Con tinta roja que se puede borrar.',1.95,1,1,2,2,'Oficina',4,1,10),
	(105,'Auriculares Corsair','Con excelente calida de sonido.',95.22,90.99,1,4,1,'Informatica',4,1,10),
    (106,'Producto G','G',14.34,14,1,2,5,'Pruebas',4,1,10),
    (107,'Producto H','H',8.5,8.25,1,2,7,'Pruebas',4,1,10);
insert into Usuario(idUsuario,tipo,direccion) values 
	('usuario','Particular','calle1'),
	('empresa','Empresa','calle 2');

	
