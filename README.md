# Multi-module project to demonstrate a bug in Quarkus

This project was bootstrapped with the following commands:

    mkdir example
    cd example
    gradle init --dsl kotlin
    quarkus create app org.acme:rest-module --extension='kotlin,rest-jackson' --gradle-kotlin-dsl
    quarkus create app org.acme:service --extension='kotlin' --gradle-kotlin-dsl

Then the 2 quarkus modules were massaged into a single project and the service adds the rest-module as a dependency.

# Description of the bug

The rest-module is built and includes the jandex metadata file in the jar. 
The service includes the rest-module as a dependency and therefore we are
expecting the ModuleService from the rest-module to be loaded by Quarkus into
the CDI context.

But when the service starts up we get the following exception:

    Caused by: jakarta.enterprise.inject.UnsatisfiedResolutionException: Unsatisfied dependency for type org.acme.ModuleService and qualifiers [@Default]
	- injection target: parameter 'moduleService' of org.acme.service.ExampleService constructor
	- declared on CLASS bean [types=[org.acme.service.ExampleService, java.lang.Object], qualifiers=[@Default, @Any], target=org.acme.service.ExampleService]
	at io.quarkus.arc.processor.Beans.resolveInjectionPoint(Beans.java:519)
	at io.quarkus.arc.processor.BeanInfo.init(BeanInfo.java:638)
	at io.quarkus.arc.processor.BeanDeployment.init(BeanDeployment.java:308)
	... 11 more

Am I missing something here? Or should this work as is?

This is the ModuleService class in the rest-module:

    package org.acme
    import jakarta.inject.Singleton
    @Singleton
    class ModuleService {
        fun hello() : String {
            return "Hello from the module"
        }
    }

And this is the ExampleService class in the service module:

    package org.acme.service

    import jakarta.inject.Singleton
    import org.acme.ModuleService
    
    @Singleton
    class ExampleService (val moduleService: ModuleService) {
        fun helloFromModule() : String {
            return moduleService.hello()
        }
    }