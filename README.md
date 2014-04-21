password-kata
=============

Dado el debate que se ha producido en torno al concepto de "domain services" que se utiliza en el desarrollo de Osiris, me he decidido a crear esta pequeña kata de ejemplo, para ilustrar la forma de enfocar el problema que a mi me parece más natural. El objetivo no es otro que iniciar una reflexión, en la que poder aprender de otros enfoques y puntos de vista.

Los objetos de dominio están en el paquete ``passwordkata.domain``. Un usuario puede tener tres diferentes tipos de password (todos ellos implementan la interfaz ``passwordkata.domain.Password``) :

* ``passwordkata.domain.ClearPassword`` : Password en texto claro (tradicional)
* ``passwordkata.domain.Md5SaltedPassword``: Password salteado con una key por usuario, y con MD5 como algortimo de hash
* ``passwordkata.domain.EmptyPassword``: Password que representa el estado 'el usuario no tiene password'. Está creado para no tener que lidiar con los odiosos NULL en la aplicación (NullObjectPattern).

Todos los objetos que implementan Password, implementan un método ``@Override public boolean match(Password otherPassword)``, que contine la lógica de comparación de un password con otro. Este método será el llamado desde la capa de servicios, liberando de complejidad el código de los servicios (los servicios, desde este enfoque, se convierten en **orquestadores** de los objetos de dominio, pero no adquieren más responsabilidad de la debida).

La lógica de comparar passwords, dependerá del tipo concreto de password de un usuario concreto (tradicional, md5, no definido, etc). Para abstraer esta lógica se crea la interfaz ``passwordkata.service.pass.PasswordMatcher``, que implementan las siguientes clases :

* ``passwordkata.service.pass.ClearPasswordMatcher``: Debería ser invocado cuando el password del usuario es ``passwordkata.domain.ClearPassword``
* ``passwordkata.service.pass.Md5SaltedPasswordMatcher``: Debería ser invocado cuando el password del usuario es ``passwordkata.domain.Md5SaltedPassword``

Todos los PasswordMatcher implementan ``@Override public boolean match(Password source, ClearPassword toCompare) throws PasswordMatchException``.

En función del tipo de password que tiene el usuario, un matcher u otro debe ser usado. Esta decisión se toma en la factoría ``passwordkata.service.pass.PasswordMatcherFactory``, que será la que cree el tipo de matcher adecuado.

Finalmente, la factoría se pasa como dependencia al servicio ``passwordkata.service.UserAuthenticator``, y es usada para la implementación del método de servicio que es accesible desde el exterior ``public boolean authenticate(User user, ClearPassword passwordToCompare) throws UserAuthenticationException``.

Se puede comprobar ejemplos de uso en el test unitario del servicio ``passwordkata.service.UserAutenticatorTest`` (dentro de la carpeta test).

De esta forma, el sistema puede crecer sin problemas. Simplemente habría que crear nuevo tipos de password que implementen la interfaz ``passwordkata.domain.Password``, y nuevos tipos de natchers que implementen la interfaz ``passwordkata.service.pass.PasswordMatcher``. Así mismo, los objetos de dominio no son tan delgados, si no que ya contienen lógica adecuada a su ámbito (saben compararse con otros passwords), y los servicios se dedican a orquestar, pero no a tomar tantas decisiones.

