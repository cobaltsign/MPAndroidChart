package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * Created by alexh for Cobalt Sign SRL on 18-Sep-18.
 */
public class NegativeValueFormatter extends DefaultAxisValueFormatter  implements IValueFormatter {
    /**
     * Constructor that specifies to how many digits the value should be
     * formatted.
     *
     * @param digits
     */
    public NegativeValueFormatter(int digits) {
        super(digits);
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return super.getFormattedValue(value * -1, axis);
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return - value + "";
    }
}
