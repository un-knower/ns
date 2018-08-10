package com.newsee.owner.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

import com.newsee.common.utils.CommonUtils;
import com.newsee.owner.enums.CustomerCallEnum;
import com.newsee.owner.enums.CustomerRelationEnum;
import com.newsee.owner.vo.CustomerFamilyVo;
import com.newsee.owner.vo.CustomerVo;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsCoreDictionaryitemVo;

public class OwnerUtils {

    public static String getDicName(NsCoreDictionaryVo dictionaryVo, String dictionaryValue){
        String value = "";
        if (dictionaryVo != null && !CommonUtils.isNullOrBlank(dictionaryValue)) {
            if (dictionaryVo.getDictionaryitemVos() != null) {
                for (NsCoreDictionaryitemVo dictionaryitemVo : dictionaryVo.getDictionaryitemVos()) {
                    if (dictionaryValue.equals(dictionaryitemVo.getDictionaryitemItemcode())) {
                        value = dictionaryitemVo.getDictionaryitemItemname();
                    }
                }
            }
        }
        return value;
    }
    public static String getDicArrayName(NsCoreDictionaryVo dictionaryVo, String dictionaryValue){
        String value = "";
        if (dictionaryVo != null && !CommonUtils.isNullOrBlank(dictionaryValue)) {
            String[] valueArray = StringUtils.delimitedListToStringArray(dictionaryValue, ",");
            if (valueArray != null) {
                List<String> valueStrList = Arrays.asList(valueArray);
                if (dictionaryVo.getDictionaryitemVos() != null) {
                    for (String valueStr : valueStrList) {
                        for (NsCoreDictionaryitemVo dictionaryitemVo : dictionaryVo.getDictionaryitemVos()) {
                            if (!CommonUtils.isNullOrBlank(valueStr) && 
                                    valueStr.equals(dictionaryitemVo.getDictionaryitemItemcode())) {
                                value = value + dictionaryitemVo.getDictionaryitemItemname() + ",";
                            }
                        }
                    }

                }
            }
        }
        if (!CommonUtils.isNullOrBlank(value)) {
            value = value.substring(0, value.length()-1);
        }
        return value;
    }
    
    public static String getDicValue(NsCoreDictionaryVo dictionaryVo, String dictionaryName){
        String value = "";
        if (dictionaryVo != null && !CommonUtils.isNullOrBlank(dictionaryName)) {
            if (dictionaryVo.getDictionaryitemVos() != null) {
                for (NsCoreDictionaryitemVo dictionaryitemVo : dictionaryVo.getDictionaryitemVos()) {
                    if (dictionaryName.equals(dictionaryitemVo.getDictionaryitemItemname())) {
                        value = dictionaryitemVo.getDictionaryitemItemcode();
                    }
                }
            }
        }
        return value;
    }
    /**
     * 
    * @Title: getCallForFamily 
    * @Description: 获取家庭成员关系称呼
    * @param familyInfo
    * @return CustomerCallEnum    返回类型 
    * @date  2018年3月2日 下午1:55:44
    * @author wangrenjie
     */
    public static CustomerCallEnum getCallForFamily(CustomerFamilyVo familyInfo){
        CustomerCallEnum callEnum = null;
        CustomerRelationEnum customerRelationEnum = CustomerRelationEnum.getInstance(familyInfo.getOwnerRelationship());
        if (customerRelationEnum != null) {
            CustomerVo customerVo = familyInfo.getCustomerVo();
            if (customerVo != null && !CommonUtils.isObjectEmpty(customerVo.getGender())) {
                switch (customerRelationEnum) {
                //个人
                case FUQI:
                    if ("1".equals(customerVo.getGender())) {
                        callEnum = CustomerCallEnum.ZHANGFU;
                    }else {
                        callEnum = CustomerCallEnum.QIZI;
                    }
                    break;
                case FUMU:
                    if ("1".equals(customerVo.getGender())) {
                        callEnum = CustomerCallEnum.FUQIN;
                    }else {
                        callEnum = CustomerCallEnum.MUQIN;
                    }
                    break;
                case ZINV:
                    if ("1".equals(customerVo.getGender())) {
                        callEnum = CustomerCallEnum.ERZI;
                    }else {
                        callEnum = CustomerCallEnum.NVER;
                    }                
                    break;
                case XIONGDI:
                    callEnum = CustomerCallEnum.XIONGDI;
                    break;
                case XIONGMEI:
                    callEnum = CustomerCallEnum.XIONGMEI;
                    break;
                case JIEDI:
                    callEnum = CustomerCallEnum.JIEDI;
                    break;
                case JIEMEI:
                    callEnum = CustomerCallEnum.JIEMEI;
                    break;
                case ZUFUMU:
                    if ("1".equals(customerVo.getGender())) {
                        callEnum = CustomerCallEnum.ZUFU;
                    }else {
                        callEnum = CustomerCallEnum.ZUMU;
                    }     
                    break;
                case SUNZINV:
                    if ("1".equals(customerVo.getGender())) {
                        callEnum = CustomerCallEnum.SUNZI;
                    }else {
                        callEnum = CustomerCallEnum.SUNNV;
                    }     
                    break;
                //企业
                case DONGSHIZHANG:
                    callEnum = CustomerCallEnum.DONGSHIZHANG;
                    break;
                case ZONGJINGLI:
                    callEnum = CustomerCallEnum.ZONGJINGLI;
                    break;
                case CAIWU:
                    callEnum = CustomerCallEnum.CAIWU;
                    break;
                case RENSHI:
                    callEnum = CustomerCallEnum.RENSHI;
                    break;
                case XINGZHENG:
                    callEnum = CustomerCallEnum.XINGZHENG;
                    break;
                case ZIGONGSI:
                    callEnum = CustomerCallEnum.ZIGONGSI;
                    break;
                //其他
                case QITA:
                    callEnum = CustomerCallEnum.QITA;
                    break;
                default:
                    callEnum = CustomerCallEnum.QITA;
                    break;
                }                          
            }
        }
        return callEnum;
    }
    
    public static CustomerRelationEnum getContraryForFamily(CustomerFamilyVo familyInfo){
        CustomerRelationEnum contraryRelationEnum = null;
        CustomerRelationEnum customerRelationEnum = CustomerRelationEnum.getInstance(familyInfo.getOwnerRelationship());
        if (customerRelationEnum != null) {
            switch (customerRelationEnum) {
            //个人
            case FUQI:
                contraryRelationEnum = CustomerRelationEnum.FUQI;
                break;
            case FUMU:
                contraryRelationEnum = CustomerRelationEnum.ZINV;
                break;
            case ZINV:
                contraryRelationEnum = CustomerRelationEnum.FUMU;
                break;
            case XIONGDI:
                contraryRelationEnum = CustomerRelationEnum.XIONGDI;
                break;
            case XIONGMEI:
                contraryRelationEnum = CustomerRelationEnum.XIONGMEI;
                break;
            case JIEDI:
                contraryRelationEnum = CustomerRelationEnum.JIEDI;
                break;
            case JIEMEI:
                contraryRelationEnum = CustomerRelationEnum.JIEMEI;
                break;
            case ZUFUMU:
                contraryRelationEnum = CustomerRelationEnum.SUNZINV;
                break;
            case SUNZINV:
                contraryRelationEnum = CustomerRelationEnum.ZUFUMU;
                break;
            //企业
            case DONGSHIZHANG:
            case ZONGJINGLI:
            case CAIWU:
            case RENSHI:
            case XINGZHENG:
            case ZIGONGSI:
                contraryRelationEnum = CustomerRelationEnum.QIYE;
                break;
            //其他
            case QITA:
            default:
                contraryRelationEnum = CustomerRelationEnum.QITA;
                break;
            }                          
        }
        return contraryRelationEnum;
    }
}
