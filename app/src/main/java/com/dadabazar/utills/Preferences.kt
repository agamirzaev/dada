package com.dadabazar.utills

import android.content.Context
import androidx.fragment.app.Fragment
import com.dadabazar.fragment.OfficeFragment

class Preferences {

    companion object {
        fun loadUserId(context: Context): String? {
            val preferences = context.getSharedPreferences("USER_ID", Context.MODE_PRIVATE)
            return preferences.getString("user_id", "")
        }

        fun saveUserId(id: String, context: Context) {
            val preferences = context.getSharedPreferences("USER_ID", Context.MODE_PRIVATE)
            preferences.edit().putString("user_id", id).apply()
        }

        fun saveGroupId(group_id: String, context: Context) {
            val preferences = context.getSharedPreferences("GROUP_ID", Context.MODE_PRIVATE)
            preferences.edit().putString("group_id", group_id).apply()
        }

        fun loadGroupId(context: Context): String? {
            val preferences = context.getSharedPreferences("GROUP_ID", Context.MODE_PRIVATE)
            return preferences.getString("group_id", "")
        }
    }
}