package com.daytonatec.userservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_user_activity_log")
@NoArgsConstructor
@AllArgsConstructor
public class UserLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Long userid;
	private Date createdDate;
	private String activity;
	@Column(name = "method_input", columnDefinition = "text")
	private String inputs;
	@Column(name = "method_returndata", columnDefinition = "text")
	private String returnData;
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInputs() {
		return inputs;
	}

	public void setInputs(String inputs) {
		this.inputs = inputs;
	}

	public String getReturnData() {
		return returnData;
	}

	public void setReturnData(String returnData) {
		this.returnData = returnData;
	}

	@Override
	public String toString() {
		return "UserLog [id=" + id + ", userid=" + userid + ", createdDate=" + createdDate + ", activity=" + activity
				+ ", inputs=" + inputs + ", returnData=" + returnData + "]";
	}

	public static class Builder {
		private long id;
		private Long userid;
		private Date createdDate;
		private String activity;
		private String inputs;
		private String returnData;

		public Builder id(long id) {
			this.id = id;
			return this;
		}

		public Builder userid(Long userid) {
			this.userid = userid;
			return this;
		}

		public Builder createdDate(Date createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public Builder activity(String activity) {
			this.activity = activity;
			return this;
		}
		
		public Builder inputs(String inputs) {
			this.inputs = inputs;
			return this;
		}		

		public Builder returnData(String returnData) {
			this.returnData = returnData;
			return this;
		}		

		public UserLog build() {
			return new UserLog(this);
		}
	}

	private UserLog(Builder builder) {
		this.id = builder.id;
		this.userid = builder.userid;
		this.createdDate = builder.createdDate;
		this.activity = builder.activity;
		this.inputs = builder.inputs;
		this.returnData = builder.returnData;
	}
	
	public static Builder builder() {
		return new Builder();
	}
}
