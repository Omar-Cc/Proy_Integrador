
-- Insertar UBIGEO
CREATE TABLE TempUbigeo (
    codigo VARCHAR(6),
    departamento VARCHAR(50),
    provincia VARCHAR(50),
    distrito VARCHAR(50)
);

-- Cargar datos desde un archivo CSV
LOAD DATA INFILE 'D:/UBIGEO 2022_1891 distritos.csv'
INTO TABLE TempUbigeo
FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

INSERT INTO Ubigeo (codigo, departamento, provincia, distrito)
SELECT codigo, departamento, provincia, distrito
FROM TempUbigeo;

DROP TABLE TempUbigeo;



-- Precargar datos en la tabla Estados
INSERT INTO Estados (nombre, descripcion) VALUES
('Reservado', 'Estado para las citas'),
('Asistió', 'Estado para las citas'),
('Cancelado', 'Estado para las citas'),
('No asistió', 'Estado para las citas'),
('Pendiente', 'Estado para las facturas y resultados de laboratorio'),
('Pagado', 'Estado para las facturas'),
('Vencido', 'Estado para las facturas'),
('En Proceso', 'Estado para resultados de laboratorio y detalles de orden'),
('Completado', 'Estado para resultados de laboratorio y detalles de orden'),
('Revisado', 'Estado para resultados de laboratorio');

-- Precargar datos en la tabla Roles
INSERT INTO Roles (nombre_rol, descripcion) VALUES
('Administrador', 'Rol con todos los permisos'),
('Medico', 'Rol para los médicos'),
('Paciente', 'Rol para los pacientes'),
('Farmacia', 'Persona que atiende la farmacia');

-- Precargar datos en la tabla Estados
INSERT INTO Estados (nombre, descripcion) VALUES
('Reservado', 'Estado para las citas'),
('Asistió', 'Estado para las citas'),
('Cancelado', 'Estado para las citas'),
('No asistió', 'Estado para las citas'),
('Pendiente', 'Estado para las facturas y resultados de laboratorio'),
('Pagado', 'Estado para las facturas'),
('Vencido', 'Estado para las facturas'),
('En Proceso', 'Estado para resultados de laboratorio y detalles de orden'),
('Completado', 'Estado para resultados de laboratorio y detalles de orden'),
('Revisado', 'Estado para resultados de laboratorio');

-- Precargar datos en la tabla Categorias
INSERT INTO Categorias (nombre_categoria, descripcion) VALUES
('Analgesicos', 'Medicamentos para aliviar el dolor'),
('Antibioticos', 'Medicamentos para tratar infecciones'),
('Antisepticos', 'Medicamentos para prevenir infecciones'),
('Antiinflamatorios', 'Medicamentos para reducir la inflamación'),
('Antipireticos', 'Medicamentos para reducir la fiebre');

-- Precargar datos en la tabla Proveedores
INSERT INTO Proveedores (nombre, direccion, telefono, email) VALUES
('Proveedor 1', 'Calle Falsa 123', '123456789', 'proveedor1@example.com'),
('Proveedor 2', 'Avenida Siempre Viva 742', '987654321', 'proveedor2@example.com'),
('Proveedor 3', 'Calle Luna 456', '456789123', 'proveedor3@example.com');

-- Precargar datos en la tabla Medicamentos
INSERT INTO Medicamentos (nombre, descripcion, id_categoria, precio, stock, fecha_vencimiento, id_proveedor) VALUES
('Paracetamol', 'Medicamento para aliviar el dolor y reducir la fiebre', 1, 0.50, 100, '2024-12-31', 1),
('Amoxicilina', 'Antibiótico para tratar infecciones bacterianas', 2, 1.00, 50, '2023-06-30', 1),
('Alcohol', 'Antiséptico para desinfectar heridas', 3, 0.75, 200, '2025-01-01', 1),
('Ibuprofeno', 'Medicamento para reducir la inflamación y aliviar el dolor', 4, 0.60, 150, '2024-11-30', 1),
('Aspirina', 'Medicamento para reducir la fiebre y aliviar el dolor', 5, 0.40, 120, '2024-10-31', 1);

-- Precargar datos en la tabla Metodos_Pago
INSERT INTO Metodos_Pago (nombre) VALUES
('Efectivo'),
('Tarjeta de Crédito'),
('Transferencia Bancaria');