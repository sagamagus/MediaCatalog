📱 MediaCatalog

Aplicación Android desarrollada en Kotlin + Jetpack Compose que consume la API pública de TVMaze para mostrar un catálogo de series con:
🎬 Carrusel principal 
📂 Sliders horizontales dinámicos por categoría
🔍 Pantalla de detalle completa
🎥 Reproductor de video en pantalla completa 
🔄 Pull to Refresh
🌙 Soporte automático Dark / Light Mode
🧪 Unit Tests básicos
🧠 Clean Architecture
⚠️ Manejo robusto de errores
👨‍💻 Autor
Alfredo Guerrero
Android Developer
México 🇲🇽

🧠 Arquitectura

El proyecto está estructurado siguiendo principios de Clean Architecture, dividido en capas:

data/
    ├── remote
    ├── mapper
    └── repository

domain/
    ├── model
    ├── repository
    ├── usecase
    └── util 

presentation/
    ├── home
    ├── detail
    ├── navigation
    ├── state
    ├── trailer
    ├── ui
    └── util

Principios aplicados:
Separación de responsabilidades
Repository pattern
UseCases independientes
UI desacoplada de navegación
Manejo tipado de errores
Cache en memoria como fallback

✨ Funcionalidades principales
🏠 Home

Carousel Principal
Sliders horizontales:
Populares
Nuevos
Géneros dinámicos
Pull to Refresh
Manejo visual de errores
Fondo adaptado a Dark/Light

📄 Detail

Poster con overlay degradado
Título y rating formateado
Chips dinámicos de géneros
Fechas formateadas para humanos
Summary renderizando HTML (b, i, u, a)
Botón:
Sitio oficial 
Ver Trailer 

🎬 Trailer

Reproductor Media3
Pantalla completa
Landscape forzado
Modo inmersivo 

⚠️ Manejo de errores

Implementado mediante AppResult:
NETWORK
HTTP
SERIALIZATION
UNKNOWN
Incluye:
Fallback a cache si falla red
Mensajes amigables
Botón de reintento
Preparado para Snackbar en refresh

🧪 Testing

Unit tests cubren:
Mapper 
Repository 
UseCases
Lógica de agrupación de secciones
Frameworks utilizados:
JUnit
MockK
kotlinx-coroutines-test

🚀 Instrucciones de uso

Clonar el repositorio: https://github.com/sagamagus/MediaCatalog
Abrir el proyecto en Android Studio
Sincronizar Gradle
Ejecutar la aplicación en:
Emulador Android API 26+
Dispositivo físico

🛠 Guía de compilación
Requisitos
Android Studio Hedgehog o superior
JDK 17
Kotlin 2.x
KSP
Gradle compatible con AGP actual
Compilar desde Android Studio
Sync Project
Build → Rebuild Project
Run

📦 Dependencias principales

Jetpack Compose
Material 3
Navigation Compose
Hilt
Retrofit
Kotlinx Serialization (depende de KSP)
Media3 ExoPlayer
Coil
MockK

📈 Posibles mejoras futuras

Cache persistente con Room
Transiciones compartidas 
Animaciones más avanzadas
Snackbar global para errores no bloqueantes
Tests del ViewModel
Integración CI/CD

📄 API utilizada

TVMaze API pública:
https://www.tvmaze.com/api

🏁 Estado del proyecto

✔ Funcional
✔ Arquitectura limpia
✔ Manejo de errores
✔ UI moderna
✔ Dark/Light Mode
✔ Video en pantalla completa
✔ Unit Tests