package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;
import com.newsee.system.entity.NsCoreFuncinfo;
import com.newsee.system.entity.NsCoreMenu;
import com.newsee.system.entity.NsCoreResourcebutton;
import com.newsee.system.entity.NsCoreResourcecolumn;
import com.newsee.system.entity.NsCoreResourcefield;


/**
 * JEPF数据同步使用vo
 * @author xiaosisi add on 2018/01/19
 */
public class JepfVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** JEPF MENU */
	private List<NsCoreMenu> menus;
	
	/** JEPF MENU */
	private List<NsCoreFuncinfo> funcinfos;
	
	/** JEPF BUTTON */
	private List<NsCoreResourcebutton> buttons;
	
	/** JEPF COLUMN */
	private List<NsCoreResourcecolumn> columns;
	
	/** JEPF FILD */
	private List<NsCoreResourcefield> fields;

	public List<NsCoreMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<NsCoreMenu> menus) {
		this.menus = menus;
	}

	public List<NsCoreFuncinfo> getFuncinfos() {
		return funcinfos;
	}

	public void setFuncinfos(List<NsCoreFuncinfo> funcinfos) {
		this.funcinfos = funcinfos;
	}

	public List<NsCoreResourcebutton> getButtons() {
		return buttons;
	}

	public void setButtons(List<NsCoreResourcebutton> buttons) {
		this.buttons = buttons;
	}

	public List<NsCoreResourcecolumn> getColumns() {
		return columns;
	}

	public void setColumns(List<NsCoreResourcecolumn> columns) {
		this.columns = columns;
	}

	public List<NsCoreResourcefield> getFields() {
		return fields;
	}

	public void setFields(List<NsCoreResourcefield> fields) {
		this.fields = fields;
	}
}
