package com.itis.firstapp.ui.objects

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.firstapp.databinding.ItemTaskBinding
import com.itis.firstapp.model.entities.Task

class TaskHolder(
    private val binding: ItemTaskBinding,
    private val onItemChosenAction: (item: Task) -> Unit,
    private val onItemDeleteAction: (item: Task) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun bind(item: Task) =
        with(binding) {
            tvTitle.text =
                if (item.title != "")
                    item.title
                else "No title of task"
            tvTime.text =
                if (item.date != null)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        SimpleDateFormat("dd.MM.yyyy HH:mm").format(item.date)
                    } else {
                        ""
                    }
                else "No time"
            tvDescription.text =
                if (item.description != "")
                    item.description
                else "No description of task"
            tvLongitude.text =
                if (item.longitude != null)
                     "Longitude: ${item.longitude.toString()}"
                else "Longitude: -"
            tvLatitude.text =
                if (item.latitude != null)
                    "Latitude: ${item.latitude.toString()}"
                else "Latitude: -"

        itemView.setOnClickListener {
            onItemChosenAction.invoke(item)
        }

        deleteTaskBtn.setOnClickListener {
            onItemDeleteAction.invoke(item)
        }
    }

    companion object {
        fun create(parent: ViewGroup,
                   onItemChosenAction: (item: Task) -> Unit,
                   onItemDeleteAction: (item:Task) -> Unit) =
            TaskHolder (
                ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onItemChosenAction, onItemDeleteAction)
    }
}
