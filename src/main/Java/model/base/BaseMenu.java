package model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseMenu<M extends BaseMenu<M>> extends Model<M> implements IBean {

	public M setMenuId(java.lang.String menuId) {
		set("menu_id", menuId);
		return (M)this;
	}
	
	public java.lang.String getMenuId() {
		return getStr("menu_id");
	}

	public M setMenuName(java.lang.String menuName) {
		set("menu_name", menuName);
		return (M)this;
	}
	
	public java.lang.String getMenuName() {
		return getStr("menu_name");
	}

	public M setMenuLevel(java.lang.Integer menuLevel) {
		set("menu_level", menuLevel);
		return (M)this;
	}
	
	public java.lang.Integer getMenuLevel() {
		return getInt("menu_level");
	}

	public M setParentId(java.lang.String parentId) {
		set("parent_id", parentId);
		return (M)this;
	}
	
	public java.lang.String getParentId() {
		return getStr("parent_id");
	}

	public M setMenuPath(java.lang.String menuPath) {
		set("menu_path", menuPath);
		return (M)this;
	}
	
	public java.lang.String getMenuPath() {
		return getStr("menu_path");
	}

	public M setMenuOrder(java.lang.Integer menuOrder) {
		set("menu_order", menuOrder);
		return (M)this;
	}
	
	public java.lang.Integer getMenuOrder() {
		return getInt("menu_order");
	}

}