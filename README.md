Crunchify
=========

A command line app to crawl techchrunch.com and export a csv file of subject companies

To install:
<code>mvn install</code>

To run tests:
<code>mvn test</code>

To run (create a <filename>.csv):
<code>mvn exec:java -Dexec.mainClass="org.cvhu.Main" -Dexec.args="<filename>"</code>