# 🧩 Locks and Keys: Maze Solver

**Locks and Keys** es un proyecto de Inteligencia Artificial centrado en la resolución de problemas de planificación y navegación compleja. El sistema resuelve laberintos donde el camino está condicionado por la gestión de recursos: el agente (o agentes) deben localizar y recoger llaves específicas para abrir las puertas que bloquean el progreso hacia la meta.

## 🚀 Resumen del Proyecto

Este proyecto aborda el desafío desde dos perspectivas distintas, combinando el modelado formal de problemas con la implementación de algoritmos de búsqueda eficiente.

### 1. Planificación con PDDL
Se ha modelado el entorno utilizando **PDDL (Planning Domain Definition Language)**, permitiendo que planificadores automáticos encuentren la secuencia óptima de acciones.

### 2. Implementación en Java & Heurística
El motor de resolución desarrollado en **Java** utiliza algoritmos de búsqueda informada para navegar por el espacio de estados.
* **Algoritmos:** Implementación de búsqueda **A\*, BFS, DFS...** y otros métodos heurísticos.
* **Funciones Heurísticas:** Diseño de funciones personalizadas para estimar el coste restante, teniendo en cuenta no solo la distancia de Manhattan, sino también la necesidad de desviarse para obtener llaves.
* **Gestión de Estados:** Control eficiente de estados que incluyen la posición del agente y el inventario de llaves recolectadas.

* ### 3. Documentación
También se incluye documentación relacionada con la práctica, así como las estadísticas y otro tipo de datos que se pedían en a práctica en si

## 🛠️ Tecnologías Utilizadas

* **Java:** Lógica del motor de búsqueda y estructuras de datos.
* **PDDL:** Modelado del dominio de planificación.

---

Este proyecto fue desarrollado como parte de mi formación en la **EPSEVG - UPC**, explorando cómo la lógica computacional puede resolver problemas de toma de decisiones secuenciales.
