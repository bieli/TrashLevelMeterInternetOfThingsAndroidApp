package net.bieli.trashlevel.service.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.lang.Exception;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import net.bieli.trashlevel.service.IClient;

public class Client implements IClient {
    private String serviceUri = "";
    private String logTag = "";
    private StringBuffer outputStringBuffer;
    private String token = "defaultTOKEN";

    private DefaultHttpClient httpClient;

    private HttpPost request;

    private HttpGet getRequest;

    private URI uri;

    private Log logger;

    Object context;

    public Client(Object context) {
        this.context = context;
    }

    @Override
    public void connect(String host) {
        setServiceUri(host);
    }

    @Override
    public String subscribe(String topicEndpoint) {
        String result = "";

        if (topicEndpoint.length() == 0 ) {
            throw new IllegalArgumentException("topicEndpoint IS EMPTY");
        }
//        NetworkTask networkTask = (NetworkTask) new NetworkTask().execute(getServiceUri() + "/" + topicEndpoint);
//        result = networkTask.getResultValue();
        logger.v(logTag, "HTTP GET starting ...getServiceUri(): '" + getServiceUri() + "'");
/*
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(getServiceUri() + "/" + topicEndpoint);
        HttpResponse response = null;
        BufferedReader bufferedReader = null;
        try {
            response = client.execute(request);

            bufferedReader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent())
            );

            result = bufferedReader.readLine().trim();
            bufferedReader.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            logger.e(logTag, e.toString());
            result = "";
        } catch (IOException e) {
            e.printStackTrace();
            logger.e(logTag, e.toString());
            result = "";
        } catch (Exception e) {
            e.printStackTrace();
            logger.e(logTag, e.toString());
            result = "";
        } finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    result = "";
                }
            }
        }
*/


        BufferedReader bufferedReader = null;

        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;

        getRequest = new HttpGet(getServiceUri() + "/" + topicEndpoint);
        getRequest.addHeader("HA-Token", token);
        logger.v(logTag, "add header 'HA-Token': '" + token + "'");

        try {
            logger.v(logTag, "Just about to send HTTP request to " + getRequest.getURI());
            response = client.execute(getRequest);

            if (response != null) {
                logger.v(logTag, "Receiving HTTP response..");
                logger.v(logTag, response.toString());

                bufferedReader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent())
                );

                result = bufferedReader.readLine().trim();

                bufferedReader.close();

                logger.v(logTag, "Received HTTP response with data: '" + result + "'");
            } else {
                logger.e(logTag, "PROBLEM with received HTTP response.");
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            logger.e(logTag, e.toString());
            result = "";
        } catch (IOException e) {
            e.printStackTrace();
            logger.e(logTag, e.toString());
            result = "";
        } catch (Exception e) {
            e.printStackTrace();
            logger.e(logTag, e.toString());
            result = "";
        } finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                    logger.v(logTag, "bufferedReader.close()");
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.v(logTag, e.toString());
                    result = "";
                }
            }
        }

        logger.v(logTag, "Http GET END.");

        return result;
    }

    @Override
    public void disconnect() {
    }

    private class NetworkTask extends AsyncTask<String, Void, String> {

        private String resultValue;

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String result = "";

            if (url.length() == 0 ) {
                throw new IllegalArgumentException("url IS EMPTY");
            }

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = null;
            BufferedReader bufferedReader = null;
            try {
                response = client.execute(request);

                bufferedReader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent())
                );

//                String line = "";
//                String LineSeparator = System.getProperty("line.separator");
//                while ((line = bufferedReader.readLine()) != null) {
//                    outputStringBuffer.append(line + LineSeparator);
//                }


                result = bufferedReader.readLine().trim();
                bufferedReader.close();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                logger.e(logTag, e.toString());
                result = "";
            } catch (IOException e) {
                e.printStackTrace();
                logger.e(logTag, e.toString());
                result = "";
            } catch (Exception e) {
                e.printStackTrace();
                logger.e(logTag, e.toString());
                result = "";
            } finally {
                if (bufferedReader != null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        result = "";
                    }
                }
            }

            return result;
        }

        protected void onPostExecute(String result) {
            resultValue = result.trim();
        }

        public String getResultValue() {
            return resultValue;
        }
    }

    @Override
    public boolean publish(String topicEndpoint, String messageBody) {
        Boolean result = true;

        if (topicEndpoint.length() == 0 ) {
            throw new IllegalArgumentException("topicEndpoint IS EMPTY");
        }


        if (messageBody.length() == 0 ) {
            throw new IllegalArgumentException("messageBody IS EMPTY");
        }

        outputStringBuffer = new StringBuffer("");

        logger.v(logTag, "HTTP POST starting ...");

        BufferedReader bufferedReader = null;

        uri.resolve(getServiceUri());
        request.setURI(uri);

        request.addHeader("HA-Token", token);
        logger.v(logTag, "add header 'HA-Token': '" + token + "'");

        List<NameValuePair> postParameters = new ArrayList<>();
        postParameters.add(
                new BasicNameValuePair(
                        "SET_OUTPUT_DIGITAL#A#1",
                        "1110"
                )
        );

        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParameters);
            request.setEntity(entity);

            logger.v(logTag, "Just about to send http request to " + request.getURI());
            HttpResponse response = httpClient.execute(request);

            logger.v(logTag, "Receiving http response..");
            logger.v(logTag, response.toString());

            bufferedReader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent())
            );

            String line = "";
            String LineSeparator = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null) {
                outputStringBuffer.append(line + LineSeparator);
            }

            bufferedReader.close();

            logger.v(logTag, "Received http response.");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            logger.e(logTag, e.toString());
            result = false;
        } catch (IOException e) {
            e.printStackTrace();
            logger.e(logTag, e.toString());
            result = false;
        } catch (Exception e) {
            e.printStackTrace();
            logger.e(logTag, e.toString());
            result = false;
        } finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                    logger.v(logTag, "bufferedReader.close()");
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.v(logTag, e.toString());
                    result = false;
                }
            }
        }

        logger.v(logTag, "Http POST END.");

        return result;
    }

    public void setLoggerTag(String logTag) {
        this.logTag = logTag;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getServiceUri() {
        return serviceUri;
    }

    public void setServiceUri(String serviceUri) {
        this.serviceUri = serviceUri;
    }

    public StringBuffer getOutputStringBuffer() {
        return outputStringBuffer;
    }
}