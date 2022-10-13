package data

import android.provider.BaseColumns




class AccountsContract {
    private fun AccountsContract() {}

    object Profile : BaseColumns {
        const val TABLE_NAME = "profile"
        const val _ID = BaseColumns._ID
        const val COLUMN_NAME = "name"
        const val COLUMN_SURNAME = "surname"
        const val COLUMN_DAD_NAME="dad_name"
        const val COLUMN_BIRTHDAY = "birthday"
        const val COLUMN_NUMBER = "phone_number"
        const val COLUMN_EMAIL = "E-mail"
        const val COLUMN_AVATAR = "avatar"
    }
}