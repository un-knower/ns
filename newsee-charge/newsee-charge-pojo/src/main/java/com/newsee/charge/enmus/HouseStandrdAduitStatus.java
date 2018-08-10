package com.newsee.charge.enmus;

public enum HouseStandrdAduitStatus {
	
	NOTAUDIT(0, "未审核"),
	PASSED(1, "审核通过"),
	NOTPASS(2, "审核不通过"),
	TASK_Manual(5, "直接录入"),
	TASK_HAND(4, "手动算费"),
	TASK_AUTO(3,"自动计划"),
	ENABLE(6,"启用"),
	DISABLED(7,"停用"),
	CycleTypeContinue(8,"延续"),
	CycleTypeAppoint(9,"指定周期");
	private Integer value;

    private String title;
    
    private HouseStandrdAduitStatus(Integer value, String title) {
        this.value = value;
        this.title = title;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getTitle() {
        return title;
    }

    public static HouseStandrdAduitStatus getInstance(String value) {
        if (value != null) {
        	HouseStandrdAduitStatus[] instArray = HouseStandrdAduitStatus.values();
            for (HouseStandrdAduitStatus instance : instArray) {
                if (instance.getValue().equals(value)) {
                    return instance;
                }
            }

        }
        return null;
    }
}
