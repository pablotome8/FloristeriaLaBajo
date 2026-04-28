USE floristerialabajo;

INSERT INTO trabajadores (dni, nombre, apellidos, telefono, email, fecha_contratacion, fecha_baja) VALUES
('12345678Z', 'María', 'García López', '612345678', 'maria.garcia@floristerialabajo.es', '2022-03-14', NULL),
('87654321X', 'Javier', 'Rodríguez Martín', '623456789', 'javier.rodriguez@floristerialabajo.es', '2021-09-01', NULL),
('23456789D', 'Lucía', 'Fernández Sánchez', '634567890', 'lucia.fernandez@floristerialabajo.es', '2023-01-10', NULL),
('34567890V', 'Carlos', 'González Pérez', '645678901', 'carlos.gonzalez@floristerialabajo.es', '2020-06-22', '2025-12-31'),
('45678901G', 'Ana', 'Martínez Gómez', '656789012', 'ana.martinez@floristerialabajo.es', '2024-02-05', NULL);

INSERT INTO clientes (dni, nombre, apellidos, email) VALUES
('56789012M', 'Antonio', 'Pérez García', 'antonio.perez@gmail.com'),
('67890123Y', 'Carmen', 'López Fernández', 'carmen.lopez@yahoo.es'),
('78901234F', 'David', 'Sánchez Romero', 'david.sromero@hotmail.com'),
('89012345P', 'Laura', 'Gómez Martín', 'laura.gomez@gmail.com'),
('90123456D', 'Sofía', 'Díaz Navarro', 'sofia.diaz@outlook.es'),
('11223344B', 'Manuel', 'Ruiz Hernández', 'manuel.ruiz@gmail.com'),
('22334455N', 'Elena', 'Torres Jiménez', 'elena.torres@yahoo.es'),
('33445566J', 'Pablo', 'Moreno Alonso', 'pablo.moreno@gmail.com'),
('44556677Z', 'Marta', 'Romero Gutiérrez', 'marta.romero@hotmail.com'),
('55667788A', 'José', 'Ortega Castro', 'jose.ortega@outlook.es');

INSERT INTO flores (nombre, precio, cantidad) VALUES
('Rosa roja', 2.50, 200),
('Tulipán blanco', 1.80, 150),
('Lirio rosa', 3.20, 90),
('Girasol', 2.10, 120),
('Orquídea blanca', 12.50, 35),
('Margarita', 1.20, 300),
('Peonía', 4.50, 60),
('Clavel rojo', 1.00, 250),
('Lavanda', 2.80, 80),
('Hortensia azul', 5.20, 40);

INSERT INTO compras (id_trabajador, id_cliente, metodo_pago, fecha, importe) VALUES
(1, 1, 'tarjeta', '2026-02-14', 25.00),
(2, 2, 'efectivo', '2026-02-28', 18.40),
(3, 3, 'transferencia', '2026-03-03', 32.10),
(1, 4, 'tarjeta', '2026-03-08', 14.40),
(5, 5, 'tarjeta', '2026-03-19', 27.50),
(2, 6, 'efectivo', '2026-03-25', 21.00),
(3, 7, 'transferencia', '2026-04-01', 19.60),
(1, 8, 'tarjeta', '2026-04-06', 41.50),
(5, 9, 'efectivo', '2026-04-12', 16.80),
(2, 10, 'tarjeta', '2026-04-18', 29.90);

INSERT INTO florescompras (id_compra, id_flor, cantidad) VALUES
(1, 1, 10),
(2, 2, 4),
(2, 6, 8),
(3, 3, 3),
(3, 4, 6),
(3, 8, 10),
(4, 6, 12),
(5, 5, 1),
(5, 1, 6),
(6, 4, 10),
(7, 9, 7),
(8, 7, 5),
(8, 10, 2),
(8, 2, 5),
(9, 8, 8),
(9, 6, 7),
(10, 3, 4),
(10, 1, 6),
(10, 2, 2);