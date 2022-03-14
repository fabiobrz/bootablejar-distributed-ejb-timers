# Bootable JAR EJB distributed timers example

Demonstrates configuring distributed (Infinispan based) EJB timers through Bootable JAR.

## Current WildFly version and bits used

This example is based on WildFly 27.0.0.Beta1 snapshot and bits resulting from a local build. 

## Build (and create a custom local maven-repo)

```shell
$ mvn clean package -Dmaven.repo.local=/home/<THE_USER>/local-repo
```

## Run

```shell
$ mvn wildfly-jar:run  -Dmaven.repo.local=/home/<THE_USER>/local-repo
```

## Verify

1. Call the app `Hello World` endpoint
```shell
$ curl http://localhost:8080/hello
Hello from XP bootable jar!
```

2. Call the app `Timer` service `create` REST API
```shell
$ curl http://localhost:8080/timer/create
{"millisecondDelay":20000,"name":"EjbTimerBean"}
```

## Different configurations

This example supports the following EJB timer service configuration

### default
Uses the default configuration, i.e. the file system storage for persistent timers

This is the default way the example is built and run. 

### local Infinispan
Demonstrates the distributed EJB timers feature relying on a local (i.e. WildFly embedded) Infinispan

Use the `-Pwildfly-local-infinispan` option to select the Maven profile that will use the CLI commands needed to 
use the local Infinispan instance.

### remote Infinispan
Demonstrates the distributed EJB timers feature relying on a remote Infinispan 


Use the `-Pwildfly-remote-infinispan` option to select the Maven profile that will use the CLI commands needed to
use the local Infinispan instance.

Of course, you'll need a separate Infinispan instance to be up and running, see https://infinispan.org/get-started/
This example assumes the `admin` and `password` credentials are used when starting Infinispan, and it is configured 
accordingly. You'll have to update [the related script](./scripts/distributed-timers-remote-infinispan.cli) in case you 
need to use different values.
