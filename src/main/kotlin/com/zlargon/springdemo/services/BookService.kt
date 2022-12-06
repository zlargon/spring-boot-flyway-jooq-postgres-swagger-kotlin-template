package com.zlargon.springdemo.services

import com.zlargon.springdemo.dao.BookDao
import com.zlargon.springdemo.models.Book
import org.springframework.stereotype.Service

@Service
class BookService(private val bookDao: BookDao) {

  // 1. get books
  fun getBooks(): List<Book> {
    return bookDao.getBooks()
  }

  // 2. add book
  fun addBook(book: Book): Book? {
    return bookDao.addBook(book)
  }
}
