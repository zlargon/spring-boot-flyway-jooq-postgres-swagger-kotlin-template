/*
 * This file is generated by jOOQ.
 */
package com.zlargon.springdemo.jooq.tables

import com.zlargon.springdemo.jooq.SpringDemo
import com.zlargon.springdemo.jooq.keys.BOOK_PKEY
import com.zlargon.springdemo.jooq.tables.records.BookRecord
import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.Name
import org.jooq.Record
import org.jooq.Row2
import org.jooq.Schema
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl

/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class Book(
  alias: Name,
  child: Table<out Record>?,
  path: ForeignKey<out Record, BookRecord>?,
  aliased: Table<BookRecord>?,
  parameters: Array<Field<*>?>?
) : TableImpl<BookRecord>(
  alias,
  SpringDemo.SPRING_DEMO,
  child,
  path,
  aliased,
  parameters,
  DSL.comment(""),
  TableOptions.table()
) {
  companion object {

    /**
     * The reference instance of <code>spring_demo.book</code>
     */
    val BOOK: Book = Book()
  }

  /**
   * The class holding records for this type
   */
  override fun getRecordType(): Class<BookRecord> = BookRecord::class.java

  /**
   * The column <code>spring_demo.book.id</code>.
   */
  val ID: TableField<BookRecord, Int?> = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "")

  /**
   * The column <code>spring_demo.book.title</code>.
   */
  val TITLE: TableField<BookRecord, String?> = createField(DSL.name("title"), SQLDataType.CLOB.nullable(false), this, "")

  private constructor(alias: Name, aliased: Table<BookRecord>?) : this(alias, null, null, aliased, null)
  private constructor(alias: Name, aliased: Table<BookRecord>?, parameters: Array<Field<*>?>?) : this(alias, null, null, aliased, parameters)

  /**
   * Create an aliased <code>spring_demo.book</code> table reference
   */
  constructor(alias: String) : this(DSL.name(alias))

  /**
   * Create an aliased <code>spring_demo.book</code> table reference
   */
  constructor(alias: Name) : this(alias, null)

  /**
   * Create a <code>spring_demo.book</code> table reference
   */
  constructor() : this(DSL.name("book"), null)

  constructor(child: Table<out Record>, key: ForeignKey<out Record, BookRecord>) : this(Internal.createPathAlias(child, key), child, key, BOOK, null)
  override fun getSchema(): Schema? = if (aliased()) null else SpringDemo.SPRING_DEMO
  override fun getIdentity(): Identity<BookRecord, Int?> = super.getIdentity() as Identity<BookRecord, Int?>
  override fun getPrimaryKey(): UniqueKey<BookRecord> = BOOK_PKEY
  override fun `as`(alias: String): Book = Book(DSL.name(alias), this)
  override fun `as`(alias: Name): Book = Book(alias, this)

  /**
   * Rename this table
   */
  override fun rename(name: String): Book = Book(DSL.name(name), null)

  /**
   * Rename this table
   */
  override fun rename(name: Name): Book = Book(name, null)

  // -------------------------------------------------------------------------
  // Row2 type methods
  // -------------------------------------------------------------------------
  override fun fieldsRow(): Row2<Int?, String?> = super.fieldsRow() as Row2<Int?, String?>
}
