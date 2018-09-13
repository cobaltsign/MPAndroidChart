
package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Predefined value-formatter that formats large numbers in a pretty way.
 * Outputs: 856 = 856; 1000 = 1k; 5821 = 5.8k; 10500 = 10k; 101800 = 102k;
 * 2000000 = 2m; 7800000 = 7.8m; 92150000 = 92m; 123200000 = 123m; 9999999 =
 * 10m; 1000000000 = 1b; Special thanks to Roman Gromov
 * (https://github.com/romangromov) for this piece of code.
 *
 * @author Philipp Jahoda
 * @author Oleksandr Tyshkovets <olexandr.tyshkovets@gmail.com>
 */
public class LargeValueFormatter implements IValueFormatter, IAxisValueFormatter
{

    private String[] mSuffix = new String[]{
            "", "k", "m", "b", "t"
    };
    private int mMaxLength = 5;
    private DecimalFormat mFormat;
    private DecimalFormat mFormat2;
    private String mText = "";

    public LargeValueFormatter() {
        mFormat = new DecimalFormat("###E00");
        mFormat2 = new DecimalFormat("0.##");
    }

    /**
     * Creates a formatter that appends a specified text to the result string
     *
     * @param appendix a text that will be appended
     */
    public LargeValueFormatter(String appendix) {
        this();
        mText = appendix;
    }

    // IValueFormatter
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return makePretty(value) + mText;
    }

    // IAxisValueFormatter
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return makePretty(value) + mText;
    }

    /**
     * Set an appendix text to be added at the end of the formatted value.
     *
     * @param appendix
     */
    public void setAppendix(String appendix) {
        this.mText = appendix;
    }

    /**
     * Set custom suffix to be appended after the values.
     * Default suffix: ["", "k", "m", "b", "t"]
     *
     * @param suffix new suffix
     */
    public void setSuffix(String[] suffix) {
        this.mSuffix = suffix;
    }

    public void setMaxLength(int maxLength) {
        this.mMaxLength = maxLength;
    }

    /**
     * Formats each number properly.
     */
    private String makePretty(double number) {

        if (Math.abs(number) < 1) {
            return mFormat2.format(number);
        }

        String formatted = mFormat.format(number);
        int ePos = formatted.indexOf("E");
        int exp = Integer.valueOf(formatted.substring(ePos + 1));
        String part =
                formatted.substring(0, formatted.contains("-") && formatted.contains(".")
                        ?
                        (formatted.indexOf('.')== ePos-2 ? ePos - 2 : ePos - 1)
                        : ePos
                );
        String suffix = mSuffix[exp / 3];
        return part + suffix;
    }

    public int getDecimalDigits() {
        return 0;
    }
}
