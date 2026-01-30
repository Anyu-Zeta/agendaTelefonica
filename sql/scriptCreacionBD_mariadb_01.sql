/* Definición de la tabla contacto*/

CREATE TABLE contacto(
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT
	,nombres VARCHAR(255) NOT NULL
	,apellidos VARCHAR(255) NOT NULL
	,email VARCHAR(255) NOT NULL
	,telefono VARCHAR(255) NOT NULL
);