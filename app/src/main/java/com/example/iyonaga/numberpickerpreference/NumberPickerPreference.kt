package com.example.iyonaga.numberpickerpreference

import android.content.Context
import android.content.res.TypedArray
import android.support.v7.preference.DialogPreference
import android.util.AttributeSet

class NumberPickerPreference(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
    DialogPreference(context, attrs, defStyleAttr, defStyleRes) {

    var minValue = 1
    var maxValue = 100
    var value = 50
        set(value) {
            field = value
            persistInt(value)
        }
    var wrapSelectorWheel = true

    init {
        isPersistent = true
        dialogIcon = null
        dialogLayoutResource = R.layout.pref_dialog_number
        setPositiveButtonText(android.R.string.ok)
        setNegativeButtonText(android.R.string.cancel)

        val typedArray = context!!.obtainStyledAttributes(attrs, R.styleable.NumberPickerPreference)
        minValue = typedArray.getInt(R.styleable.NumberPickerPreference_minValue, minValue)
        maxValue = typedArray.getInt(R.styleable.NumberPickerPreference_maxValue, maxValue)
        wrapSelectorWheel = typedArray.getBoolean(R.styleable.NumberPickerPreference_wrapSelectorWheel, wrapSelectorWheel)
        typedArray.recycle()
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.attr.preferenceStyle)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, defStyleAttr)

    override fun onGetDefaultValue(a: TypedArray, index: Int): Any {
        return a.getInt(index, minValue)
    }

    override fun onSetInitialValue(restorePersistedValue: Boolean, defaultValue: Any?) {
        value = if (restorePersistedValue) {
            getPersistedInt(value)
        } else {
            defaultValue as Int
        }
    }

    override fun getSummary(): CharSequence {
        return "value: " + getPersistedInt(value).toString()
    }
}
