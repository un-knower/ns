package com.newsee.system.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.newsee.common.utils.StringUtils;
import com.newsee.system.entity.NsCoreResourcefield;

public class FieldUtils {

    /**
     * @Description: /根据显隐表达式interpreter过滤字段
     * @author 胡乾亮
     * @date 2017年12月22日下午2:51:00
     */
    public static void filterFieldsByInterpreter(List<NsCoreResourcefield> fields,String interpreter){
        if (!StringUtils.isBlank(interpreter)) {
            Iterator<NsCoreResourcefield> it = fields.iterator();
            while (it.hasNext()) {
                NsCoreResourcefield nsField = (NsCoreResourcefield) it.next();
                String fieldInterpreter = nsField.getResourcefieldInterpreter();
                if (!StringUtils.isBlank(fieldInterpreter) && !fieldInterpreter.equals("0")) {
                    String[] strArray = fieldInterpreter.split("==");
                    if ((!(strArray[1].trim()).equals(interpreter))) {
                        it.remove();
                    }
                }
            }
        }
    }
    
    /**
     * @Description: 根据辅助配置中的formOperateType（0：新增1：编辑）过滤字段 
     * @author 胡乾亮
     * @date 2017年12月22日下午3:10:34
     */
    @SuppressWarnings("unchecked")
    public static void filterFieldsByFormOperateType(List<NsCoreResourcefield> fields,String formOperateType){
        if (!StringUtils.isBlank(formOperateType)) {
            Iterator<NsCoreResourcefield> it = fields.iterator();
            while (it.hasNext()) {
                NsCoreResourcefield nsField = (NsCoreResourcefield) it.next();
                if (!StringUtils.isBlank(nsField.getResourcefieldOtherconfig())) {
                    Map<String, String> otherConfigMap =JSON.parseObject(nsField.getResourcefieldOtherconfig(), Map.class);
                    String formOperateTypeInMap = otherConfigMap.get("formOperateType");
                    if (!StringUtils.isBlank(formOperateTypeInMap)) {
                        if (!formOperateType.equals(formOperateTypeInMap)) {
                            it.remove();
                        }
                    }
                }
            }
        }
    }
    
    
}
