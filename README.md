Proyecto 1 de Computo Evolutivo

Agente Viajero

---------------------------------------------------------------------------------------------------------------------------------------

Integrantes:
	Jiménez Méndez Ricardo
	Soto Martínez Luis

---------------------------------------------------------------------------------------------------------------------------------------

Con el uso de un algoritmo genético simple se trata de encontrar la mejor solución posible.

El algoritmo codifica el problema tomando una ruta con las ciudades representadas como enteros, cada una de ellas se convierte a binario.
La función de evaluación toma la distancia de la ruta, toma un valor fijo 100000 y se le resta la distancia, el resultado es el fitness del individuo evaluado.
Se usa la selección de ruleta, cruzamiento uniforme ordenado y mutación uniforme con una pequeña modificación.
Se usó ese cruzamiento y mutación para generar en todo momento individuos válidos.
El algoritmo es elitista, siempre se mantiene al mejor individuo.

---------------------------------------------------------------------------------------------------------------------------------------

Para modificar los parámetros a evaluar se tienen que cambiar directamente en Main.java