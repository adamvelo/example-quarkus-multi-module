package org.acme.service

import jakarta.inject.Singleton
import org.acme.ModuleService

@Singleton
class ExampleService (val moduleService: ModuleService) {
    fun helloFromModule() : String {
        return moduleService.hello()
    }
}