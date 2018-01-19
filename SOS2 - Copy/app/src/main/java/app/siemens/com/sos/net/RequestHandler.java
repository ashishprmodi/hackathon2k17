//
//package app.siemens.com.sos.net;
//
//import android.util.Log;
//
//
//
//import android.content.Context;
//import android.util.Log;
//
//import com.inn.fsa.data.AppConstants;
//import com.inn.fsa.data.MessageConstant;
//import com.inn.fsa.util.AppUtil;
//import com.inn.fsa.util.MySSLSocketFactory;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.HttpVersion;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.ClientConnectionManager;
//import org.apache.http.conn.scheme.Scheme;
//import org.apache.http.conn.scheme.SchemeRegistry;
//import org.apache.http.conn.ssl.SSLSocketFactory;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.conn.SingleClientConnManager;
//import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.CoreConnectionPNames;
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;
//import org.apache.http.params.HttpProtocolParams;
//import org.apache.http.protocol.HTTP;
//
//import java.io.InputStreamReader;
//import java.net.ConnectException;
//import java.security.KeyStore;
//
//public class RequestHandler {
//	private static final String TAG = RequestHandler.class.getSimpleName();
//	private static final int CONNECTION_TIME_OUT = 15 * 1000;
//	private static final int SOCKET_TIME_OUT_MIN = 30 * 1000;
//	private static final int SOCKET_TIME_OUT_LESS = 10 * 1000;
//	private static final int SOCKET_TIME_OUT_AVG = 1 * 60 * 1000;
//	private static final int SOCKET_TIME_OUT_MAX = 2 * 60 * 1000;
//	private static RequestHandler _instance;
//	private static Context mContext;
//	public static DefaultHttpClient mHttpclient;
//	private int attempt = 0;
//
//	private RequestHandler() {
//		HttpParams httpParameters = new BasicHttpParams();
//		HttpConnectionParams.setConnectionTimeout(httpParameters, CONNECTION_TIME_OUT);
//		mHttpclient = new DefaultHttpClient(httpParameters);
//
//		// mHttpclient = createClientConnectionManager();
//		//mHttpclient = getNewHttpClient();
//	}
//
//
//	/**
//	 * returns the single object.
//	 *
//	 * @param pContext
//	 * @return
//	 *//*
//
///*
//
//	public static RequestHandler getInstance(Context pContext) {
//		mContext = pContext;
//		if (_instance == null) {
//			_instance = new RequestHandler();
//		}
//		return _instance;
//	}
//
//
///**
// * used to create http client object for HTTPS with verified ertificate.
// *
// * @return
//
//
//	protected DefaultHttpClient createClientConnectionManager() {
//		SchemeRegistry registry = new SchemeRegistry();
//		registry.register(new Scheme("https", newSslSocketFactory(), 443));
//		BasicHttpParams params = new BasicHttpParams();
//		ClientConnectionManager cm = new SingleClientConnManager(params, registry);
//		return new DefaultHttpClient(cm, params);
//	}
//
//
//
///**
// * this is used to get https client to test on testing server.do not use
// * this on production.
//
//
//	public DefaultHttpClient getNewHttpClient() {
//		try {
//			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//			trustStore.load(null, null);
//
//			SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
//			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//
//			HttpParams params = new BasicHttpParams();
//			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
//			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
//
//			SchemeRegistry registry = new SchemeRegistry();
//			registry.register(new Scheme("https", sf, 443));
//
//			ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
//
//			return new DefaultHttpClient(ccm, params);
//		} catch (Exception e) {
//			return new DefaultHttpClient();
//		}
//	}
//
//
//	/**
//	 * Make an HTTP GET request
//	 *
//	 * @param url
//	 * @return
//	 *//*
//*/
//	public String sendHttpGetRequest(String url) {
//		Log.d(TAG, "requested url is " + url);
//		try {
//			HttpGet httpGet = null;
//			httpGet = new HttpGet(url);
//			httpGet.setHeader("applicationName", AppConstants.APP_NAME);
//			mHttpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, SOCKET_TIME_OUT_AVG);
//			HttpResponse response = mHttpclient.execute(httpGet);
//
//			HttpEntity entity = response.getEntity();
//			InputStreamReader isr = new InputStreamReader(entity.getContent());
//			String msg = AppUtil.readData(isr);
//
//			System.out.println("status" + response.getStatusLine());
//			System.out.println("msg" + response.getStatusLine());
//
//			isr.close();
//			entity.consumeContent();
//
//			if (msg.contains("login")) {
//				return MessageConstant.SESSION_EXPIRED;
//			}
//
//			attempt = 0;
//			return msg;
//
//		} catch (ClientProtocolException e) {
//			Log.e("log_tag", e.toString());
//			e.printStackTrace();
//			if (attempt != 3) {
//				attempt++;
//				return sendHttpGetRequest(url);
//			} else {
//				return MessageConstant.Exception;
//			}
//		} catch (ConnectException e) {
//			if (attempt != 3) {
//				attempt++;
//				return sendHttpGetRequest(url);
//			} else {
//				return MessageConstant.Exception;
//			}
//		} catch (Exception e) {
//			if (attempt != 3) {
//				attempt++;
//				return sendHttpGetRequest(url);
//			} else {
//				return MessageConstant.Exception;
//			}
//		} finally {
//			mHttpclient.getConnectionManager().closeExpiredConnections();
//		}
//	}
//}
//
//
//
