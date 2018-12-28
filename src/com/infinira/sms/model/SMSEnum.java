package com.infinira.sms.model;

import java.text.MessageFormat;

public final class SMSEnum{

	public enum Gender{
		MALE("M"),FEMALE("F"),OTHERS("O");
		private final String gender;

		private Gender(String gender) {
			this.gender = gender;
		}

		public String getGender() {
			return this.gender;
		}
		public static Gender validateGender(String value) {
			Gender gender = null;
			for (Gender gdr : Gender.values()) {				
				if (gdr.gender.equalsIgnoreCase(value)) {
					gender = gdr;
				}
			}
			if(gender == null){
				throw new RuntimeException(MessageFormat.format("Gender <{0}> is invalid", value));
			}			
			return gender;
		}		
	}

	public enum IdType{
		AADHAR("aadhar");
		private final String idType;

		private IdType(String idType) {
			this.idType= idType;
		}

		public String getIdType() {
			return this.idType;
		}
		public static IdType validateIdType(String value) {
			IdType idType= null;
			for (IdType id_Type: IdType.values()) {
				if (id_Type.idType.equalsIgnoreCase(value)) {
					idType = id_Type;
				}
			}
			if(idType == null){
				throw new RuntimeException(MessageFormat.format("IdType <{0}> is invalid", value));
			}
			return idType;
		}
	}
}
