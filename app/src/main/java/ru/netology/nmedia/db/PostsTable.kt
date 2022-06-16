package ru.netology.nmedia.db
//
//object PostsTable {
//    const val NAME = "posts"
//
//    val DDL = """
//        CREATE TABLE $NAME (
//        ${Column.ID.columnName} INTEGER PRIMARY KEY AUTOINCREMENT,
//        ${Column.AUTHOR.columnName} TEXT NOT NULL,
//        ${Column.CONTENT.columnName} TEXT NOT NULL,
//        ${Column.PUBLISHED.columnName} TEXT NOT NULL,
//        ${Column.LIKES.columnName} INTEGER NOT NULL DEFAULT 0,
//        ${Column.LIKED_BY_ME.columnName} BOOLEAN NOT NULL DEFAULT 0,
//        ${Column.VIEWS.columnName} INTEGER NOT NULL DEFAULT 0,
//        ${Column.REPOSTS.columnName} INTEGER NOT NULL DEFAULT 0,
//        ${Column.AVATAR.columnName} TEXT NOT NULL DEFAULT 0,
//        ${Column.VIDEO_ATTACHMENT_COVER.columnName} INTEGER DEFAULT NULL,
//        ${Column.VIDEO_ATTACHMENT_HEADER.columnName} TEXT DEFAULT NULL,
//        ${Column.URL_VIDEO.columnName} TEXT DEFAULT NULL
//        );
//    """.trimIndent()
//
//
//    val ALL_COLUMNS_NAME = Column.values().map {
//        it.columnName
//    }.toTypedArray()
//
//    enum class Column(val columnName: String) {
//        ID("id"),
//        AUTHOR("author"),
//        CONTENT("content"),
//        PUBLISHED("published"),
//        LIKES("likes"),
//        LIKED_BY_ME("likedByMe"),
//        VIEWS("views"),
//        REPOSTS("reposts"),
//        AVATAR("avatar"),
//        VIDEO_ATTACHMENT_COVER("videoAttachmentCover"),
//        VIDEO_ATTACHMENT_HEADER("videoAttachmentHeader"),
//        URL_VIDEO("urlVideo")
//    }
//}