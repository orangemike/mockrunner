package de.mockrunner.example;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.TextTag;

public class ConstrainedNumericTextTag extends TextTag
{
    private int minValue = 0;
    private int maxValue = Integer.MAX_VALUE;
    
    public int getMaxValue()
    {
        return maxValue;
    }

    public int getMinValue()
    {
        return minValue;
    }

    public void setMaxValue(int maxValue)
    {
        this.maxValue = maxValue;
    }

    public void setMinValue(int minValue)
    {
        this.minValue = minValue;
    }

    public void release()
    {
        super.release();
        minValue = 0;
        maxValue = Integer.MAX_VALUE;
    }

    public int doStartTag() throws JspException
    {
        setOnblur(createJavaScriptHandler());
        return super.doStartTag();
    }
    
    private String createJavaScriptHandler()
    {
        StringBuffer handler = new StringBuffer();
        handler.append("checkConstraints(");
        handler.append("this,");
        handler.append(getMinValue() + ",");
        handler.append(getMaxValue() + ");");
        if(null != getOnblur()) handler.append(getOnblur());
        return handler.toString();
    }
}
