package com.fissa.conferencemanagement

import com.fissa.conferencemanagement.logic.util.ManageUtil
import org.junit.Assert.assertEquals
import org.junit.Test

class ManageUtilUnitTest {
    @Test
    fun correctTopicNormalFormat() {
        assertEquals(ManageUtil().validateTopic("Communicating Over Distance 50min"), true)
    }

    @Test
    fun correctTopicLightning() {
        assertEquals(ManageUtil().validateTopic("Rails for Python Developers lightning"), true)
    }

    @Test
    fun wrongFormatTopicNameWithNumbers() {
        assertEquals(ManageUtil().validateTopic("Overdoing it in Python3 45min"), false)
    }

    @Test
    fun wrongFormatTopicTimeFormat() {
        assertEquals(ManageUtil().validateTopic("Common Ruby Errors 45h"), false)
    }

    @Test
    fun correctGenerateTopic() {
        assertEquals(ManageUtil().generateTopicVo("Communicating Over Distance 50min").name, "Communicating Over Distance")
        assertEquals(ManageUtil().generateTopicVo("Communicating Over Distance 50min").minsDuration, 50)
    }
}