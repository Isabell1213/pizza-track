# 🍕 Pizza-Track — Sistema de Gestión de Pedidos con Pilas Undo/Redo

## Objetivo

Simular el sistema de gestión de pedidos de una pizzería utilizando **dos pilas manuales** basadas en listas ligadas implementadas desde cero en Java. El sistema permite registrar pedidos, deshacerlos (Undo) y rehacerlos (Redo) sin usar ninguna librería de colecciones de Java.

---

## 🏗️ Arquitectura del Proyecto

```
PizzaTrack/
├── src/
│   ├── Pizza.java          → Modelo de datos (nombre + arreglo fijo de 3 ingredientes)
│   ├── Nodo.java           → Nodo de la lista ligada (puntero al siguiente nodo)
│   ├── PilaManual.java     → Implementación de pila con lista ligada (push, pop, peek, isEmpty)
│   ├── GestionPedidos.java → Controlador: coordina Pila Undo y Pila Redo
│   └── Main.java           → Menú interactivo en consola
└── README.md
```

### Flujo de las dos pilas

```
REGISTRAR  →  push() a Pila Principal
UNDO       →  pop() de Principal  →  push() a Secundaria
REDO       →  pop() de Secundaria →  push() a Principal
```

---

## ▶️ Instrucciones de Ejecución

### Requisitos
- Java JDK 17 o superior (recomendado: [Eclipse Temurin](https://adoptium.net/))
- VS Code con extensión **Extension Pack for Java**

### Pasos

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/TU_USUARIO/pizza-track.git
   cd pizza-track
   ```

2. Compilar desde la carpeta raíz:
   ```bash
   javac -d bin src/*.java
   ```

3. Ejecutar:
   ```bash
   java -cp bin Main
   ```

---

## 📋 Menú del Sistema

```
┌──────────────────────────────────────────┐
│              MENÚ PRINCIPAL              │
├──────────────────────────────────────────┤
│  1. Registrar Pizza (push)               │
│  2. Deshacer último pedido (Undo / pop)  │
│  3. Rehacer último deshecho (Redo)       │
│  4. Ver pedido actual (peek)             │
│  5. Ver estado completo del sistema      │
│  0. Salir                                │
└──────────────────────────────────────────┘
```

---

## 🖥️ Capturas de Pantalla

### Registrar pedido
```
--- REGISTRAR NUEVO PEDIDO ---
Nombre de la pizza: Margarita
Ingrese los 3 ingredientes:
  Ingrediente 1: Tomate
  Ingrediente 2: Mozzarella
  Ingrediente 3: Albahaca

✅ Pedido registrado: 🍕 Pizza: Margarita | Ingredientes: [Tomate, Mozzarella, Albahaca]
```

### Deshacer (Undo)
```
↩️  Pedido deshecho: 🍕 Pizza: Margarita | Ingredientes: [Tomate, Mozzarella, Albahaca]
```

### Rehacer (Redo)
```
↪️  Pedido recuperado: 🍕 Pizza: Margarita | Ingredientes: [Tomate, Mozzarella, Albahaca]
```

### Estado del sistema
```
╔══════════════════════════════════════════╗
║         ESTADO DEL SISTEMA               ║
╠══════════════════════════════════════════╣
║  📋 PEDIDOS ACTIVOS (Pila Undo): 2 item(s)
   1. 🍕 Pizza: Pepperoni | Ingredientes: [Pepperoni, Queso, Orégano]
   2. 🍕 Pizza: Margarita | Ingredientes: [Tomate, Mozzarella, Albahaca]
║  🗑️  PEDIDOS DESHECHOS (Pila Redo): 1 item(s)
   1. 🍕 Pizza: Hawaiana | Ingredientes: [Jamón, Piña, Queso]
╚══════════════════════════════════════════╝
```

---

## 🧠 Lógica de los Punteros (Lista Ligada)

### push()
```
Antes: TOPE --> [B | sig→C] → [C | null]
push(A)
Después: TOPE --> [A | sig→B] → [B | sig→C] → [C | null]
```

### pop()
```
Antes: TOPE --> [A | sig→B] → [B | null]
pop() retorna A
Después: TOPE --> [B | null]
```

---

## 🎥 Video de Sustentación

> **Enlace del video:** [Agregar enlace aquí — YouTube / Drive / GitHub]

El video incluye:
- Presentación del estudiante
- Explicación de `push()` y `pop()` con la lista ligada
- Demo del ciclo: Registro → Deshacer → Rehacer

---

## 👥 Integrantes del Grupo

| Nombre | GitHub |
|--------|--------|
| [Tu nombre] | [@tu_usuario](https://github.com/tu_usuario) |

---

## 📌 Notas Técnicas

- **Restricción cumplida:** No se usa `java.util.Stack` ni ninguna colección de Java.
- Los ingredientes se almacenan en un **arreglo de tamaño fijo (3)** dentro de cada objeto `Pizza`.
- Al registrar un nuevo pedido, la pila Redo se vacía automáticamente (comportamiento estándar de Undo/Redo).
