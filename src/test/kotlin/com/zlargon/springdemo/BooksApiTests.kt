package com.zlargon.springdemo

import org.assertj.core.api.Assertions
import org.hamcrest.Matchers
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@ExtendWith(PostgreSQLExtension::class)
@DirtiesContext
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation::class) // Enable @Order Annotation
class BooksApiTests @Autowired constructor(private val mockMvc: MockMvc) {

  @Test
  @Order(1)
  @DisplayName("Expect to get empty book list first time")
  @Throws(Exception::class)
  fun getBook() {
    mockMvc
      .perform(
        MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/books") //
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(MockMvcResultMatchers.status().isOk) // 200
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize<Any>(0)))
  }

  @Test
  @Order(2)
  @DisplayName("Expect to create a book, and get book list with new book")
  @Throws(Exception::class)
  fun createBook() {
    mockMvc
      .perform(
        MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/books")
          .contentType(MediaType.APPLICATION_JSON)
          .content(json("{`title`:`my book`}"))
      )
      .andExpect(MockMvcResultMatchers.status().isOk) // 200
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("my book")))

    // expect to see new book from the list
    val jsonResponse =
      mockMvc
        .perform(
          MockMvcRequestBuilders.request(HttpMethod.GET, "/api/v1/books") //
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isOk) // 200
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize<Any>(1)))
        .andReturn()
        .response
        .contentAsString
    Assertions.assertThat(jsonResponse).isEqualTo(json("[{`id`:1,`title`:`my book`}]"))
  }

  private fun json(str: String): String {
    return str.replace('`', '"')
  }
}
