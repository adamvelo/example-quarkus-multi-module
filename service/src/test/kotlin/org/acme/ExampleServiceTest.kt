package org.acme

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import jakarta.inject.Inject
import org.acme.service.ExampleService
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class ExampleServiceTest {

    @Inject
    lateinit var exampleService: ExampleService

    @Test
    fun `test service is good`() {
        val helloFromModule = exampleService.helloFromModule()
        assert(helloFromModule == "Hello from the module")
    }

}