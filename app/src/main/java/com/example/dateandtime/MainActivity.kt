package com.example.dateandtime

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fromDate: Button = findViewById(R.id.button_from_date)
        val fromTime: Button = findViewById(R.id.button_from_time)

        val endDate: Button = findViewById(R.id.button_end_date)
        val endTime: Button = findViewById(R.id.button_end_time)

        fromDate.setOnClickListener {
            getDate(fromDate)
        }

        endDate.setOnClickListener {
            getDate(endDate)
        }

        fromTime.setOnClickListener {
            getTime(fromTime)
        }

        endTime.setOnClickListener {
            getTime(endTime)
        }
    }

    private fun getDate(button: Button) {

        val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
        var newCalendar: Calendar = Calendar.getInstance()

        if (button.text.toString().trim().isNotEmpty()) {
            val cal = Utils.toDate(button.text.toString().trim(), "dd-MM-yyyy")
            newCalendar = Utils.toCalendar(cal ?: Date()) ?: Calendar.getInstance()
        }
        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                val newDate: Calendar = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)
                button.text = dateFormatter.format(newDate.time)
            },
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH),
            newCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun getTime(button: Button) {
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
        val minute = mcurrentTime[Calendar.MINUTE]
        val mTimePicker: TimePickerDialog
        mTimePicker = TimePickerDialog(
            this,
            { timePicker, selectedHour, selectedMinute ->
                button.text = String.format("%02d:%02d", selectedHour, selectedMinute)
            },
            hour,
            minute,
            true
        ) //Yes 24 hour time

        mTimePicker.setTitle("Select Time")
        mTimePicker.show()
    }
}