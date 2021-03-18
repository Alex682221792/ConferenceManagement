package com.fissa.conferencemanagement.store

import android.content.Context
import android.content.SharedPreferences
import com.fissa.conferencemanagement.logic.interfaces.toObject
import com.fissa.conferencemanagement.model.TopicVO

class TopicsStore(internal var _context: Context) {
    private var introPreference: SharedPreferences
    private var editor: SharedPreferences.Editor
    private var PREF_FILE_NAME = "com.fissa.conferencemanagement.topics"
    private var TOPICS_LIST_KEY = "topics_list"
    private var SHARED_PREF_PRIVATE_MODE = 0

    init {
        introPreference = _context.getSharedPreferences(PREF_FILE_NAME, SHARED_PREF_PRIVATE_MODE)
        editor = introPreference.edit()
    }

    var topicsQueue: ArrayList<TopicVO>
        get() {
            val listReturn = arrayListOf<TopicVO>()
            val topicSet  = introPreference.getStringSet(TOPICS_LIST_KEY, setOf())
            if (!topicSet.isNullOrEmpty()){
                topicSet.forEach { s ->  listReturn.add(s.toObject())}
            }
            return listReturn
        }
        set(topicsList) {
            val topicSet = topicsList.map { openTravel -> openTravel.toJSON() }.toSet()

            editor.putStringSet(TOPICS_LIST_KEY, topicSet)
            editor.commit()
        }

    fun clearTopicsQueue(){
        this.topicsQueue = arrayListOf()
    }

    fun addTopicToQueue(topic: TopicVO){
        var listClone = this.topicsQueue.clone() as ArrayList<TopicVO>
        listClone.add(topic)
        this.topicsQueue = listClone
    }

    fun removeTopicInQueue(topicId:String){
        val listClone = ArrayList(this.topicsQueue)
        listClone.removeAll { it.id.equals(topicId)  }
        this.topicsQueue = listClone
    }
}