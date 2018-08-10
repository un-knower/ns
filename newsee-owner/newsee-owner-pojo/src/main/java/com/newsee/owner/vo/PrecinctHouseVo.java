package com.newsee.owner.vo;

import java.io.Serializable;
import java.util.List;

public class PrecinctHouseVo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -7537814838564408865L;

    /**
     * 项目ID
     */
    private Long precinctId;

    private String precinctName;
    
    private List<OwnerHouseRelationshipVo> houseRelationshipVos;

    public Long getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(Long precinctId) {
        this.precinctId = precinctId;
    }

    public String getPrecinctName() {
        return precinctName;
    }

    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }

    public List<OwnerHouseRelationshipVo> getHouseRelationshipVos() {
        return houseRelationshipVos;
    }

    public void setHouseRelationshipVos(List<OwnerHouseRelationshipVo> houseRelationshipVos) {
        this.houseRelationshipVos = houseRelationshipVos;
    }
    
    
}
