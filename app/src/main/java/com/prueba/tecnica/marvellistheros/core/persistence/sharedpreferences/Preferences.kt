package com.prueba.tecnica.marvellistheros.core.persistence.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.reflect.KProperty

const val PREFERENCES_NAME = "com.prueba.tecnica.marvellistheros.preferences"

const val KEY_LIST_MODE = "KEY_LIST_MODE"

class Preferences(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, 0)

    var listMode: Boolean by prefs.persistedBoolean(KEY_LIST_MODE, defaultValue = false)
}

private class PersistedBoolean(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private val defaultValue: Boolean
) {
    operator fun getValue(thisRef: Any, property: KProperty<*>): Boolean =
        sharedPreferences.getBoolean(key, defaultValue)

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        sharedPreferences.edit { putBoolean(key, value) }
    }
}

private fun SharedPreferences.persistedBoolean(
    key: String,
    defaultValue: Boolean
) = PersistedBoolean(this, key, defaultValue)

private inline fun SharedPreferences.edit(operation: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    operation(editor)
    editor.apply()
}

