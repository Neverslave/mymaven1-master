package model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSession<M extends BaseSession<M>> extends Model<M> implements IBean {

	public M setSessionId(java.lang.String SessionId) {
		set("SessionId", SessionId);
		return (M)this;
	}
	
	public java.lang.String getSessionId() {
		return getStr("SessionId");
	}

	public M setUserid(java.lang.String userid) {
		set("userid", userid);
		return (M)this;
	}
	
	public java.lang.String getUserid() {
		return getStr("userid");
	}

	public M setExpireAt(java.lang.Long expireAt) {
		set("expireAt", expireAt);
		return (M)this;
	}
	
	public java.lang.Long getExpireAt() {
		return getLong("expireAt");
	}

}
