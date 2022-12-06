package com.zlargon.springdemo.dao

import com.zlargon.springdemo.jooq.tables.references.BOOK
import com.zlargon.springdemo.models.Book
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class BookDao(private val jooq: DSLContext) {
  fun getBooks(): List<Book> {
    return jooq.select()
      .from(BOOK)
      .fetchInto(Book::class.java)
  }

  fun addBook(book: Book): Book? {
    return jooq.insertInto(BOOK)
      .set(BOOK.TITLE, book.title)
      .returning()
      .fetchOne()
      ?.into<Book>(Book::class.java)
  }
}
