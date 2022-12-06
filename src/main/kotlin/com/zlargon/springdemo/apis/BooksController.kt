package com.zlargon.springdemo.apis

import com.zlargon.springdemo.models.Book
import com.zlargon.springdemo.services.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/v1/books"], produces = ["application/json"])
class BooksController(private val bookService: BookService) {

  // GET /api/v1/books
  @GetMapping
  fun getBooks(): List<Book> {
    return bookService.getBooks()
  }

  // POST /api/v1/books
  @PostMapping
  @ResponseBody
  fun addBook(@RequestBody book: Book): Book? {
    return bookService.addBook(book)
  }
}
