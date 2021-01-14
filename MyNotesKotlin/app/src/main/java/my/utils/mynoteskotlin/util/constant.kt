package my.utils.mynoteskotlin.util

import my.utils.mynoteskotlin.database.DatabaseRepository
import my.utils.mynoteskotlin.screens.MainActivity

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY:DatabaseRepository
const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
lateinit var EMAIL:String
lateinit var PASSWORD:String