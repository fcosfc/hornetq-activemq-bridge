# jboss-test-webapp

Java EE 6 test application intended to be deployed on a JBoss EAP 6.3 application server after executing the CLI scripts of de cli/jboss-eap-6.3 folder.

This small application allows you to see the messages received through the bridge and to send test messages to be propagated, also through the bridge.

It uses websockets in order to have push notifications. In order to enable them on JBoss EAP 6.3, you have to execute the following CLI commands on the server (https://access.redhat.com/documentation/en-US/JBoss_Enterprise_Application_Platform/6.3/html/Development_Guide/Create_a_Websocket_Application.html):

  /subsystem=web/connector=http/:write-attribute(name=protocol,value=org.apache.coyote.http11.Http11NioProtocol)
  
  reload
