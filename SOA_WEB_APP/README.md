SOA WEB APP. Тестовое задание.

Endpoints:

[post] http://localhost:8080/api/v1/registration - для регистрации.

[post] http://localhost:8080/api/v1/login - для аутентификации. (больше 10 попыток за  час - бан.)

[post] http://localhost:8080/api/v1/user/animal - для добавления своего животного.

[get] http://localhost:8080/api/v1/user/animal - получить список всех своих животных.

[get] http://localhost:8080/api/v1/user/animal/{id} - получить информацию по любому животному.

[delete] http://localhost:8080/api/v1/user/animal/{id} - удалить свое животное.

[put] http://localhost:8080/api/v1/user/animal - изменить данные своего животного.

Все ошибки возвращаются в ввиде JSON объекта.

Стек: Spring MVC, Spring Security, Spring Boot, Hibernate, PostgreSQL, Lombok, ratelimitj
