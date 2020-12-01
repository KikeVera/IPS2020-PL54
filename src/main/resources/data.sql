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
('0001','103','2020-11-23','2','calle1','2','READY'),
('0002','103','2020-11-23','2','calle1','6','READY'),
('0003','104','2020-11-23','2','calle2','13','READY'),
('0004','104','2020-11-23','3','calle2','3','READY'),
('0005','105','2020-11-23','3','calle3','12','READY'),
('0006','105','2020-11-23','4','calle3','9','READY'),
('1000','105','2020-11-23','4','calle8','11','READY'),
('1001','105','2020-11-23','6','calle2','4','READY'),

('0007','103','2020-11-22','2','calle1','3','READY'),
('0008','103','2020-11-22','2','calle1','34','READY'),
('0009','104','2020-11-22','2','calle4','21','READY'),
('1009','106','2020-11-22','2','calle4','12','READY'),
('1010','106','2020-11-22','2','calle4','21','READY'),
('0010','104','2020-11-22','4','calle2','6','READY'),
('0011','105','2020-11-22','3','calle6','2','READY'),
('0012','105','2020-11-22','6','calle3','9','READY'),

('0013','103','2020-11-21','3','calle1','6','READY'),
('0014','103','2020-11-21','5','calle1','21','READY'),
('0015','104','2020-11-21','6','calle2','12','READY'),
('0016','104','2020-11-21','4','calle3','17','READY'),
('0017','105','2020-11-21','2','calle6','41','READY'),
('0018','105','2020-11-21','6','calle7','11','READY'),

('0019','103','2020-11-20','3','calle1','3','READY'),
('0020','103','2020-11-20','1','calle1','12','READY'),
('0021','104','2020-11-20','5','calle2','13','READY'),
('0022','104','2020-11-20','6','calle2','21','READY'),
('0023','105','2020-11-20','3','calle3','3','READY'),
('0024','105','2020-11-20','6','calle3','2','READY');

insert into OrdenTrabajo values 
(1000,'TERMINADO',1,'2020-11-23','240',15),
(1001,'TERMINADO',2,'2020-11-24','240',15),
(1002,'TERMINADO',3,'2020-11-25','240',15),
(1003,'TERMINADO',4,'2020-11-26','240',15),
(1004,'TERMINADO',5,'2020-11-27','240',15),
(1005,'TERMINADO',1,'2020-11-28','240',15);

                                                                  
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
insert into PerteneceSubcategoria(idProducto,nombreSubcategoria) values 
	(100,'Impresoras'),
	(101,'Bolígrafos'),
	(102,'Audio'),
	(103,'Cableado'),
	(104,'Bolígrafos'),
	(105,'Audio'),
	(100,'Periféricos');
insert into Producto(id,nombre,descripcion,precioNormal,precioEmpresa,pasillo,estanteria,altura,stock,stockMin,stockReposicion,IVA) values 
	(100,'Impresora HP Laserjet','Imprime cualquier documento.',122.22,110.12,0,1,3,4,5,10,12),
	(101,'Pack 6 bolígrafos','Bolígrafos bic de tinta azul a estrenar.',3.2,2.75,1,1,0,4,3,10,10),
	(102,'Micrófono Yeti Nano','Con 3 condensadores.',100.99,99.03,2,2,5,10,3,10,4),
	(103,'Cable USB','3 metros y de color negro.',10.30,5.25,5,4,2,4,2,10,3),
	(104,'Bolígrafo borrable','Con tinta roja que se puede borrar.',1.95,1,1,2,2,13,10,10,7),
	(105,'Auriculares Corsair','Con excelente calida de sonido.',95.22,90.99,1,4,1,14,5,10,21),
    	(106,'Producto G','G',14.34,14,1,2,5,9,7,10,2),
    	(107,'Producto H','H',8.5,8.25,1,2,7,3,2,10,10),
	(108,'Pisapapeles','Circular',2,1.25,2,2,7,4,3,10,2);
insert into PerteneceCategoria(idProducto,nombreCategoria) values 
	(100,'Oficina'),
	(101,'Oficina'),
	(102,'Informatica'),
	(103,'Informatica'),
	(104,'Oficina'),
	(105,'Informatica'),
	(106,'Pruebas'),
	(107,'Pruebas'),
	(108,'Oficina'),
	(100,'Informatica');
insert into Usuario(idUsuario,tipo,direccion) values 
	('usuario','Particular','calle1'),
	('empresa','Empresa','calle 2'),
    	('empresa2','Empresa','calle 3'),
    	('empresa3','Empresa','calle 4');
