## Notice

> As of **December 13, 2023**, Netflix has discontinued maintenance of Netflix Conductor OSS on GitHub. This is a fork of the [original](https://github.com/Netflix/conductor-community) project maintained by [Swift Software Group](https://www.swiftsoftwaregroup.com).

## Swift Conductor CE (Community Edition)

This repository hosts all the community contributed modules and extensions for [Swift Conductor Core](https://github.com/swift-conductor/conductor)

![Swift Conductor Logo](https://raw.githubusercontent.com/swift-conductor/conductor/main/docs/logo.svg)

## What is Swift Conductor?

Swift Conductor is a workflow orchestration engine that runs in the cloud. You can find more details about Swift Conductor at the [Swift Conductor Core](https://github.com/swift-conductor/conductor) repository.

## What is _this_ repository?

Conductor is an extensible platform that allows users to bring in their own persistence, queues, integrations eventing systems such as SQS, NATS, AMQP and other integrations.

The Swift Conductor Core project contains implementations tested and supported by Swift Software Group, while the Swift Conductor CE (this) repository contains all the modules contributed by the community.

## Repository Structure and Published Artifacts

Binaries are available from the [Maven Central Repository](https://search.maven.org/search?q=g:com.swiftconductor).

Binaries are published under the group: **com.swiftconductor.conductor**

For the list of artifacts published please see the table below:

| Parent Folder                                                  | Description                                                                                    |
| -------------------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| [event-queue](event-queue/README.md)                           | Support for external eventing systems like AMQP and NATS                                       |
| [external-payload-storage](external-payload-storage/README.md) | Storage for large workflow payloads                                                            |
| [index](index/README.md)                                       | Indexing for searching workflows                                                               |
| [metrics](metrics/README.md)                                   | Support for various metrics integrations including Datadog and Prometheus                      |
| [persistence](persistence/README.md)                           | Persistence for metadata, execution and queue implementation                                   |
| [task](task/README.md)                                         | Various system tasks - Kafka Publish                                                           |
| [lock](lock/README.md)                                         | Workflow execution lock implementation                                                         |
| [workflow-event-listener](workflow-event-listener/README.md)   | Workflow Status Listener and Binary compatibility with previously published conductor-contribs |

## FAQ

#### Why separate repository?

The number of contributions, especially newer implementations of the core contracts in Conductor has increased over the past few years. There is interest in the community to contribute more implementations. To streamline the support and release of the existing community-contributed implementations and future ones, we are creating a new repository dedicated to hosting just contributions.

Conductor users who wish to use a contributed module will have a dedicated place to ask questions directly to fellow members of the community.

Having a separate repository will allow us to scale the contributions and also ensure we are able to review and merge PRs in a timely fashion.

#### How often builds are published?

Similar to core Conductor the builds are published often with each major release. Release numbers are kept in sync with main Conductor releases, which removes the need for a version compatibility matrix.

#### How do I get help?

Please use the Discussions on Conductor repo at https://github.com/swift-conductor/conductor/discussions

#### How do I add new modules here?

1. Start with a proposal by posting on the discussion
2. Send a PR

#### I have a question not listed here.

Please use the Discussions in the Swift Conductor Core repo at https://github.com/swift-conductor/conductor/discussions

#### Does it change how I build Swift Conductor or use the Conductor binaries? (Do I need to pull additional dependency in my builds going forward?)

[Swift Conductor Core](https://github.com/swift-conductor/conductor) **no longer** pulls in all the dependencies from this repository as part of the [server](https://github.com/swift-conductor/conductor/tree/main/server) project build. If you plan to use the modules from this repository, please build the [community-server](./community-server) project in this repository. That pulls in all the dependencies from Maven Central.
