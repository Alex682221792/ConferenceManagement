package com.fissa.conferencemanagement.logic.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.fissa.conferencemanagement.R
import com.fissa.conferencemanagement.logic.enums.DurationsEnum
import com.fissa.conferencemanagement.model.TopicVO
import com.fissa.conferencemanagement.view.topics.ITopicsController

class TopicListAdapter (private val context: Activity, private val topic: Array<TopicVO>,
                        private val topicController: ITopicsController?)
    : ArrayAdapter<TopicVO>(context, R.layout.topic_row_item, topic) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.topic_row_item, null, true)

        val timeText = rowView.findViewById(R.id.txtVw_time) as TextView
        val titleText = rowView.findViewById(R.id.txtVw_title) as TextView
        val imageView = rowView.findViewById(R.id.imgVw_delete) as ImageView
        val durationText = rowView.findViewById(R.id.txtVw_duration) as TextView
        val divider = rowView.findViewById(R.id.vw_divider) as View

        titleText.text = topic[position].name
        durationText.text = context.getString(R.string.time_format, topic[position].minsDuration)
        timeText.text = topic[position].startTime
        timeText.visibility = if (topic[position].startTime.isEmpty()) {View.GONE} else {View.VISIBLE}
        divider.visibility = if (topic[position].startTime.equals(DurationsEnum.START_FIRST_SESSION.value)) {View.VISIBLE} else {View.GONE}
        this.topicController.let {
            imageView.setOnClickListener { v -> this.topicController!!.removeTopic(topic[position].id) }
        }
        imageView.visibility = if(topic[position].dismissible) View.VISIBLE else View.GONE
        durationText.visibility = if(topic[position].minsDuration != 0) View.VISIBLE else View.GONE

        return rowView
    }
}