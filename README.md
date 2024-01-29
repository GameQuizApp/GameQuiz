# GameQuiz App 🎮 ![CodeQL](https://github.com/nasa/openmct/workflows/CodeQL/badge.svg)![GitHub Release Date](https://img.shields.io/github/release-date/dropbox/dropbox-sdk-java)

¡Bienvenido al repositorio oficial de GameQuiz! 🚀

## Descripción del Proyecto:

Somos un equipo de tres estudiantes y semi-desarrolladores con afición por los videojuegos y la tecnología. En este perfil, compartimos nuestros avances en el proyecto GameQuiz, un proyecto con el que pretendemos aprender muchas cosas sobre el desarrollo de aplicaciones y sus distintos ámbitos, y sobre cómo trabajar en equipo en un proyecto de desarrollo, además de crear una gran aplicación que ofrezca la mejor experiencia.


## Integrantes del Equipo:

- **[Martín](https://github.com/martinguijarro):** SCRUM Master. 👨‍💻
- **[Alejandro](https://github.com/alexrgez14):** Cloud Master. 👨‍💻
- **[Kevin](https://github.com/Kevbast):** Designer. 👩‍💻

## Contribuciones:

¡Agradecemos cualquier contribución! Si deseas mejorar la aplicación, por favor sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una nueva rama para tu función: `git checkout -b nueva_rama`
3. Realiza tus cambios y haz commit: `git commit -m 'Añadir nueva característica'`
4. Envía tus cambios: `git push origin nueva_caracteristica`
5. Abre un Pull Request.

## Contacto:

Para preguntas, sugerencias o colaboraciones, no dudes en ponerte en contacto con nosotros:

- Correo Electrónico: appgamequiz@gmail.com 📧

¡Gracias por tu interés en nuestro proyecto! Esperamos que disfrutes con GameQuiz.

> [!NOTE]
> Lo que viene a continuación es una breve explicación con imágenes,videos,etc
> Vamos a estar documentando todo el proceso poco a poco

## Herramientas utilizadas

<a href="https://www.java.com" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> 

<a href="https://developer.android.com/studio" target="_blank"> <img src="https://developer.android.com/images/logos/android.svg" alt="androidStudio" width="40" height="40"/> </a> <a href="https://firebase.google.com/?hl=pt" target="_blank"> <img src="https://www.gstatic.com/mobilesdk/160503_mobilesdk/logo/2x/firebase_96dp.png" alt="firebase" width="40" height="40"/> </a>


## Pruebas ⚙️

Primera muestra de nuestro trabajo,este sería el Splash de la aplicación.

## Splash

![splash activity](img/animacion_splash.gif)

### Bottom Navigation

[bottom_navigation_menu.xml](app%2Fsrc%2Fmain%2Fres%2Fmenu%2Fbottom_navigation_menu.xml)


## Dependencias utilizadas
```gradle
dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // SwipeRefresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("com.airbnb.android:lottie:6.1.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    // GIF
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.17")

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))

    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")

    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth-ktx")

    implementation ("com.google.firebase:firebase-firestore:24.10.1") // La versión puede variar
}
```

## Post relacionados 

> [!NOTE]
> Esta es una simple tabla en la cual vamos a _describir los componentes_ que más han *destacado* en nuestro proyecto


| Componentes       | Descripción                                                                                                                                                                                                                                                                                     |
|-------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Cardview          | Es la implementación que nos proporciona Google del elemento visual en forma de tarjetas de información que tanto utiliza en muchas de sus aplicaciones.                                                                                                                                        |
| Bottom Navigation | Las barras de navegación de Android o Bottom Bar es un menú secundario situado encima de la barra de navegación de Android que se utiliza para proporcionar una navegación rápida al usuario a las páginas o secciones más utilizadas de una aplicación, como puedes ver en la imagen de abajo. |
