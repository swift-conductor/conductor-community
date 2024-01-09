# Index

Indexing systems for workflow executions

## Published Artifacts

Group: `com.swiftconductor.conductor`

| Published Artifact        | Description     |
| ------------------------- | --------------- |
| conductor-es7-persistence | Elasticsearch 7 |

## ES7 Persistence

This module provides ES7 persistence when indexing workflows and tasks.

### ES Breaking changes

From ES6 to ES7 there were significant breaking changes which affected ES7-persistence module implementation.

- Mapping type deprecation
- Templates API
- TransportClient deprecation

More information can be found here: https://www.elastic.co/guide/en/elasticsearch/reference/current/breaking-changes-7.0.html
### Build

1. In order to use the ES7, you must change the following files from ES6 to ES7:

https://github.com/swift-conductor/conductor/blob/main/build.gradle
https://github.com/swift-conductor/conductor/blob/main/server/src/main/resources/application.properties

In file:

- /build.gradle

change `ext['elasticsearch.version'] = revElasticSearch6` to `ext['elasticsearch.version'] = revElasticSearch7`

In file:

- /server/src/main/resources/application.properties

change `conductor.elasticsearch.version=6` to `conductor.elasticsearch.version=7`

Also you need to recreate dependencies.lock files with ES7 dependencies. To do that delete all dependencies.lock files and then run:

```sh
./gradlew generateLock updateLock saveLock
```

2. To use the ES7 for all modules include test-harness, you must change also the following files:

https://github.com/swift-conductor/conductor/blob/main/test-harness/build.gradle
https://github.com/swift-conductor/conductor/blob/main/test-harness/src/test/java/com/swiftconductor/conductor/test/integration/AbstractEndToEndTest.java

In file:

- /test-harness/build.gradle

* change module inclusion from `es6-persistence` to `es7-persistence`

In file:

- /test-harness/src/test/java/com/swiftconductor/conductor/test/integration/AbstractEndToEndTest.java

* change `conductor.elasticsearch.version=6` to `conductor.elasticsearch.version=7`
* change `DockerImageName.parse("elasticsearch").withTag("6.8.23")` to `DockerImageName.parse("elasticsearch").withTag("7.17.16")`

### Configuration

(Default values shown below)

This module uses the following configuration options:

```ini
# A comma separated list of schema/host/port of the ES nodes to communicate with.
# Schema can be `http` or `https`. If schema is ignored then `http` transport will be used;
# Since ES deprecated TransportClient, conductor will use only the  REST transport protocol.
conductor.elasticsearch.url=

# The name of the workflow and task index.
conductor.elasticsearch.indexPrefix=conductor

# Worker Queue size used in executor service for async methods in IndexDao.
conductor.elasticsearch.asyncWorkerQueueSize=100

# Maximum thread pool size in executor service for async methods in IndexDao
conductor.elasticsearch.asyncMaxPoolSize=12

# Timeout (in seconds) for the in-memory to be flushed if not explicitly indexed
conductor.elasticsearch.asyncBufferFlushTimeout=10
```

#### BASIC Authentication

If you need to pass username/password to connect to ES, add the following properties to your config file

- `conductor.elasticsearch.username`
- `conductor.elasticsearch.password`

Example

```ini
conductor.elasticsearch.username=someusername
conductor.elasticsearch.password=somepassword
```
