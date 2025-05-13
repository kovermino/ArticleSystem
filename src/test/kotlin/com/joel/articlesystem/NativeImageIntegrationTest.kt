package com.joel.articlesystem

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.web.client.RestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = ["spring.profiles.active=native-test"])
@ActiveProfiles("test")
class NativeImageIntegrationTest {

    @LocalServerPort
    private var port: Int = 0

    @Test
    fun `should handle reflection based operations in native mode`() {
        // Test reflection-based functionality
        val client = RestTemplate()
        val response = client.getForEntity("http://localhost:$port/api/article/1", String::class.java)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(response.body, "")
    }

}