package data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//class AccountDbHelper : SQLiteOpenHelper() {
//    override fun onCreate(p0: SQLiteDatabase?) {
//
//    }
//
//    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
//
//    }
//}


//class AccountDbHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
//    val LOG_TAG = AccountDbHelper::class.java.simpleName
//
//    private val DATABASE_NAME = "Accounts.db"
//
//    private val DATABASE_VERSION = 1
//
//    /**
//     * Конструктор {@link HotelDbHelper}.
//     *
//     * @param context Контекст приложения
//     */
//    open fun AccountDbHelper(context: Context?) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION)
//    }
//    /**
//     * Вызывается при создании базы данных
//     */
//    override fun onCreate(db: SQLiteDatabase) {
//        // Строка для создания таблицы
//        val SQL_CREATE_PROFILE_TABLE = ("CREATE TABLE " + AccountsContract.Profile.TABLE_NAME.toString() + " ("
//                + AccountsContract.Profile._ID.toString() + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + AccountsContract.Profile.COLUMN_NAME.toString() + " TEXT NOT NULL, "
//                + AccountsContract.Profile.COLUMN_SURNAME.toString() + " TEXT NOT NULL, "
//                + AccountsContract.Profile.COLUMN_DAD_NAME.toString() + " TEXT NOT NULL, "
//                + AccountsContract.Profile.COLUMN_BIRTHDAY.toString() + " TEXT NOT NULL, "
//                + AccountsContract.Profile.COLUMN_NUMBER.toString() + " TEXT NOT NULL, "
//                + AccountsContract.Profile.COLUMN_EMAIL.toString() + " TEXT NOT NULL, "
//                + AccountsContract.Profile.COLUMN_AVATAR.toString() + " BLOB); ")
//
//        // Запускаем создание таблицы
//        db.execSQL(SQL_CREATE_PROFILE_TABLE)
//    }
//
//    /**
//     * Вызывается при обновлении схемы базы данных
//     */
//    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//
//    }
//
//    companion object {
//
//
//        /**
//         * Имя файла базы данных
//         */
//        private const val DATABASE_NAME = "Accounts.db"
//
//        /**
//         * Версия базы данных. При изменении схемы увеличить на единицу
//         */
//        private const val DATABASE_VERSION = 1
//    }
//}


class AccountDbHelper
/**
 * Конструктор [HotelDbHelper].
 *
 * @param context Контекст приложения
 */
    (context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    /**
     * Вызывается при создании базы данных
     */
    override fun onCreate(db: SQLiteDatabase) {
        // Строка для создания таблицы
        val SQL_CREATE_PROFILE_TABLE = ("CREATE TABLE " + AccountsContract.Profile.TABLE_NAME.toString() + " ("
                + AccountsContract.Profile._ID.toString() + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AccountsContract.Profile.COLUMN_NAME.toString() + " TEXT NOT NULL, "
                + AccountsContract.Profile.COLUMN_SURNAME.toString() + " TEXT NOT NULL, "
                + AccountsContract.Profile.COLUMN_DAD_NAME.toString() + " TEXT NOT NULL, "
                + AccountsContract.Profile.COLUMN_BIRTHDAY.toString() + " TEXT NOT NULL, "
                + AccountsContract.Profile.COLUMN_NUMBER.toString() + " TEXT NOT NULL, "
                + AccountsContract.Profile.COLUMN_EMAIL.toString() + " TEXT NOT NULL, "
                + AccountsContract.Profile.COLUMN_AVATAR.toString() + " BLOB); ")

        // Запускаем создание таблицы
        db.execSQL(SQL_CREATE_PROFILE_TABLE)
    }

    /**
     * Вызывается при обновлении схемы базы данных
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        val LOG_TAG = AccountDbHelper::class.java.simpleName

        /**
         * Имя файла базы данных
         */
        private const val DATABASE_NAME = "accounts.db"

        /**
         * Версия базы данных. При изменении схемы увеличить на единицу
         */
        private const val DATABASE_VERSION = 1
    }
}