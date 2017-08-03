package com.yanxintec.sjdk.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yanxintec.sjdk.ServiceConfig;
import com.yanxintec.sjdk.global.CommonGlobal;
import com.yanxintec.sjdk.pojo.Param;
import com.yanxintec.sjdk.pojo.Req;
import com.yanxintec.sjdk.util.amrsoft.EncrypyUtil;
import com.yanxintec.sjdk.util.gson.GsonUtils;


public class HttpsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpsUtil.class);
    private static MyHostnameVerifier1 hnv = new MyHostnameVerifier1();
    private static final String KEY_STORE_TYPE_JKS = "jks";
    private static final String KEY_STORE_TYPE_P12 = "PKCS12";
    private static String key_store_client_path = ServiceConfig.HTTPS_CLIENT;
    private static String key_store_trust_path = ServiceConfig.HTTPS_KEY_STORE;
    private static String key_store_password = "yanxintec.com";
    private static String key_store_trust_password = "yanxintec.com";

    private HttpsUtil(){}
    
    /**
     * @param localFilePath	 有值即下载文件
     * @return			 返回字符串
     * @author czg
     */
    public static String httpsRequest(String url, Req req, String reqMethod) {
        return httpsRequest(url, req, reqMethod, null);
    }

    /**
     * @param localFilePath	 有值即下载文件
     * @return			 返回字符串
     * @author czg
     */
    public static String httpsRequest(String url, Req req, String reqMethod, String localFilePath) {
        int code = 0;
        StringBuilder respStr = new StringBuilder();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE_P12);
            KeyStore trustStore = KeyStore.getInstance(KEY_STORE_TYPE_JKS);
            InputStream ksIn = new FileInputStream(key_store_client_path);
            InputStream tsIn = new FileInputStream(new File(key_store_trust_path));
            try {
                keyStore.load(ksIn, key_store_password.toCharArray());
                trustStore.load(tsIn, key_store_trust_password.toCharArray());
            } finally {
                try {
                    ksIn.close();
                } catch (Exception ignore) {
                	LOGGER.error(ignore.getMessage(),ignore);
                }
                try {
                    tsIn.close();
                } catch (Exception ignore) {
                	LOGGER.error(ignore.getMessage(),ignore);
                }
            }
            // 相信自己的CA和所有自签名的证书
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, key_store_password.toCharArray())
                    .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,
                    new String[] { "TLSv1" }, null, hnv);
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            // -----数据值处理-------
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<>();

            String method = req.getMethod();
            nvps.add(new BasicNameValuePair("pro", req.getPro()));
            nvps.add(new BasicNameValuePair("method", method));
            nvps.add(new BasicNameValuePair("caller", req.getCaller()));

            String json = "";
            if (req.getParam() != null) {
                json = GsonUtils.toJson(req.getParam());
            } else {
                json = GsonUtils.toJson(req.getParams());
            }
            String base64 = "";
            // 根据method选择不同的base64方法，当前安硕需要用原始的base64方法
            if ("amrsoft".equals(method)) {
                base64 = EncrypyUtil.encryptBASE64(json.getBytes("UTF-8"));
            } else {
                base64 = Base64Coder.encodeString(json);
            }
            nvps.add(new BasicNameValuePair("param", base64));
            nvps.add(new BasicNameValuePair("paramstyle", req.getParamstyle()));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            // ---------数据值处理end-----------
            // 由服务器端自动关闭链接
            httpPost.setHeader("Connection", "close");

            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            try {
                code = response2.getStatusLine().getStatusCode();
                HttpEntity entity2 = response2.getEntity();
                InputStream is = entity2.getContent();
                if (StringUtils.isNotBlank(localFilePath)) {
                    boolean bool = writeToFile(is, localFilePath);
                    if (bool) {
                        respStr.append("文件下载成功");
                    } else {
                        respStr.append("文件下载失败");
                    }
                } else {
                    List<String> lines = IOUtils.readLines(is, "UTF-8");
                    for (int i = 0; i < lines.size(); i++) {
                        respStr.append(lines.get(i));
                    }
                }
            } finally {
                response2.close();
            }
        } catch (Exception e) {
            
            LOGGER.error("httpsUtil发起请求出现异常：" + e.getMessage(), e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                LOGGER.error("httpsUtil关闭请求出现异常：" + e.getMessage(), e);
            }
        }
        
        return respStr.toString();
    }

    /**
     * @param SendStr	 发送字符串
     * @return			 返回字符串
     */
    public static String httpsRequest2(String url, Req req, String reqMethod) throws Exception {
        StringBuilder respStr = new StringBuilder();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE_P12);
            KeyStore trustStore = KeyStore.getInstance(KEY_STORE_TYPE_JKS);
            InputStream ksIn = new FileInputStream(key_store_client_path);
            InputStream tsIn = new FileInputStream(new File(key_store_trust_path));
            try {
                keyStore.load(ksIn, key_store_password.toCharArray());
                trustStore.load(tsIn, key_store_trust_password.toCharArray());
            } finally {
                try {
                    ksIn.close();
                } catch (Exception ignore) {
                	LOGGER.error(ignore.getMessage(),ignore);
                }
                try {
                    tsIn.close();
                } catch (Exception ignore) {
                	LOGGER.error(ignore.getMessage(),ignore);
                }
            }
            // 相信自己的CA和所有自签名的证书
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, key_store_password.toCharArray())
                    .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,
                    new String[] { "TLSv1" }, null, hnv);
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            // -----数据值处理-------
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<>();

            String method = req.getMethod();
            nvps.add(new BasicNameValuePair("pro", req.getPro()));
            nvps.add(new BasicNameValuePair("method", method));
            nvps.add(new BasicNameValuePair("caller", req.getCaller()));

            Object json = "";

            if (CommonGlobal.JSON.equals(req.getParamstyle()) || CommonGlobal.XML.equals(req.getParamstyle())) {
                Param param = GsonUtils.getJson(req.getJsonParams(), Param.class);
                json = GsonUtils.toJson(param.getValue());
            } else {
                if (req.getParam() != null) {
                    json = GsonUtils.toJson(req.getParam());
                } else {
                    json = GsonUtils.toJson(req.getParams());
                }
            }

            String base64 = "";
            // 根据method选择不同的base64方法，当前安硕需要用原始的base64方法
            if ("amrsoft".equals(method)) {
                base64 = EncrypyUtil.encryptBASE64(((String) json).getBytes("UTF-8"));
            } else {
                base64 = Base64Coder.encodeString((String) json);
            }

            nvps.add(new BasicNameValuePair("param", base64));
            nvps.add(new BasicNameValuePair("paramstyle", req.getParamstyle()));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            // ---------数据值处理end-----------
            // 由服务器端自动关闭链接
            httpPost.setHeader("Connection", "close");

            LOGGER.info("#I https双向请求的url为:" + url + " 参数:" + nvps.toString() + "  未base64前的param:"
                    + json);

            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            try {
                HttpEntity entity2 = response2.getEntity();
                // 显示结果
                InputStream is = entity2.getContent();
                List<String> lines = IOUtils.readLines(is);
                for (int i = 0; i < lines.size(); i++) {
                    respStr.append(lines.get(i));
                }
            } finally {
                response2.close();
            }
        } catch (Exception e) {
            LOGGER.error("#E 调用https发生了异常 :" + e.getMessage());
        } finally {
            httpclient.close();
        }
        return respStr.toString();
    }

    /**
     * 将流写入文件
     * @param in 输入浄
     */
    public static boolean writeToFile(InputStream in, String localFilePath) {
        if (in == null) {
            return false;
        }
        File file = new File(localFilePath);
        try {
            String localpath = "";
            if (localFilePath.lastIndexOf('/') > 0) {
                localpath = localFilePath.substring(0, localFilePath.lastIndexOf('/'));
            } else if (localFilePath.lastIndexOf('\\') > 0) {
                localpath = localFilePath.substring(0, localFilePath.lastIndexOf('\\'));
            }
            File f = new File(localpath);
            // 文件夹不存在时创建文件夹
            if (!f.exists()) {
                f.mkdirs();
            }
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            byte[] buf = new byte[2048];
            int length = in.read(buf);
            while (length != -1) {
                out.write(buf, 0, length);
                length = in.read(buf);
            }
            out.close();
        } catch (Exception e) {
            LOGGER.error("将流写入文件出现异常：" + e.getMessage(), e);
        }
        return file.isFile();
    }

}

class MyHostnameVerifier1 implements X509HostnameVerifier {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyHostnameVerifier1.class);
	@Override
    public boolean verify(String hostname, SSLSession session) {
		LOGGER.info("Warning: URL Host: " + hostname + " vs. " + session.getPeerHost());
        return true;
    }
	@Override
    public void verify(String host, SSLSocket ssl) {
        return;
    }
	@Override
    public void verify(String host, X509Certificate cert) {
        return;
    }
	@Override
    public void verify(String host, String[] cns, String[] subjectAlts) {
        return;
    }

}
