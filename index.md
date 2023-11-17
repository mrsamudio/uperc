## Acerca del proyecto

Este proyecto presenta los aspectos más importantes para el desarrollo de un sistema de información que permita la gestión de parqueaderos en la sede de la universidad del municipio de Chía, pasando por el
análisis de las funcionalidades y el flujo de datos en forma general del sistema, el modelado del sistema y los resultados del desarrollo del mismo con el propósito de contribuir al establecimiento del gobierno digital universitario, los requisitos de ley para el funcionamiento de parqueaderos y facilitar la movilidad de la comunidad universitaria.

### Documentos relacionados

1. [Anexo 1 Formato de encuesta sobre el uso del estacionamiento de la universidad de Cundinamarca en Chía.pdf](src/Anexo%201%20Formato%20de%20encuesta%20sobre%20el%20uso%20del%20estacionamiento%20de%20la%20universidad%20de%20Cundinamarca%20en%20Ch%C3%ADa.pdf)
2. [Anexo 2 RESULTADOS ENCUESTA SOBRE EL USO DEL ESTACIONAMIENTO DE LA UNIVERISIDAD DE CUNDINAMARCA SEDE CHIA FINAL.pdf](src/Anexo%202%20RESULTADOS%20ENCUESTA%20SOBRE%20EL%20USO%20DEL%20ESTACIONAMIENTO%20DE%20LA%20UNIVERISIDAD%20DE%20CUNDINAMARCA%20SEDE%20CHIA%20FINAL.pdf)
3. [ARTÍCULO SISTEMA DE INFORMACIÓN PARA LA GESTIÓN DE PARQUEADEROS DE LA UNIVERSIDAD DE CUNDINAMARCA SEDE CHÍA.pdf](src/ART%C3%8DCULO%20SISTEMA%20DE%20INFORMACI%C3%93N%20PARA%20LA%20GESTI%C3%93N%20DE%20PARQUEADEROS%20DE%20LA%20UNIVERSIDAD%20DE%20CUNDINAMARCA%20SEDE%20CH%C3%8DA.pdf)
4. [MANUAL DE USUARIO.pdf](src/MANUAL%20DE%20USUARIO.pdf)
5. [Modelo fisico de base de datos.pdf](src/Modelo%20fisico%20de%20base%20de%20datos.pdf)
6. [Modelo fisico de base de datos.png](src/Modelo%20fisico%20de%20base%20de%20datos.png)
7. [SISTEMA DE INFORMACIÓN PARA LA GESTIÓN DE PARQUEADEROS DE LA UNIVERSIDAD DE CUNDINAMARCA SEDE CHÍA.pdf](src/SISTEMA%20DE%20INFORMACI%C3%93N%20PARA%20LA%20GESTI%C3%93N%20DE%20PARQUEADEROS%20DE%20LA%20UNIVERSIDAD%20DE%20CUNDINAMARCA%20SEDE%20CH%C3%8DA.pdf)

### Análisis del contexto

![Diagrama de contexto](img/analisis/Diagrama%20de%20an%C3%A1lisis%20de%20nivel%200(de%20contexto).png)

### Fragmentos de código fuente relacionado

``` Java
/**
 * Cambio del color en la barra de navegación dependiendo del perfil de usuario
 * 
 * @param auth
 * @return las clases bootstrap css en string
 */
public String colorNav(Authentication auth) {
  String perfil = auth.getAuthorities().stream().reduce((prev, next) -> next).get().getAuthority();
  String coloresnav = "";
  switch (perfil) {
    case "Usuario general":
      coloresnav = "navbar-dark bg-info ";
      break;
    case "Supervisor":
      coloresnav = "navbar-dark bg-success ";
      break;
    case "Visitante":
      coloresnav = "navbar-light bg-light ";
      break;
    case "Administrador":
      coloresnav = "navbar-dark bg-dark";
      break;
    default:
      coloresnav = "navbar-dark bg-danger ";
      break;
  }
  return coloresnav;
}
