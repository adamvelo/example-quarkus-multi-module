package org.acme

import jakarta.inject.Singleton

@Singleton
class ModuleService {
    fun hello() : String {
        return "Hello from the module"
    }
}