CREATE DATABASE proyectolurosa;

USE proyectolurosa;

CREATE TABLE Usuarios (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombreUsuario VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    contrasena VARCHAR(45) NOT NULL,
    EsAdmin BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE Clientes (
    idCliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idUsuario INT NOT NULL,
    nombreUsuario VARCHAR(45) NOT NULL,
    apellido VARCHAR(45) NOT NULL,
    direccion VARCHAR(45) NOT NULL,
    provincia VARCHAR(45) NOT NULL,
    pais VARCHAR(45) NOT NULL,
    poblacion VARCHAR(45) NOT NULL,
    telefono INT NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES Usuarios(id)
);

CREATE TABLE Pedidos (
    idPedido INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idCliente INT NOT NULL,
    cantidad INT NOT NULL,
    estado VARCHAR(45) NOT NULL,
    fecha VARCHAR(45) NOT NULL,
    idProducto int not null,
	
    FOREIGN KEY (idCliente) REFERENCES Clientes(idCliente)
);

CREATE TABLE Administradores (
    idAdmin INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idUsuario INT NOT NULL,
    nombre VARCHAR(45) NOT NULL,
    apellido VARCHAR(45) not null,
    email VARCHAR(45) NOT NULL   
);



CREATE TABLE Productos (
    idProducto INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idAdmin INT NOT NULL,
    descripcion VARCHAR(45) NOT NULL,
    Precio INT NOT NULL,
    Stock INT NOT NULL,
    NombreProducto VARCHAR(45) NOT NULL,
    FOREIGN KEY (idAdmin) REFERENCES Administradores(idAdmin)
);

CREATE TABLE Categorias (
    IdCategoria INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idAdmin INT NOT NULL,
    nombre VARCHAR(45),
    FOREIGN KEY (idAdmin) REFERENCES Administradores(idAdmin)
);

ALTER TABLE `proyectolurosa`.`pedidos`
ADD INDEX `pedido_producto_idx` (`idProducto` ASC);

ALTER TABLE `proyectolurosa`.`pedidos` 
ADD CONSTRAINT `pedido_producto`
  FOREIGN KEY (`idProducto`)
  REFERENCES `proyectolurosa`.`productos` (`idProducto`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

