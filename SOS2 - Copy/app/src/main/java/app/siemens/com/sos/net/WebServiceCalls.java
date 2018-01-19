package app.siemens.com.sos.net;/*
package app.siemens.com.sos.net;

import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.inn.fsa.data.AccessChamberInstallation;
import com.inn.fsa.data.AppConstants;
import com.inn.fsa.data.CableBlowing;
import com.inn.fsa.data.EquipmentInstallation;
import com.inn.fsa.data.FiberScopeForOTDR;
import com.inn.fsa.data.FiberScopeForPM;
import com.inn.fsa.data.GenericTask;
import com.inn.fsa.data.MessageConstant;
import com.inn.fsa.data.OTDRTesting;
import com.inn.fsa.data.OpticalTesting;
import com.inn.fsa.data.PMTesting;
import com.inn.fsa.data.Splicing;
import com.inn.fsa.data.TrenchingAndDucting;
import com.inn.fsa.frag.FiberScopeOTDRFrag;

public class WebServiceCalls {

	private Context mContext;
	private final String TAG = WebServiceCalls.class.getSimpleName();

	public WebServiceCalls(Context context) {
		this.mContext = context;
	}

	*/
/************ Task Uploading ****************//*


	public String getTaskUrl(String taskName) {
		String url = "";
		if (MessageConstant.ACCESSCHAMBERINSTALLATION.equalsIgnoreCase(taskName)) {
			url = UrlConfig.SYNC_ACCESSCHAMBERINSTALLATION;
		} else if (MessageConstant.CABLEBLOWING.equalsIgnoreCase(taskName)) {
			url = UrlConfig.SYNC_CABLEBLOWING;
		} else if (MessageConstant.EQUIPMENTINSTALLATION.equalsIgnoreCase(taskName)) {
			url = UrlConfig.SYNC_EQUIPMENTINSTALLATION;
		} else if (MessageConstant.OPYICALTESTING.equalsIgnoreCase(taskName)) {
			url = UrlConfig.SYNC_OPYICALTESTING;
		} else if (MessageConstant.SPLICING.equalsIgnoreCase(taskName)) {
			url = UrlConfig.SYNC_SPLICING;
		} else if (MessageConstant.TRENCHINGANDDUCTING.equalsIgnoreCase(taskName)) {
			url = UrlConfig.SYNC_TRENCHINGANDDUCTING;
		} else if (MessageConstant.PMTESTING.equalsIgnoreCase(taskName)) {
			url = UrlConfig.SYNC_POWERMETER_TESTING;
		} else if (MessageConstant.OTDRTESTING.equalsIgnoreCase(taskName)) {
			url = UrlConfig.SYNC_OTDR_TESTING;
		}else if (MessageConstant.FIBER_SCOPE_PMTESTING.equalsIgnoreCase(taskName)) {
			url = UrlConfig.SYNC_FIBER_SCOPE_PM_TESTING;
		}else if (MessageConstant.FIBER_SCOPE_OTDRTESTING.equalsIgnoreCase(taskName)) {
			url = UrlConfig.SYNC_FIBER_SCOPE_OTDR_TESTING;
		}

		return url;
	}

	public String getTaskJson(String taskName, String taskJson) {
		JsonObject jsObject = new JsonObject();
		if (MessageConstant.ACCESSCHAMBERINSTALLATION.equalsIgnoreCase(taskName)) {
			AccessChamberInstallation ac = new Gson().fromJson(taskJson, AccessChamberInstallation.class);
			jsObject.addProperty("TASKID", ac.getTASKID());
			jsObject.addProperty("INSTALLATION_STATUS", ac.getINSTALLATION_STATUS());
			jsObject.addProperty("REMARKS", ac.getRemarks());
			jsObject.addProperty("TASKSTATUS", ac.getTASKSTATUS());
		} else if (MessageConstant.CABLEBLOWING.equalsIgnoreCase(taskName)) {
			CableBlowing cb = new Gson().fromJson(taskJson, CableBlowing.class);
			jsObject.addProperty("TASKID", cb.getTASKID());
			jsObject.addProperty("INSTALLATION_STATUS", cb.getINSTALLATION_STATUS());
			jsObject.addProperty("REMARKS", cb.getRemarks());
			jsObject.addProperty("ACTUAL_LENGTH", cb.getACTUAL_LENGTH());
			jsObject.addProperty("TASKSTATUS", cb.getTASKSTATUS());
		} else if (MessageConstant.EQUIPMENTINSTALLATION.equalsIgnoreCase(taskName)) {
			EquipmentInstallation eq = new Gson().fromJson(taskJson, EquipmentInstallation.class);
			jsObject.addProperty("TASKID", eq.getTASKID());
			jsObject.addProperty("INSTALLATION_STATUS", eq.getINSTALLATION_STATUS());
			jsObject.addProperty("REMARKS", eq.getRemarks());
			jsObject.addProperty("TASKSTATUS", eq.getTASKSTATUS());
			jsObject.addProperty("EquipmentSerialNumber", eq.getEQUIPMENT_SERIAL_NO());
			
			System.out.println("Uploading JSON keys "+"latitude"+eq.getLatitude()+"\n"
			+"longitude"+ eq.getLongitude()+"\nregistrationDate"+eq.getDateTime());
			
			jsObject.addProperty("latitude", eq.getLatitude());
			jsObject.addProperty("longitude", eq.getLongitude());
			jsObject.addProperty("registrationDate", eq.getDateTime()); 
			
		} 
		else if (MessageConstant.PMTESTING.equalsIgnoreCase(taskName)) {
			PMTesting opt = new Gson().fromJson(taskJson, PMTesting.class);
			jsObject.addProperty("taskId", opt.getTaskId());
			System.out.println("test case list: " + new Gson().toJson(opt.getTestCaseList()).toString());
			jsObject.addProperty("testCaseList",new Gson().toJson(opt.getTestCaseList()).toString());
			jsObject.addProperty("remarks", opt.getRemarks());
			jsObject.addProperty("deviceId", opt.getDeviceId());
			jsObject.addProperty("taskStatus", opt.getTaskStatus());
			jsObject.addProperty("deviceMake", opt.getDeviceMake());
			jsObject.addProperty("deviceModel", opt.getDeviceModel());
			jsObject.addProperty("deviceSerialNo", opt.getDeviceSerialNo());
		} 
		else if (MessageConstant.OTDRTESTING.equalsIgnoreCase(taskName)) {
			OTDRTesting opt = new Gson().fromJson(taskJson, OTDRTesting.class);
			jsObject.addProperty("taskId", opt.getTaskId());
			jsObject.addProperty("remarks", opt.getRemarks());
			jsObject.addProperty("taskStatus", opt.getTaskStatus());
			jsObject.addProperty("deviceMake", opt.getDeviceMake());
			jsObject.addProperty("deviceModel", opt.getDeviceModel());
			jsObject.addProperty("deviceSerialNo", opt.getDeviceSerialNo());
			System.out.println("test case list: " + new Gson().toJson(opt.getTestCaseList()).toString());
			jsObject.addProperty("testCaseList",new Gson().toJson(opt.getTestCaseList()).toString());
			
		} else if (MessageConstant.FIBER_SCOPE_PMTESTING.equalsIgnoreCase(taskName)) {
			FiberScopeForPM opt = new Gson().fromJson(taskJson, FiberScopeForPM.class);
			jsObject.addProperty("taskId", opt.getTaskId());
			jsObject.addProperty("remarks", opt.getRemarks());
			jsObject.addProperty("taskStatus", opt.getTaskStatus());
			jsObject.addProperty("deviceId", opt.getDeviceId());
			jsObject.addProperty("deviceMake", opt.getDeviceMake());
			jsObject.addProperty("deviceModel", opt.getDeviceModel());
			jsObject.addProperty("deviceSerialNo", opt.getDeviceSerialNo());
			System.out.println("test case list: " + new Gson().toJson(opt.getTestCaseList()).toString());
			jsObject.addProperty("testCaseList",new Gson().toJson(opt.getTestCaseList()).toString());
			
		} else if (MessageConstant.FIBER_SCOPE_OTDRTESTING.equalsIgnoreCase(taskName)) {
			FiberScopeForOTDR opt = new Gson().fromJson(taskJson, FiberScopeForOTDR.class);
			jsObject.addProperty("taskId", opt.getTaskId());
			jsObject.addProperty("remarks", opt.getRemarks());
			jsObject.addProperty("taskStatus", opt.getTaskStatus());
			jsObject.addProperty("deviceMake", opt.getDeviceMake());
			jsObject.addProperty("deviceModel", opt.getDeviceModel());
			jsObject.addProperty("deviceSerialNo", opt.getDeviceSerialNo());
			System.out.println("test case list: " + new Gson().toJson(opt.getTestCaseList()).toString());
			jsObject.addProperty("testCaseList",new Gson().toJson(opt.getTestCaseList()).toString());
			
		} 
		
//		else if (MessageConstant.OPYICALTESTING.equalsIgnoreCase(taskName)) {
//			OpticalTesting opt = new Gson().fromJson(taskJson, OpticalTesting.class);
//			jsObject.addProperty("TASKID", opt.getTASKID());
//			String testResult="";
//			if(MessageConstant.YES.equalsIgnoreCase(opt.getTEST_RESULT())){
//				testResult=MessageConstant.PASS;
//			}else if(MessageConstant.FAIL.equalsIgnoreCase(opt.getTEST_RESULT())){
//				testResult=MessageConstant.FAIL;
//			}
//			jsObject.addProperty("TEST_RESULT",testResult );
//			jsObject.addProperty("REMARKS", opt.getRemarks());
//			jsObject.addProperty("TASKSTATUS", opt.getTASKSTATUS());
//		} 
		else if (MessageConstant.SPLICING.equalsIgnoreCase(taskName)) {
			Splicing opt = new Gson().fromJson(taskJson, Splicing.class);
			jsObject.addProperty("TASKID", opt.getTASKID());
			jsObject.addProperty("NOOFSPLICES", opt.getNOOFSPLICES());
			jsObject.addProperty("REMARKS", opt.getRemarks());
			jsObject.addProperty("INSTALLATION_STATUS", opt.getINSTALLATION_STATUS());
			jsObject.addProperty("TASKSTATUS", opt.getTASKSTATUS());
		} else if (MessageConstant.TRENCHINGANDDUCTING.equalsIgnoreCase(taskName)) {
			TrenchingAndDucting opt = new Gson().fromJson(taskJson, TrenchingAndDucting.class);
			jsObject.addProperty("TASKID", opt.getTASKID());
			jsObject.addProperty("ACTUAL_LENGTH", opt.getACTUAL_LENGTH());
			jsObject.addProperty("REMARKS", opt.getRemarks());
			jsObject.addProperty("TASKSTATUS", opt.getTASKSTATUS());
			
		}
		return jsObject.toString();
	}

	public String checkResponse(String response) {
		String data = AppConstants.UPLOAD_Exception;
		try {
			JSONObject rsponseData = new JSONObject(response);
			if (rsponseData.has(MessageConstant.RESULT)) {
				String result = rsponseData.getString(MessageConstant.RESULT);
				if (MessageConstant.SUCCESS.equalsIgnoreCase(result)) {
					data = AppConstants.SUCCESS;
				}else if (MessageConstant.FAILURE.equalsIgnoreCase(result)) {
					if(rsponseData.has(MessageConstant.MESSAGE)){
						String mess = rsponseData.getString(MessageConstant.MESSAGE);
						if(MessageConstant.NOTASSIGNYOU.equalsIgnoreCase(mess)){
							data = AppConstants.NOTASSIGNYOU;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public String uploadTaskInfo(String username, GenericTask task) {
		String result;
		try {
			String url = getTaskUrl(task.getTASK_NAME());
			String taskJson = getTaskJson(task.getTASK_NAME(), task.getTASK_JSON());
			
			System.out.println("Befor taskJson : " + taskJson);

			taskJson = taskJson.replaceAll("\\\\", "");
			taskJson = taskJson.replace("\"[", "[");
			taskJson = taskJson.replace("]\"", "]");
			taskJson = taskJson.replace("\"{", "{");
			taskJson = taskJson.replace("}\"", "}");
			
			System.out.println("after json : " + taskJson );

			url = url + username;
			result = RequestHandler.getInstance(mContext).sendHttpPosttRequest(url,taskJson);
			System.out.println("Before check result " + result);
			result = checkResponse(result);
			System.out.println("upload result " + result);

			if (MessageConstant.SUCCESS.equalsIgnoreCase(result)) {
				return MessageConstant.UPLOAD_SUCCESS;
			}
			else if(MessageConstant.NOTASSIGNYOU.equalsIgnoreCase(result)){
				return MessageConstant.NOTASSIGNYOU;
			}
			else {
				return MessageConstant.UPLOAD_Exception;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageConstant.UPLOAD_Exception;
		}
	}
}
*/
