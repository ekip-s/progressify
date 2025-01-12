# Progressifi

## Вижу результат — иду к цели!

#### Сайт для отслеживания прогресса обучения. Когда видишь свой прогресс, становится легче двигаться к цели. Наглядный прогресс мотивирует продолжать, укрепляя уверенность и создавая ощущение успеха на каждом этапе пути.

---

### Запуск приложения dev

1. Для запуска приложения нужно локально поднять все сервисы:

---

`docker-compose.yml`

```
services:
  progressify_backend:
    build:
      context: .
      dockerfile: progressify_backend_app/master_data/Dockerfile
    depends_on:
      - progressify_database
      - kafka
      - keycloak
    ports:
      - "8081:8081"
    environment:
      - POSTGRES_DB=${APP_POSTGRES_DB}
      - POSTGRES_USER=${APP_POSTGRES_USER}
      - POSTGRES_PASSWORD=${APP_POSTGRES_PASSWORD}
      - ENV=${ENV}
    networks:
      - database_network
      - kafka-network
      - keycloak_network
    restart: always

  kafka_reader:
    build:
      context: .
      dockerfile: progressify_backend_app/kafka_reader/Dockerfile
    depends_on:
      - progressify_database
      - kafka
    environment:
      - POSTGRES_DB=${APP_POSTGRES_DB}
      - POSTGRES_USER=${APP_POSTGRES_USER}
      - POSTGRES_PASSWORD=${APP_POSTGRES_PASSWORD}
      - ENV=${ENV}
    networks:
      - database_network
      - kafka-network
    restart: always

  progressify_database:
    image: postgres:16.3
    container_name: progressify_database
    environment:
      - POSTGRES_DB=${APP_POSTGRES_DB}
      - POSTGRES_USER=${APP_POSTGRES_USER}
      - POSTGRES_PASSWORD=${APP_POSTGRES_PASSWORD}
    volumes:
      - app_postgres_data:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD", "pg_isready", "-U", "${APP_POSTGRES_USER}", "-d", "progressify_database"]
      interval: 10s
      retries: 5
      start_period: 30s
      timeout: 10s
    networks:
      - database_network
    restart: always

  keycloak-db:
    image: postgres:16.2
    container_name: keycloak_db
    environment:
      - POSTGRES_DB=${POSTGRES_DB}
      - POSTGRES_USER=${POSTGRES_USER}
      - POSTGRES_PASSWORD=${POSTGRES_PASSWORD}
    volumes:
      - kc_postgres_data:/var/lib/postgresql/data
    restart: always

  keycloak:
    image: quay.io/keycloak/keycloak:24.0.2
    container_name: keycloak
    command: ["start-dev", "--import-realm"]
    environment:
      DB_VENDOR: POSTGRES
      DB_ADDR: keycloak-db
      DB_DATABASE: ${DB_DATABASE}
      DB_USER: ${POSTGRES_USER}
      DB_PASSWORD: ${POSTGRES_PASSWORD}
      KEYCLOAK_ADMIN: ${KEYCLOAK_ADMIN}
      KEYCLOAK_ADMIN_PASSWORD: ${KEYCLOAK_ADMIN_PASSWORD}
    volumes:
      - ./realm-config-dev.json:/opt/keycloak/data/import/realm-config.json
    depends_on:
      - keycloak-db
    ports:
      - 8080:8080
    restart: always
    networks:
      - keycloak_network

  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    container_name: zookeeper
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    restart: always
    networks:
      - kafka-network

  kafka:
    image: confluentinc/cp-kafka:latest
    container_name: kafka
    depends_on:
      - zookeeper
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092
    restart: always
    networks:
      - kafka-network

  kafka-ui:
    image: provectuslabs/kafka-ui:latest
    container_name: kafka-ui
    depends_on:
      - kafka
    ports:
      - "8082:8080"
    environment:
      KAFKA_CLUSTERS_0_NAME: local
      KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS: kafka:9092
    restart: always
    networks:
      - kafka-network

volumes:
  app_postgres_data:
  kc_postgres_data:

networks:
  kafka-network:
    driver: bridge
  database_network:
    driver: bridge
  keycloak_network:
    driver: bridge
```

---

`.env`

```
POSTGRES_DB=keycloak_master_db
POSTGRES_USER=keycloak_master_db_user
POSTGRES_PASSWORD=obH409kHH1QCiDx5Vj2t
APP_POSTGRES_DB=progressify_database
APP_POSTGRES_USER=keycloak_master_db_user
APP_POSTGRES_PASSWORD=kUW2P\T:8$P1af.;)!J,
APP_DB_DATABASE=progressify_database
KEYCLOAK_ADMIN=keycloak_master_admin
KEYCLOAK_ADMIN_PASSWORD=k~8–uBMT(c2E+hw2lg}W1em?tp81)3[SJxTT,fL
ENV=dev
```

2. Фронт приложение запускается локально командой: `npm run dev`;
