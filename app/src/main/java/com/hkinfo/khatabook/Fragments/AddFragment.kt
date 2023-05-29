package com.hkinfo.khatabook.Fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.hkinfo.khatabook.Database.DBHelper
import com.hkinfo.khatabook.Models.TransModel
import com.hkinfo.khatabook.R
import com.hkinfo.khatabook.databinding.FragmentAddBinding
import com.nex3z.togglebuttongroup.MultiSelectToggleGroup
import java.text.SimpleDateFormat
import java.util.Date

class AddFragment : Fragment() {

    private val TAG = "AddFragment"
    lateinit var binding: FragmentAddBinding
    var isExpense = 0
    var seleYear = 0
    var seleMonth = 0
    var seleDate = 0
    var seleTime = ""

    lateinit var dbHelper: DBHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)

        dbHelper = DBHelper(context)

        initView()

        return binding.root
    }

    private fun initView() {
        binding.groupWeekdays.setOnCheckedChangeListener(object :
            MultiSelectToggleGroup.OnCheckedStateChangeListener {
            override fun onCheckedStateChanged(
                group: MultiSelectToggleGroup?,
                checkedId: Int,
                isChecked: Boolean
            ) {
                if (checkedId == R.id.income) {
                    isExpense = 0
                } else if (checkedId == R.id.expense) {
                    isExpense = 1
                }
            }

        })

        getTotals()
        setCurrentTimeDate()
        selectTime()
        submitData()
    }

    private fun submitData() {
        binding.submit.setOnClickListener {

            var amt = binding.edtAmount.text.toString().toInt()
            var category = binding.edtCategory.text.toString()
            var note = binding.edtNote.text.toString()

            var model = TransModel(0, amt, category, note, isExpense, seleTime, seleDate, seleMonth, seleYear)

            dbHelper.addAmount(model)

        }
    }

    private fun selectTime() {
        binding.txtTime.setOnClickListener {


            var dialog = TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {

                }

            }, 10, 0, false)

            dialog.show()

        }
    }

    private fun setCurrentTimeDate() {
        var date = Date()

        var format1 = SimpleDateFormat("hh:mm:ss a")
        var currentTime = format1.format(date)

        binding.txtTime.text = currentTime
        seleTime = currentTime

        var format = SimpleDateFormat("dd/MM/YYYY")
        var currentDate = format.format(date)

        var dates = currentDate.split("/")
        binding.txtDate.text = currentDate

        dateSelect(dates)

    }

    private fun dateSelect(dates: List<String>) {

        binding.txtDate.setOnClickListener {
            var dialog =
                DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                        Log.e(TAG, "onDateSet: $p1/$p2/$p3")

                        seleYear = p1
                        seleMonth = p2 + 1
                        seleDate = p3
                        var selectedDate = "$p3/${(p2 + 1)}/$p1"
                        binding.txtDate.text = selectedDate

                    }
                }, dates[2].toInt(), dates[1].toInt() - 1, dates[0].toInt())
            dialog.show()

        }

    }

    private fun getTotals() {
        try {


            var list = dbHelper.getTransaction()

            var income = 0
            var expense = 0
            for (trans in list) {
                if (trans.isExpense == 0) {
                    income += trans.amt
                } else {
                    expense += trans.amt
                }
            }
        } catch (e: Exception) {
        }
    }
}