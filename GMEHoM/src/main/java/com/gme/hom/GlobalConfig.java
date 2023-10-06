package com.gme.hom;

public class GlobalConfig {

	public final static String ORG_NAME = "GME Remit";
	
	public final static String SYSTEM_DEFAULT = "SYSTEM";

	// public final static String DB_DRIVER = "org.postgresql.Driver";
	// public final static String DB_USERNAME = "postgres";
	// public final static String DB_PASSWORD = "w!nterc@stle";
	// public final static String DB_URL =
	// "jdbc:postgresql://192.168.137.2:5432/gme_hom";
	public final static String DB_URL = "jdbc:postgresql://localhost:5432/gme_hom";

	public final static String MAIL_SERVER_HOST = "smtp.office365.com";
	public final static int MAIL_SERVER_PORT = 587;
	public final static String MAIL_SERVER_USERNAME = "uatinfo@gmeremit.com";
	public final static String MAIL_SERVER_PASSWORD = "Vok87481";
	public final static String MAIL_SERVER_FROM = "GME Remit <uatinfo@gmeremit.com>";
	public final static String MAIL_SERVER_PROTOCOL = "smtp";
	public final static String MAIL_SERVER_AUTH = "true";
	public final static String MAIL_SERVER_STARTTLS = "true";
	public final static String MAIL_DEBUG = "true";

	public final static String SMS_API_URL = "https://fastapi.swifttech.com.np:8080";
	public final static String SMS_API_USERNAME = "GME_HOM";
	public final static String SMS_API_PASSWORD = "GM3@123";
	public final static String SMS_API_CONTENTTYPE = "application/json";
	public final static String SMS_API_ORGCODE = "GME";
	public final static String SMS_API_CLIENTLOGIN = "N";

	// public final static String FILES_DOCUMENT_LOC = "C:\\temp\\"; public final
	public final static String FILES_DOCUMENT_LOC = "/home/gmehom/files/";

	public final static int AUTH_TIMEOUT = 24; // HOURS
	public final static int AUTH_JWT_TIMEOUT = AUTH_TIMEOUT * 60 * 60 * 1000; // 1 day in milliseconds
	public final static String AUTH_JWT_SECRET = "pram0d4gm3h0mWalongkeyfortheJWTtokenwintercastleIsCold";
	public final static int AUTH_COOKIE_MAXAGE = AUTH_TIMEOUT * 60 * 60; // 1 day in seconds public
	public final static int AUTH_OTP_MAXAGE = 30; // minutes

	public final static String DATA_ENTITY_HASH = "MD5";
	public final static String DATA_ROW_HASH = "SHA256";

}
