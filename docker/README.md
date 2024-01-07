# Conductor Docker Builds

## Pre-built docker images

Conductor server with support for the following backend:

1. Redis Db + Redis Queue
2. Postgres Db + Redis Queue
3. Mysql Db + Redis Queue
4. Cassandra

### Docker File for Server and UI

[Docker Image Source for Server with UI](server/Dockerfile)

### Configuration Guide for Conductor Server

Conductor uses a persistent store for managing state. The choice of backend is quite flexible and can be configured at runtime using `conductor.db.type` property.

Refer to the table below for various supported backend and required configurations to enable each of them.

> [!IMPORTANT]
>
> See [config.properties](docker/server/config/config.properties) for the required properties for each of the backends.
>
> | Backend   | Property                           |
> | --------- | ---------------------------------- |
> | postgres  | conductor.db.type=postgres         |
> | redis     | conductor.db.type=redis_standalone |
> | mysql     | conductor.db.type=mysql            |
> | cassandra | conductor.db.type=cassandra        |

Conductor using Elasticsearch for indexing the workflow data. Currently, Elasticsearch 6 and 7 are supported.

**Note:** Docker images use Elasticsearch 7.

We welcome community contributions for other indexing backends.

# Build

```sh
docker-compose --file docker-compose-redis.yaml build
```

## Run

### Use `docker-compose` to bring up the local server:

| Docker Compose                                               | Description                                                                      |
| ------------------------------------------------------------ | -------------------------------------------------------------------------------- |
| [docker-compose-redis.yaml](docker-compose-redis.yaml)       | Redis + Elasticsearch 7, Redis database, Redis queue, ElasticSearch index        |
| [docker-compose-postgres.yaml](docker-compose-postgres.yaml) | Postgres + Elasticsearch 7, Postgress database, Redis queue, ElasticSearch index |
| [docker-compose-mysql.yaml](docker-compose-mysql.yaml)       | Mysql + Elasticsearch 7, MySql database, Redis queue, ElasticSearch index        |

For example this will start the server instance backed by a Redis persistence, Redis DB queue, ElasticSearch index.

```sh
docker-compose --file docker-compose-redis.yaml up --detach
```

You can open the Server and UI URLs in your browser to verify that they are running correctly:

- Server: http://localhost:8080
- UI: http://localhost:5000

## Monitoring with Prometheus

Start Prometheus only with:

```sh
docker-compose --file docker-compose-prometheus.yaml up --detach
```

Open Prometheus (http://localhost:9090) in your browser:

(Optionally) Start Prometheus and Grafana with:

```sh
docker-compose --file docker-compose-prometheus-grafana.yaml up --detach
```

Open Prometheus (http://localhost:9090) and Grafana (http://localhost:3000) in your browser, use (admin/admin to login to Grafana).
