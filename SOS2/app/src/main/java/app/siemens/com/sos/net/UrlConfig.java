package app.siemens.com.sos.net;

import android.os.Environment;



public interface UrlConfig {

//	static String BASE_URL = "https://api-sit.ril.com:8443/v1/fttx/"; //SIT
//	static String BASE_URL = "https://api-st.ril.com:8443/v1/fttx/"; //DEV
//	static String BASE_URL = "https://api-st.ril.com:7443/v2/fttx/";// QA
//	static String FSA_JSON_BASE_URL = "https://api-st.ril.com:8443/v2/"; //DEV
//	static String FSA_JSON_LAYER_URL= FSA_JSON_BASE_URL + "arcgis/rest/services/FTTx/FsaConstruction/FeatureServer/"; //DEV/SIT
//	public static final String SECO_BASE_URL = "https://api-st.ril.com:8443/v2/";//Change v1 to v2
//	public static String FEATURE_SERVICE_X_API_KEY = "l7xxf8b55d4fabf246aab880b9e70cf72ac2";
	
	
	static String BASE_URL = "https://api-preprod.ril.com:8443/v1/fttx/";//Prod
	static String FSA_JSON_BASE_URL = "https://api-preprod.ril.com:8443/v1/";//Prod
	static String FSA_JSON_LAYER_URL= FSA_JSON_BASE_URL + "arcgis/rest/services/Misc/FTTxEdit/FeatureServer/";//Prod
	public static final String SECO_BASE_URL = "https://api-preprod.ril.com:8443/v1/";//Prod
	public static String FEATURE_SERVICE_X_API_KEY = "l7xx8ae89de54bf24213b065e7c91151b5a3";//Prod
		

	static String refererValue = BASE_URL;
	static String login_URL = "rest/unauthorize/mobileAppAuth";
    /******************** FSA URL****************/
	
	static String GET_FSA_LIST="rest/UnAuthFSAConstructionTaskRestImpl/getFSAForAssignedTasks?username=";
	static String  GET_FSA_TASK_LIST="rest/UnAuthFSAConstructionTaskRestImpl/getAssignedTasksByFSA?username=";

	
	static String  SYNC_ACCESSCHAMBERINSTALLATION="rest/UnAuthFSAConstructionTaskRestImpl/syncAccessChamberInstallationTaskDetails?username=";
	static String  SYNC_CABLEBLOWING="rest/UnAuthFSAConstructionTaskRestImpl/syncCableBlowingTaskDetails?username=";
	static String  SYNC_EQUIPMENTINSTALLATION="rest/UnAuthFSAConstructionTaskRestImpl/syncEquipmentInstallationTaskDetails?username=";
	static String  SYNC_OPYICALTESTING="rest/UnAuthFSAConstructionTaskRestImpl/syncOpticalTestingTaskDetails?username=";
	static String  SYNC_SPLICING="rest/UnAuthFSAConstructionTaskRestImpl/syncSplicingTaskDetails?username=";
	static String  SYNC_TRENCHINGANDDUCTING="rest/UnAuthFSAConstructionTaskRestImpl/syncTrunchingAndDuctingTaskDetails?username=";
	static String SYNC_POWERMETER_TESTING = "rest/UnAuthFSAConstructionTaskRestImpl/syncPowerMeeterTestingTaskDetails?username=";
	static String SYNC_OTDR_TESTING = "rest/UnAuthFSAConstructionTaskRestImpl/syncOtdrTestingTaskDetails?username=";
	static String SYNC_FIBER_SCOPE_PM_TESTING = "rest/UnAuthFSAConstructionTaskRestImpl/syncFiberScopePowerMeeterTaskDetails?username=";
	static String SYNC_FIBER_SCOPE_OTDR_TESTING = "rest/UnAuthFSAConstructionTaskRestImpl/syncFiberScopeOtdrTaskDetails?username=";
	
	static String UPLOAD_ZIP_OF_TESTCASES = "rest/UnAuthFSAConstructionTaskRestImpl/uploadZipFileOfTestCase?username=";
	
	static String UPLOAD_EQUIPMENT_PHOTO ="rest/UnAuthFSAConstructionTaskRestImpl/uploadEquipmentPhoto?username=";
    static String UPLOAD_SPLICE_PHOTO="rest/UnAuthFSAConstructionTaskRestImpl/uploadSplicePhoto?username=";
//	static String GET_SPLICE_PLAN = "rest/UnAuthFSAConstructionTaskRestImpl/getFSASplicePlan?username=";
	static String GET_SPLICE_PLAN = "rest/UnAuthFSAConstructionTaskRestImpl/getFSASplicePlanDocument?username=";  
	
	/****************************** Downloads ********************************/
	//Changes Made V1 to V2
	static String FSA_EXTENDS_URL= FSA_JSON_BASE_URL + "fttx/get-fsa-extends?fsaid=";	
	static String FSA_JSON_FSAID_URL = "&objectIds=&time=&geometry=&geometryType=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&outFields=*&returnGeometry=true&maxAllowableOffset=&geometryPrecision=&outSR=&gdbVersion=&returnDistinctValues=false&returnIdsOnly=false&returnCountOnly=false&orderByFields=&groupByFieldsForStatistics=&outStatistics=&returnZ=false&returnM=false&f=pjson";
	
	static String ROW_JSON_URL="https://api-st.ril.com:7443/v1/fttx/gisjson/api/RJIL/aoi/GetDataByExtent/";		
	/********************************* Grid Data Download *************************************/
	
	public static final String BASE_PATH=Environment.getExternalStorageDirectory().getPath() ;
	public static final String FSA_FILE_PATH =BASE_PATH + "/.FSA/Files/";
	public static final String FSA_IMAGE_PATH =BASE_PATH + "/.FSA/Images";
	public static final String OTDR_PATH = BASE_PATH + "/.FSA/OTDR/";
	public static final String FIBER_SCOPE_PM_PATH = BASE_PATH + "/.FSA/Fiber_Scope_PM/";
	public static final String FIBER_SCOPE_OTDR_PATH = BASE_PATH + "/.FSA/Fiber_Scope_OTDR/";
	public static final String TPK_PATH = BASE_PATH + "/.FSA/INN_SURVEY/TPKS/";
	public static final String GDB_PATH = BASE_PATH + "/.FSA/INN_SURVEY/GEODATABASES/";
	public static String IMAGE_NA = "NA";

	
	public static final String BASE_MAP_INTERNAL_URL = "http://10.66.26.31:6080/";
	public static String FEATURE_SERVICE_URL = SECO_BASE_URL + "arcgis/rest/services/FTTx/FsaConstruction/FeatureServer";
	public static String BASE_MAP_URL = SECO_BASE_URL + "arcgis/rest/services/Rmaps_Updated/MapServer"; // GlobalConstant.urlTPKs;//
	public static final String URL_X_API_KEY = "l7xxf8b55d4fabf246aab880b9e70cf72ac2";

}
