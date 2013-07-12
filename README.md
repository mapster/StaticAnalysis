StaticAnalysis
==============

As a service for JavaEvaluator
------------------------------
1. Run Main.java
2. Service must be available at the same host and port as JavaEvaluator
3. Apache httpd offers ProxyPass:
   ProxyPass        /tojson http://localhost:9998/tojson
   ProxyPassReverse /tojson http://localhost:9998/tojson



Libraries 
---------
* asm-3.3.1.jar  	      
* grizzly-http-2.2.16.jar	      
* gson-2.2.2.jar	      
* gson-2.2.2-sources.jar  
* jersey-grizzly2-1.16.jar	
* jsr311-api-1.1.1.jar
* grizzly-framework-2.2.16.jar  
* grizzly-http-server-2.2.16.jar  
* gson-2.2.2-javadoc.jar  
* jersey-core-1.16.jar    
* jersey-server-1.16.jar	
* tools.jar
