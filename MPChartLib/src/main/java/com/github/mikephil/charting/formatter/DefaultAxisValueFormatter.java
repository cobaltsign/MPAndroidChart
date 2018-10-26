package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

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

        StringBuffer b = new StringBuffer();
        for (int i = 0; i < digits; i++) {
            if (i == 0)
                b.append(".");
            b.append("0");
        }
        char groupingSeparator = this.getGroupingCharacter();
        StringBuilder pattern = new StringBuilder();
        for(int i = 0; i < 3; i++) {
            pattern.append("###");
            pattern.append(groupingSeparator);
        }
        mFormat = new DecimalFormat(pattern + "##0" + b.toString());
    }

    private char getGroupingCharacter() {
        DecimalFormatSymbols formatConfig = DecimalFormatSymbols.getInstance(new Locale("de", "ch"));
        return formatConfig.getGroupingSeparator();
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
