Installation Instructions
=========================

Install the following software (Versions are as of time of writing/updating this file):

1. Latest JDK 7 (1.7.0u21) - http://www.oracle.com/technetwork/java/javase/downloads/index.html
1. Latest Maven (3.0.5) - http://maven.apache.org/
1. Latest Eclipse Juno for Java EE Developers (4.2.2 aka 4.2 SR2) - http://www.eclipse.org/downloads/ 
   1. Install Latest Spring STS and Maven (M2E) support if not already there
   1. Install the sample spring-hibernate-cf example application from STS
1. Latest Jenkins WAR file (version changes weekly)- http://www.jenkins-ci.org

Environment Variables
=====================

Set the following environment variables.

PATH separators are Operating System dependant, using semi-colons in this example.

M2_HOME=$MAVEN_INSTALL<br />
JAVA_HOME=$JDK_INSTALL<br />
ECLIPSE_HOME=$ECLIPSE_INSTALL<br />
PATH=$JDK_INSTALL/bin;$M2_HOME/bin,$ECLIPSE_HOME/bin<br />

Confirm installations
=====================

On command line run:

1. java -version (you should get back JDK1.7.0_21 )
1. mvn -version (you should get back Maven 3.0.5)
1. cd $JENKINS_INSTALL
1. java -jar jenkins.war (this should start up Jenkins)

On the desktop run:

1. Eclipse (Eclipse IDE should start)
