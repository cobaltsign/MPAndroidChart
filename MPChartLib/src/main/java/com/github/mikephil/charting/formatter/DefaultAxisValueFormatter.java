package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;

import java.text.DecimalFormat;

/**
 * Created by philipp on 02/06/16.
 */
public class DefaultAxisValueFormatter implements IAxisValueFormatter
{

    /**
     * decimalformat for formatting
     */
    protected DecimalFormat mFormat;

    /**
     * the number of decimal digits this formatter uses
     */
    protected int digits = 0;

    /**
     * Constructor that specifies to how many digits the value should be
     * formatted.
     *
     * @param digits
     */
    public DefaultAxisValueFormatter(int digits) {
        this.digits = digits;
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("en", "ch"));
        mFormat = (DecimalFormat)nf;
        mFormat.setMinimumFractionDigits(digits);
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // avoid memory allocations here (for performance)
        return mFormat.format(value);
    }

    /**
     * Returns the number of decimal digits this formatter uses or -1, if unspecified.
     *
     * @return
     */
    public int getDecimalDigits() {
        return digits;
    }
}
