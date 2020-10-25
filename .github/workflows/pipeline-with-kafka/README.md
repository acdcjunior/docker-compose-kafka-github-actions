# kafka_broker_jaas.conf

Contains the login information for the brokers.

In the *docker-compose*, it is mapped into the container as volume and is referenced
via env var:

    KAFKA_OPTS: "-Djava.security.auth.login.config=/etc/kafka/secrets/kafka_broker_jaas.conf"

The location, `etc/kafka/secrets`, does not matter.
