package ru.yandex.hackaton.server.geocoder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpProtocolParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sergey Polovko
 */
public abstract class DataHost<T> {

    private static final Logger logger = LoggerFactory.getLogger(DataHost.class);

    /**
     * user agent headers
     */
    private static final String[] USER_AGENTS = {
            "Mozilla/5.0 (X11; Linux i686) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/11.0.696.71 Safari/534.24",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C)",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)",
            "Android-x86-1.6-r2 - Mozilla/5.0 (Linux; U; Android 1.6; en-us; eeepc Build/Donut) AppleWebKit/528.5+ (KHTML, like Gecko) Version/3.1.2 Mobile Safari/525.20.1",
            "Samsung Galaxy S - Mozilla/5.0 (Linux; U; Android 2.1-update1; ru-ru; GT-I9000 Build/ECLAIR) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17",
            "Samsung Galaxy S Android 2.2 - Mozilla/5.0 (Linux; U; Android 2.2; ru-ru; GT-I9000 Build/FROYO) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
            "Samsung Galaxy Tab 10.1 Android 3.1 - Mozilla/5.0 (Linux; U; Android 3.1; en-us; GT-P7510 Build/HMJ37) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13",
            "Opera/9.80 (Macintosh; Intel Mac OS X 10.6.7; U; ru) Presto/2.8.131 Version/11.10",
            "Opera/9.80 (Windows NT 6.1; U; ru) Presto/2.8.131 Version/11.10",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_7; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.205 Safari/534.16" };

    /**
     * This object represents only the most basic contract for HTTP request execution.
     */
    private final HttpClient httpClient = new DefaultHttpClient();

    /**
     * Handler that encapsulates the process of generating a response object from a {@link org.apache.http.HttpResponse}
     */
    private final ParserResponseHandler responseHandler = new ParserResponseHandler();

    /**
     * host name
     */
    private final String host;


    public DataHost(String host) {
        this.host = host;
        Random rnd = new Random(System.currentTimeMillis());
        HttpProtocolParams.setUserAgent(httpClient.getParams(), USER_AGENTS[rnd.nextInt(USER_AGENTS.length)]);
    }

    protected abstract T parseResponse(InputStream content, Charset charset) throws IOException;
    protected abstract T emptyResponse(Charset charset);


    public T get(String path, String... params) {
        try {
            HttpGet get = new HttpGet(buildUri(host, path, params));
            if (logger.isInfoEnabled()) {
                logger.info("Request: " + get.getRequestLine());
            }

            T response = httpClient.execute(get, responseHandler);
            if (logger.isDebugEnabled()) {
                logger.debug("Response: " + response);
            }

            return response;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public T post(String path, HttpEntity entity, String... params) {
        try {
            HttpPost post = new HttpPost(buildUri(host, path, params));
            post.setEntity(entity);

            if (logger.isInfoEnabled()) {
                logger.info("Request: " + post.getRequestLine());
            }

            T response = httpClient.execute(post, responseHandler);
            if (logger.isDebugEnabled()) {
                logger.debug("Response: " + response);
            }

            return response;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static URI buildUri(String host, String path, String... params) {
        try {
            URIBuilder uriBuilder = new URIBuilder()
                    .setScheme("http")
                    .setHost(host)
                    .setPath(path);
            int index = 0;
            while (index < params.length) {
                uriBuilder.setParameter(params[index++], params[index++]);
            }
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    public UrlEncodedFormEntity buildFormEntity(String... params) {
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(params.length / 2);
        int index = 0;
        while (index < params.length) {
            parameters.add(new BasicNameValuePair(params[index++], params[index++]));
        }
        return new UrlEncodedFormEntity(parameters, Consts.UTF_8);
    }

    private class ParserResponseHandler implements ResponseHandler<T> {
        @Override
        public T handleResponse(HttpResponse response) throws IOException {
            try {
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() != HttpStatus.SC_OK || response.getEntity() == null) {
                    logger.error("Response [" + statusLine.getStatusCode() + "]: " + statusLine);
                    return emptyResponse(Consts.UTF_8);
                }

                return parseResponse(response.getEntity().getContent(), Consts.UTF_8);
            } finally {
                HttpClientUtils.closeQuietly(response);
            }
        }
    }

}
