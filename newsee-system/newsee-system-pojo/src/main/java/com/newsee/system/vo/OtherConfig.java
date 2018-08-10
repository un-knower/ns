package com.newsee.system.vo;

import java.io.Serializable;

/**
 * 列表中可编辑项参数
 */
public class OtherConfig implements Serializable {
    private static final long serialVersionUID = -7753806298514621593L;
    /** 开关 */
    private boolean switchType;
    /** 表单类型 时间:date, 数字:number, 百分比类型:rate,文本框:text,输入框input */
    private String type;
    /** 是否可为空 */
    private boolean require;
    /** 最小值 */
    private Integer min;
    /** 最大值 */
    private Integer max;
    /** 最大长度 */
    private String maxlength;
    /** 保留小数位数 */
    private Integer decimal;
    /** 提示语 */
    private String placeHolder;
    /** 特殊验证类型：num,en,num,tel,phone */
    private String validateRule;
    /** disabled */
    private boolean disabled;

    public boolean isSwitchType() {
        return switchType;
    }

    public void setSwitchType(boolean switchType) {
        this.switchType = switchType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequire() {
        return require;
    }

    public void setRequire(boolean require) {
        this.require = require;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public String getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(String maxlength) {
        this.maxlength = maxlength;
    }

    public Integer getDecimal() {
        return decimal;
    }

    public void setDecimal(Integer decimal) {
        this.decimal = decimal;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    public String getValidateRule() {
        return validateRule;
    }

    public void setValidateRule(String validateRule) {
        this.validateRule = validateRule;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }


}
