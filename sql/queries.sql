USE floristerialabajo;

-- 1. Ver todas las flores disponibles ordenadas por precio ascendente
SELECT id_flor, nombre, precio, cantidad
FROM flores
ORDER BY precio ASC;

-- 2. Ver clientes, cuántas compras ha realizado cada uno y el gasto total acumulado
SELECT c.id_cliente,
       CONCAT(c.nombre, ' ', c.apellidos) AS cliente,
       COUNT(co.id_compra) AS total_compras,
       COALESCE(SUM(co.importe), 0) AS gasto_total
FROM clientes c
LEFT JOIN compras co ON c.id_cliente = co.id_cliente
GROUP BY c.id_cliente, c.nombre, c.apellidos
ORDER BY gasto_total DESC;

-- 3. Buscar flores cuyo nombre coincida con una variedad o color específico (ej. Rosa o Blanco)
SELECT id_flor, nombre, precio, cantidad
FROM flores
WHERE nombre LIKE '%Rosa%' OR nombre LIKE '%blanco%';

-- 4. Ver ingresos totales y número de ventas por mes
SELECT YEAR(fecha) AS anio,
       MONTH(fecha) AS mes,
       COUNT(id_compra) AS num_compras,
       SUM(importe) AS ingresos_totales
FROM compras
GROUP BY YEAR(fecha), MONTH(fecha)
ORDER BY anio DESC, mes DESC;

-- 5. Listar flores con stock bajo o moderado (menos de 50 unidades)
SELECT id_flor, nombre, precio, cantidad
FROM flores
WHERE cantidad < 50
ORDER BY cantidad ASC;

-- 6. TOP 5 flores más vendidas (por unidades totales e ingresos generados)
SELECT f.id_flor,
       f.nombre,
       SUM(fc.cantidad) AS total_unidades_vendidas,
       SUM(fc.cantidad * f.precio) AS facturacion_flor
FROM florescompras fc
JOIN flores f ON fc.id_flor = f.id_flor
GROUP BY f.id_flor, f.nombre
ORDER BY total_unidades_vendidas DESC
LIMIT 5;

-- 7. Ver qué trabajador registró cada compra y su importe
SELECT CONCAT(t.nombre, ' ', t.apellidos) AS trabajador,
       co.id_compra,
       co.fecha,
       co.metodo_pago,
       co.importe
FROM trabajadores t
JOIN compras co ON t.id_trabajador = co.id_trabajador
ORDER BY co.fecha DESC;

-- 8. Resumen de facturación agrupado por método de pago
SELECT metodo_pago,
       COUNT(id_compra) AS num_operaciones,
       SUM(importe) AS total_facturado
FROM compras
GROUP BY metodo_pago
ORDER BY total_facturado DESC;

-- 9. Detectar si hay flores sin stock o con valores inválidos
SELECT id_flor, nombre, cantidad
FROM flores
WHERE cantidad IS NULL OR cantidad <= 0;

-- 10. Ver clientes registrados que aún no han realizado ninguna compra
SELECT c.id_cliente, c.dni, c.nombre, c.apellidos, c.email
FROM clientes c
LEFT JOIN compras co ON c.id_cliente = co.id_cliente
WHERE co.id_compra IS NULL;
