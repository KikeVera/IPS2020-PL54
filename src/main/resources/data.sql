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
insert into Venta values
('2020-11-23','Contrareembolso','Anónimo',null,'2.28'),
('2020-11-23',null,'Empresa','empresa','6.8'),
('2020-11-23','Transferencia','Particular',null,'50.6'),
('2020-11-23','Transferencia','Anónimo',null,'66,6'),
('2020-11-23','Tarjeta','Particular',null,'35.45'),
('2020-11-23','Tarjeta','Anónimo',null,'24,66'),
('2020-11-23',null,'Empresa','empresa2','20.16'),
('2020-11-23','Contrareembolso','Particular',null,'4.6'),

('2020-11-22','Contrareembolso','Anónimo',null,'67.8'),
('2020-11-22',null,'Empresa','empresa','6.9'),
('2020-11-22','Transferencia','Particular',null,'55.8'),
('2020-11-22','Transferencia','Anónimo',null,'134.8'),
('2020-11-22',null,'Empresa','empresa2','67.7'),
('2020-11-22','Tarjeta','Particular',null,'63.57'),
('2020-11-22','Tarjeta','Anónimo',null,'405.4'),
('2020-11-22','Contrareembolso','Particular',null,'2.8'),
('2020-11-22',null,'Empresa','empresa3','135.5'),

('2020-11-21','Contrareembolso','Anónimo',null,'200.4'),
('2020-11-21',null,'Empresa','empresa','66.8'),
('2020-11-21','Transferencia','Particular',null,'3.8'),
('2020-11-21','Transferencia','Anónimo',null,'77.6'),
('2020-11-21',null,'Empresa','empresa2','20.2'),
('2020-11-21','Contrareembolso','Particular',null,'668.9'),

('2020-11-20','Contrareembolso','Anónimo',null,'24.5'),
('2020-11-20',null,'Empresa','empresa','53.3'),
('2020-11-20','Transferencia','Particular',null,'10.3'),
('2020-11-20','Transferencia','Anónimo',null,'4.5'),
('2020-11-20','Tarjeta','Particular',null,'67.7'),
('2020-11-20','Tarjeta','Anónimo',null,'65.5'),
('2020-11-20',null,'Empresa','empresa2','4.2'),
('2020-11-20','Contrareembolso','Particular',null,'80.5'),
('2020-11-20',null,'Empresa','empresa3','255.2');


insert into Paquete values 
('0001','3','2020-11-23','2','calle1'),
('0002','3','2020-11-23','2','calle1'),
('0003','4','2020-11-23','2','calle2'),
('0004','4','2020-11-23','3','calle2'),
('0005','5','2020-11-23','3','calle3'),
('0006','5','2020-11-23','4','calle3'),
('1000','5','2020-11-23','4','calle8'),
('1001','5','2020-11-23','6','calle2'),

('0007','3','2020-11-22','2','calle1'),
('0008','3','2020-11-22','2','calle1'),
('0009','4','2020-11-22','2','calle4'),
('1009','6','2020-11-22','2','calle4'),
('1010','6','2020-11-22','2','calle4'),
('0010','4','2020-11-22','4','calle2'),
('0011','5','2020-11-22','3','calle6'),
('0012','5','2020-11-22','6','calle3'),

('0013','3','2020-11-21','3','calle1'),
('0014','3','2020-11-21','5','calle1'),
('0015','4','2020-11-21','6','calle2'),
('0016','4','2020-11-21','4','calle3'),
('0017','5','2020-11-21','2','calle6'),
('0018','5','2020-11-21','6','calle7'),

('0019','3','2020-11-20','3','calle1'),
('0020','3','2020-11-20','1','calle1'),
('0021','4','2020-11-20','5','calle2'),
('0022','4','2020-11-20','6','calle2'),
('0023','5','2020-11-20','3','calle3'),
('0024','5','2020-11-20','6','calle3');


                                                                  
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
    (107,'Producto H','H',8.5,8.25,1,2,7,'Pruebas',4,1,10),
	(108,'Pisapapeles','Circular',2,1.25,2,2,7,'Oficina',4,1,10);
insert into Usuario(idUsuario,tipo,direccion) values 
	('usuario','Particular','calle1'),
	('empresa','Empresa','calle 2'),
    ('empresa2','Empresa','calle 3'),
    ('empresa3','Empresa','calle 4');


	
