-- 1. Ver todas las flores, ordenadas por precio
SELECT * FROM flor ORDER BY precio ASC;

-- 2. Ver clientes y cuántas compras ha realizado cada uno
SELECT c.nombre, COUNT(v.id_venta) AS total_compras
FROM cliente c
LEFT JOIN venta v ON c.id_cliente = v.id_cliente
GROUP BY c.id_cliente;

-- 3. Buscar flores de una categoría o color específico
SELECT * FROM flor WHERE categoria = 'Ornamental' OR color = 'Rojo';

-- 4. Ver ingresos totales por mes
SELECT MONTH(fecha_venta) AS mes, SUM(total) AS ingresos
FROM venta
GROUP BY MONTH(fecha_venta);

-- 5. Listar productos con stock bajo (ej. menos de 5 unidades)
SELECT * FROM flor WHERE stock < 5;

-- 6. TOP 5 flores más vendidas
SELECT f.nombre, SUM(dv.cantidad) AS total_vendido
FROM detalle_venta dv
JOIN flor f ON dv.id_flor = f.id_flor
GROUP BY f.id_flor
ORDER BY total_vendido DESC
LIMIT 5;

-- 7. Ver qué trabajador registró qué venta
SELECT t.nombre AS trabajador, v.id_venta, v.total
FROM trabajador t
JOIN venta v ON t.id_trabajador = v.id_trabajador;

-- 8. Ver compras a proveedores por fecha
SELECT p.nombre_proveedor, c.fecha_compra, c.total_compra
FROM compra c
JOIN proveedor p ON c.id_proveedor = p.id_proveedor
ORDER BY c.fecha_compra DESC;

-- 9. Detectar si hay flores sin stock registrado
SELECT * FROM flor WHERE stock IS NULL OR stock < 0;

-- 10. Ver clientes que no han realizado ninguna compra
SELECT c.nombre 
FROM cliente c 
LEFT JOIN venta v ON c.id_cliente = v.id_cliente 
WHERE v.id_venta IS NULL;