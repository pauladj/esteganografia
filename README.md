<p align="center">
  <a href="" rel="noopener">
 <img width=500px height=150px src="img/logo.jpg" alt="Project logo"></a>
</p>
<h3 align="center">Hide messages inside images</h3>

<div align="center">
    <img src="https://img.shields.io/badge/Java-v8-orange?logo=java" />
    <img src="https://img.shields.io/badge/license-MIT-green" />
</div>

---

<p align="center">Hide messages inside images using this steganography application.</p>



## 🧐 Descripción

El programa realizado esconderá en una imagen .png o .jpg una cadena de texto. Se ha utilizado la técnica "*least significant bit*”.

En el código fuente de la aplicación (carpeta `src`) se puede ver una explicación detallada de lo que se ha ido haciendo.

## 🎈 Modo de uso
Ejecutar el fichero ejecutable `estaganografia.jar`.

Se va a ocultar un texto en la siguiente imagen:

<div align="center"><img src="img/sample.jpg" width="200"/></div>

Se especifican las rutas de las imágenes (izquierda) y la foto resultante que tiene el texto oculto (derecha) sigue siendo igual que la original.

<div align="center"><img src="img/esconder.png" width="500"/><img src="img/sample-text-hidden.jpg" width="200"/></div>

Se especifican la ruta de la imagen que tiene el mensaje oculto (izquierda) y se obtiene de forma correcta el mensaje.

<div align="center"><img src="img/obtener.png" width="550"/></div>

