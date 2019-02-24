# Spring Boot / MVC Demo Application

## Notes

### MVC configuration

* As of SpringBoot 2.x, the configuration via implementation of ``WebMvcConfigurer``
  for WebJars and other static resources has been superceded!
  * see <https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/html/boot-features-developing-web-applications.html#boot-features-spring-mvc-pathmatch>
  * The preference is to use ``spring.mvc.*`` properties in application.yml to tune the dispatcher behavior, e.g. w.r.t
  to static resources etc.

### Webjars

* The ``webjars-locator`` dependency can be used to provide version-agnostic resource URLs.