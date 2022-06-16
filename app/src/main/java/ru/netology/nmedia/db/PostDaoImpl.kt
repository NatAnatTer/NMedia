package ru.netology.nmedia.db
//
//import android.content.ContentValues
//import android.database.sqlite.SQLiteDatabase
//import ru.netology.nmedia.dto.Post
//
//class PostDaoImpl(
//    private val db: SQLiteDatabase
//) : PostDao {
//    override fun getAll() =
//        db.query(
//            PostsTable.NAME,
//            PostsTable.ALL_COLUMNS_NAME,
//            null, null, null, null,
//            "${PostsTable.Column.ID.columnName} DESC"
//        ).use { cursor ->
//            List(cursor.count) {
//                cursor.moveToNext()
//                cursor.toPost()
//            }
//        }
//
//    override fun getById(id: Long) {
//        db.query(
//            PostsTable.NAME,
//            PostsTable.ALL_COLUMNS_NAME,
//            "${PostsTable.Column.ID.columnName} =?",
//            arrayOf(id.toString()), null, null, null
//        ).use { cursor ->
//            List(cursor.count) {
//                cursor.moveToNext()
//                cursor.toPost()
//            }
//        }
//    }
//
//
//    override fun save(post: Post): Post {
//        val values = ContentValues().apply {
//            put(PostsTable.Column.AUTHOR.columnName, post.author)
//            put(PostsTable.Column.CONTENT.columnName, post.content)
//            put(PostsTable.Column.PUBLISHED.columnName, post.published)
//        }
//        val id = if (post.id != 0L) {
//            db.update(
//                PostsTable.NAME,
//                values,
//                "${PostsTable.Column.ID.columnName} =?",
//                arrayOf(post.id.toString())
//            )
//            post.id
//        } else {
//            db.insert(PostsTable.NAME, null, values)
//        }
//        return db.query(
//            PostsTable.NAME,
//            PostsTable.ALL_COLUMNS_NAME,
//            "${PostsTable.Column.ID.columnName} =?",
//            arrayOf(id.toString()),
//            null, null, null
//        ).use { cursor ->
//            cursor.moveToNext()
//            cursor.toPost()
//        }
//    }
//
//
//    override fun likeById(id: Long) {
//
//        val post: List<Post> = db.query(
//            PostsTable.NAME,
//            PostsTable.ALL_COLUMNS_NAME,
//            "${PostsTable.Column.ID.columnName} =?",
//            arrayOf(id.toString()), null, null, null
//        ).use { cursor ->
//            List(cursor.count) {
//                cursor.moveToNext()
//                cursor.toPost()
//            }
//        }
//        val values = ContentValues().apply {
//            put(PostsTable.Column.LIKES.columnName, if(post[0].likedByMe) post[0].likes - 1 else post[0].likes + 1 )
//            put(PostsTable.Column.LIKED_BY_ME.columnName, if(post[0].likedByMe) 0 else 1 )
//        }
//        if (post[0].id != 0L) {
//            db.update(
//                PostsTable.NAME,
//                values,
//                "${PostsTable.Column.ID.columnName} =?",
//                arrayOf(post[0].id.toString())
//            )
//        }
//    }
//
//    override fun repost(id: Long) {
//        val post: List<Post> = db.query(
//            PostsTable.NAME,
//            PostsTable.ALL_COLUMNS_NAME,
//            "${PostsTable.Column.ID.columnName} =?",
//            arrayOf(id.toString()), null, null, null
//        ).use { cursor ->
//            List(cursor.count) {
//                cursor.moveToNext()
//                cursor.toPost()
//            }
//        }
//        val values = ContentValues().apply {
//            put(PostsTable.Column.REPOSTS.columnName, post[0].reposts + 1)
//        }
//        if (post[0].id != 0L) {
//            db.update(
//                PostsTable.NAME,
//                values,
//                "${PostsTable.Column.ID.columnName} =?",
//                arrayOf(post[0].id.toString())
//            )
//        }
//    }
//
//
//        override fun removeById(id: Long) {
//            db.delete(
//                PostsTable.NAME,
//                "${PostsTable.Column.ID.columnName} = ?",
//                arrayOf(id.toString())
//
//            )
//        }
//
//    }
