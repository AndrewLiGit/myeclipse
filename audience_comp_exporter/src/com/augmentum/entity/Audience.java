package com.augmentum.entity;

public class Audience {
	private String audienceGUId;
	private String audieceName;
	public String getAudienceGUId() {
		return audienceGUId;
	}
	public void setAudienceGUId(String audienceGUId) {
		this.audienceGUId = audienceGUId;
	}
	public String getAudieceName() {
		return audieceName;
	}
	public void setAudieceName(String audieceName) {
		this.audieceName = audieceName;
	}
	@Override
	public String toString() {
		return "Audience [audienceGUId=" + audienceGUId + ", audieceName="
				+ audieceName + "]";
	}
	 
}
