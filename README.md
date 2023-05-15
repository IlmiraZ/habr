# Habr

### Краткий обзор модулей
- **backend-api-app** - приложение spring boot, серверный REST API
  - Это приложение также является сервером авторизации / аутентификации
  - Refresh token 
  - swagger ui
  - unit & integration тесты
- **database** - plugin module
  - объекты базы данных
  - репозитории
  - специификации
  - интеграционные тесты
- **frontend-app** - Angular application, frontend
- **picture-service** - plugin module, просто сервис для манипулирования изображениями
- **picture-service-api-app** - spring boot application, API для получения изображений
  - swagger ui
   
### Общие инструменты
- [Spring Boot](https://spring.io/projects/spring-boot) -  упрощает создание автономных производственных приложений на базе Spring, которые вы можете "просто запустить".
- [Spring Security](https://spring.io/projects/spring-security) - это мощная и легко настраиваемая система аутентификации и контроля доступа
- [JWT](https://jwt.io/) - Веб-токены JSON - это открытый отраслевой стандарт __RFC 7519__ метод безопасного представления требований между двумя сторонами

- [docker](https://www.docker.com/) - создавайте, развертывайте, запускайте, обновляйте контейнеры и управляйте ими
- [jib-maven-plugin](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin) - инструмент контейнеризации для проектов maven
- [nginx](https://nginx.org/) - использование в качестве простого обратного прокси-сервера
- [swagger](https://swagger.io/) - для визуализации ресурсов API и взаимодействия с ними
- [PostgreSQL](https://www.postgresql.org/) - самая совершенная в мире реляционная база данных с открытым исходным кодом
- [liquibase](https://docs.liquibase.com/) - контроль версий
- [Testcontainers for Java](https://www.testcontainers.org/) - это библиотека Java, которая поддерживает тесты JUnit, предоставляя облегченные одноразовые экземпляры общих баз данных
- [Project Lombok](https://projectlombok.org/) - это библиотека java, которая автоматически подключается к вашему редактору и инструментам сборки, делает код компактным 
- [Angular](https://angular.io/) - это платформа для создания мобильных и настольных веб-приложений
- [Font Awesome](https://github.com/FortAwesome/Font-Awesome) - это интернет-библиотека иконок и инструментарий, используемый миллионами дизайнеров, разработчиков и создателей контента
- [ng-bootstrap](https://ng-bootstrap.github.io) - Виджеты Angular, созданные с нуля с использованием Bootstrap 5 CSS с API, разработанными для экосистемы Angular
- [ngx-markdown](https://github.com/jfcere/ngx-markdown) - разбор markdown в HTML и многое другое
- [ngx-pagination](https://github.com/michaelbromley/ngx-pagination) - разбивка на страницы для Angular
- [RxJS](https://rxjs.dev/) - реактивная библиотека расширений для Javascript

### Краткое описание проекта и реализованной функциональности:
Это командный учебный проект.
- Что это такое? 
- Это совместный блог об информационных технологиях, информатике и всем, что связано с Интернетом.
- Мы попробовали использовать гибкую методологию: бэклоги, спринты, пользовательские истории, истории вакансий и т.д.
Главным инструментом для управления всем персоналом был [Кайтен](https://kaiten.ru /)

