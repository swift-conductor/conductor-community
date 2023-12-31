# Locks
Conductor uses locks to ensure only one instance of the server is evaluating the workflow state.
Locking is required for workflows with forks where multiple parallel tasks can be in progress and could complete at the same time.

Conductor locks are pluggable - the core conductor uses Redis for locks.

## Published Artifacts

Group: `com.swiftconductor`

| Published Artifact | Description |
| ----------- | ----------- | 
| conductor-zookeeper-lock | Support for Zookpeeper as locks |

### Zookeeper
https://zookeeper.apache.org/

Uses Apache Zookeeper for locking

#### Configuration
(Default values shown below)
```properties
conductor.workflow-execution-lock.type=zookeeper
conductor.zookeeper-lock.connectionString=localhost:2181
conductor.zookeeper-lock.sessionTimeout=
conductor.zookeeper-lock.connectionTimeout=
#The namespace to use within the zookeeper cluster
conductor.zookeeper-lock.namespace=
```
