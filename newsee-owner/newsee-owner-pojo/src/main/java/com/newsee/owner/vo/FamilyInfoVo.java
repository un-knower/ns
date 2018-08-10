package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by niyang on 2017/10/13.
 */
public class FamilyInfoVo implements Serializable {

    private static final long serialVersionUID = -2413100514375792986L;

    private Long familyId;
    
    private Long ownerId;

    private Long relationOwnerId;
    
    @ApiModelProperty(value = "与被关联客户关系 1夫妻 2父母 3子女 4兄弟 5兄妹 6姐弟 7姐妹 8祖父母 9孙子女 10董事长 11总经理 12财务 13人事 14行政 15子公司 16其他")
    private String ownerRelationship;

    private String ownerRelationshipName;

    @ApiModelProperty(value = "被关联客户身份 1丈夫 2妻子 3父亲 4母亲 5儿子 6女儿 7姐妹 8兄弟 9兄妹 10姐弟 11祖父 12祖母 13孙子 14孙女 15董事长 16总经理 17财务 18人事 19行政 20子公司 21其他")
    private String relationOwnerCall;
    
    private String relationOwnerCallName;

    private CustomerVo customerVo;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getRelationOwnerId() {
        return relationOwnerId;
    }

    public void setRelationOwnerId(Long relationOwnerId) {
        this.relationOwnerId = relationOwnerId;
    }

    public String getOwnerRelationship() {
        return ownerRelationship;
    }

    public void setOwnerRelationship(String ownerRelationship) {
        this.ownerRelationship = ownerRelationship;
    }

    public String getRelationOwnerCall() {
        return relationOwnerCall;
    }

    public void setRelationOwnerCall(String relationOwnerCall) {
        this.relationOwnerCall = relationOwnerCall;
    }

    public CustomerVo getCustomerVo() {
        return customerVo;
    }

    public void setCustomerVo(CustomerVo customerVo) {
        this.customerVo = customerVo;
    }

    public String getOwnerRelationshipName() {
        return ownerRelationshipName;
    }

    public void setOwnerRelationshipName(String ownerRelationshipName) {
        this.ownerRelationshipName = ownerRelationshipName;
    }

    public String getRelationOwnerCallName() {
        return relationOwnerCallName;
    }

    public void setRelationOwnerCallName(String relationOwnerCallName) {
        this.relationOwnerCallName = relationOwnerCallName;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

}
