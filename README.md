# Что это?
Проект Rest-API на Spring с базовой авторизацией и ролевой моделью доступа к данным.

## Роли
Сервис поддерживает 8 ролей, для каждой роли создан демонстрационный пользватель:

|         Роль         |                  Права                  | Пользователь |
|:--------------------:|:---------------------------------------:|:------------:|
|      ROLE_ADMIN      | Просмотр и редактирование любых данных  |    admin     |
|      ROLE_USERS      | Просмотр и редактирование пользователей |    bravo     |
|      ROLE_POSTS      |    Просмотр и редактирование постов     |   charlie    |
|     ROLE_ALBUMS      |   Просмотр и редактирование альбомов    |    delta     |
| ROLE_TOTAL_OBSERVER  |          Просмотр любых данных          |     echo     |
| ROLE_USERS_OBSERVER  |         Просмотр пользователей          |   foxtrot    |
| ROLE_ALBUMS_OBSERVER |            Просмотр альбомов            |     golf     |
| ROLE_POSTS_OBSERVER  |             Просмотр постов             |    hotel     |

Для каждого из пользователей пароль совпадает с именем.

## Доступные запросы
|            URI             | Метод  |                 Что получится                 |
|:--------------------------:|:------:|:---------------------------------------------:|
|           `/api`           |  GET   |               Домашняя страница               |
|        `/api/posts`        |  GET   |                   Все посты                   |
|        `/api/posts`        |  POST  |                 Добавить пост                 |
|     `/api/posts/{id}`      |  GET   |              Пост с указанным id              |
|     `/api/posts/{id}`      |  PUT   |         Обновить пост с указанным id          |
|     `/api/posts/{id}`      | DELETE |          Удалить пост с указанным id          |
| `/api/posts/{id}/comments` |  GET   | Список комментариев под постом с указанным id |
| `/api/posts/{id}/comments` |  POST  | Добавить комментарий под пост с указанным id  |
|        `/api/users`        |  GET   |               Все пользователи                |
|        `/api/users`        |  POST  |             Добавить пользователя             |
|     `/api/users/{id}`      |  GET   |          Пользователь с указанным id          |
|     `/api/users/{id}`      |  PUT   |     Обновить пользователя с указанным id      |
|     `/api/users/{id}`      | DELETE |      Удалить пользователя с указанным id      |
|  `/api/posts/{id}/todos`   |  GET   |   Список задач пользователя с указанным id    |
|  `/api/posts/{id}/todos`   |  POST  |  Добавить задачу пользователю с указанным id  |
|    `/users/{id}/posts`     |  GET   |          Список постов пользователя           |
|    `/users/{id}/albums`    |  GET   |         Список альбомов пользователя          |
|       `/api/albums`        |  GET   |                  Все альбомы                  |
|       `/api/albums`        |  POST  |                Добавить альбом                |
|     `/api/albums/{id}`     |  GET   |             Альбом с указанным id             |
|     `/api/albums/{id}`     |  PUT   |        Обновить альбом с указанным id         |
|     `/api/albums/{id}`     | DELETE |         Удалить альбом с указанным id         |
| `/api/albums/{id}/photos`  |  GET   |  Список фотографий в альбоме с указанным id   |
| `/api/albums/{id}/photos`  |  POST  |  Добавить фотографию в альбом с указанным id  |
