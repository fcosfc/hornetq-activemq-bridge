# hornetq-activemq-bridge

This test project explores one possibility of connecting JMS topics/queues deployed over the HornetQ installation of JBoss EAP 6.3 application server and the ActiveMQ system of Apache ServiceMix 6.0.0 ESB, by establishing a bridge between HornetQ and ActiveMQ.

The project servicemix-test-bundle has two routes: the first one publishes messages on a topic every 30 seconds and the second logs the received messages on a queue.

On the other hand, you have some CLI scripts that set up the bridge connections, deploying an ActiveMQ resource adapter on JBoss EAP 6.3 and creating the bridge connections, by publishing on a HornetQ queue the messages read by a durable consumer of the ServiceMix topic and transfering to ActiveMQ the messages written on a HornetQ queue by a test application.

The code of this Java EE 6 test application is in the project jboss-test-webapp, which allows you to see the received messages (generated in ServiceMix) and to send test messages.
