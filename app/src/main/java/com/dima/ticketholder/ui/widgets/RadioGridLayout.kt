package com.dima.ticketholder.ui.widgets

import android.content.Context
import android.view.View.generateViewId
import android.widget.LinearLayout
import androidx.core.view.setMargins
import androidx.lifecycle.LiveData
import com.dima.ticketholder.utils.getPixelsFromDp

class RadioGridLayout(
    context: Context,
    private val state: LiveData<BooleanArray>,
    private val setter: (Int, Boolean) -> Unit,
    rows: Int,
    private val cols: Int
) {

    private val _verticalLayout: LinearLayout = LinearLayout(context)
    val layout: LinearLayout
        get() = _verticalLayout

    private val horizontalLayouts: MutableList<LinearLayout> = ArrayList(rows)
    private val buttons: MutableList<ToggleableRadioButton> = ArrayList(rows * cols)

    private val linearLayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT)

    private val buttonSize = getPixelsFromDp(context, 30.0f)
    private val buttonMargin = getPixelsFromDp(context, 4.0f)

    init {
        _verticalLayout.apply {
            id = generateViewId()
            layoutParams = linearLayoutParams
            orientation = LinearLayout.VERTICAL
        }

        val buttonParams = LinearLayout.LayoutParams(buttonSize, buttonSize)
        buttonParams.setMargins(buttonMargin)

        repeat(rows) { row ->
            val layout = LinearLayout(context).apply {
                id = generateViewId()
                layoutParams = linearLayoutParams
                orientation = LinearLayout.HORIZONTAL
            }

            repeat(cols) { col ->
                val button = ToggleableRadioButton(context).apply {
                    id = generateViewId()
                    layoutParams = buttonParams
                    setOnCheckedChangeListener { _, isChecked ->
                        setter(row * cols + col, isChecked)
                    }
                }
                buttons.add(button)
                layout.addView(button)
            }

            horizontalLayouts.add(layout)
            _verticalLayout.addView(layout)
        }
    }

    fun update() {
        state.value?.forEachIndexed { index, b ->
            buttons[index].isChecked = b
        }
    }
}