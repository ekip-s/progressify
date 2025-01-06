# Progressifi

## Вижу результат — иду к цели!

#### Сайт для отслеживания прогресса обучения. Когда видишь свой прогресс, становится легче двигаться к цели. Наглядный прогресс мотивирует продолжать, укрепляя уверенность и создавая ощущение успеха на каждом этапе пути.

---

### Запуск приложения dev

1. Для запуска приложения нужно локально поднять keycloak, конфигурация для этого:

---

`docker-compose.yml`

```
version: '3.8'
services:
  keycloak-db:
    image: postgres:16.2
    container_name: keycloak_db
    ports:
      - "6541:5432"
    environment:
      - POSTGRES_DB=${POSTGRES_DB}
      - POSTGRES_USER=${POSTGRES_USER}
      - POSTGRES_PASSWORD=${POSTGRES_PASSWORD}
    volumes:
      - postgresdata:/var/lib/postgresql/data
    restart: always

  keycloak:
    image: quay.io/keycloak/keycloak:24.0.2
    container_name: keycloak
    command: start-dev
    environment:
      DB_VENDOR: POSTGRES
      DB_ADDR: keycloak-db
      DB_DATABASE: ${DB_DATABASE}
      DB_USER: ${POSTGRES_USER}
      DB_PASSWORD: ${POSTGRES_PASSWORD}
      KEYCLOAK_ADMIN: ${KEYCLOAK_ADMIN}
      KEYCLOAK_ADMIN_PASSWORD: ${KEYCLOAK_ADMIN_PASSWORD}
    depends_on:
      - keycloak-db
    ports:
      - 8080:8080
    restart: always

volumes:
  postgresdata:
```

---

`.env`

```
POSTGRES_DB=keycloak_master_db
POSTGRES_USER=keycloak_master_db_user
POSTGRES_PASSWORD=obH409kHH1QCiDx5Vj2t
DB_DATABASE=keycloak_master_db
KEYCLOAK_ADMIN=keycloak_master_admin
KEYCLOAK_ADMIN_PASSWORD=k~8–uBMT(c2E+hw2lg}W1em?tp81)3[SJxTT,fL
```

2. Фронт приложение запускается локально командой: `npm run dev`;
3. Бекенд стартует стандартно с `main` в корне проекта;
