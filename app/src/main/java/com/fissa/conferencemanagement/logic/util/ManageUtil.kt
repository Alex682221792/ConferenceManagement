package com.fissa.conferencemanagement.logic.util

import androidx.core.text.isDigitsOnly
import com.fissa.conferencemanagement.logic.enums.DurationsEnum
import com.fissa.conferencemanagement.model.TopicVO
import java.text.SimpleDateFormat
import java.util.*

class ManageUtil {
    private val NETWORKING_EVENT = "Networking"
    private val LUNCH_EVENT = "Lunch"

    fun validateTopic(topic: String): Boolean{
        val result = this.splitStringTopic(topic) ?: return false
        return result.let {
            this.validateName(it!![0]) && this.validateDuration(it[1])
        }
    }

    fun generateTopicVo(topic: String): TopicVO{
        return this.splitStringTopic(topic).let {
            TopicVO(id = Date().time.toString(), name = it!![0],
                minsDuration = castDurationToInt(it[1]), startTime = "", dismissible = true)
        }
    }

    private fun castDurationToInt(duration: String): Int{
        val cleanDuration = duration.toUpperCase().replace(DurationsEnum.LIGHTNING.value, DurationsEnum.LIGHTNING_MINUTES.value)
        return cleanDuration.replace(Regex( "[^0-9]"), "").toInt()
    }

    private fun splitStringTopic(topic: String): Array<String>?{
        val lastIndex = topic.lastIndexOf(" ")
        if (lastIndex < 0){
            return null
        }
        return arrayOf(topic.substring(0, lastIndex), topic.substring(lastIndex + 1))
    }

    private fun validateName(name: String): Boolean{
        return !name.matches(Regex(".*\\d+.*"))
    }

    private fun validateDuration(duration: String): Boolean{
        val upperDuration = duration.toUpperCase()
        if(upperDuration.equals(DurationsEnum.LIGHTNING.value)){
            return true
        }
        if(upperDuration.contains(DurationsEnum.MIN.value)){
            return upperDuration.replace(Regex("[^0-9]"), "")
                .matches(Regex("[0-9]+"))
        }
        return false
    }

    fun generateTracks(topics: Array<TopicVO>): Array<TopicVO>{
        var index = 0
        var currentSectionLength = 0
        var sectionLimit = 180
        var resultTracks: ArrayList<TopicVO> = arrayListOf()
        topics.sortBy { topicVO -> topicVO.minsDuration }
        topics.reverse()
        var clonedTopics = topics.toList()
        var isFactible = true
        var auxCounter = 0
        while (clonedTopics.isNotEmpty() && isFactible) {
            val nextSectionLength = clonedTopics[index].minsDuration + currentSectionLength
            if ( nextSectionLength <= sectionLimit){
                resultTracks.add(clonedTopics[index])
                currentSectionLength = nextSectionLength
                clonedTopics = clonedTopics.minus(clonedTopics[index])
                index = 0
                if (clonedTopics.isEmpty()){
                    resultTracks.add(if (sectionLimit == 180) {getLunchEvent()} else {getNetworkingEvent("")})
                }
            } else {
                if (clonedTopics.isNotEmpty() && currentSectionLength != 180 && currentSectionLength != 240){
                    val itemToMove = if(auxCounter >= topics.size) resultTracks.first() else resultTracks.last()
                    clonedTopics = clonedTopics.plus(itemToMove)
                    resultTracks.remove(itemToMove)
                    index = 0
                    currentSectionLength -= itemToMove.minsDuration
                    auxCounter++
                } else if (index >= (clonedTopics.size - 1)){
                    resultTracks.add(if (sectionLimit == 180) {getLunchEvent()} else {getNetworkingEvent("")})
                    sectionLimit = if(sectionLimit == 180) {240} else {180}
                    index = 0
                    currentSectionLength = 0
                    auxCounter = 0
                } else {
                    index++
                }
            }
            isFactible = resultTracks.isNotEmpty()
        }
        resultTracks.forEachIndexed { index, topicVO ->
            if (index == 0){
                topicVO.startTime = DurationsEnum.START_FIRST_SESSION.value
            } else {
                val prevEvent = resultTracks[index - 1]
                if (prevEvent.name == NETWORKING_EVENT) {
                    topicVO.startTime = DurationsEnum.START_FIRST_SESSION.value
                } else if (topicVO.name == LUNCH_EVENT) {
                    topicVO.startTime = DurationsEnum.LUNCH_TIME.value
                }
                else {
                    topicVO.startTime = sumTime(prevEvent.startTime, prevEvent.minsDuration)
                }
            }
            topicVO.dismissible = false
        }
        return resultTracks.toTypedArray()
    }

    private fun getLunchEvent(): TopicVO {
        return TopicVO(Date().time.toString(), LUNCH_EVENT, 60, DurationsEnum.LUNCH_TIME.value, false)
    }

    private fun getNetworkingEvent(startTime: String): TopicVO {
        return TopicVO(Date().time.toString(), NETWORKING_EVENT, 0, startTime, false)
    }

    private fun sumTime(hora: String, minutos: Int): String {
        var retorno = ""
            val df = SimpleDateFormat("HH:mm")
            val calendar = Calendar.getInstance()
            calendar.time = df.parse(hora)
            calendar.add(Calendar.MINUTE, minutos)
            retorno = df.format(calendar.time)
        return retorno
    }
}