/*
 * PB4NB
 *
 * Copyright (c) 2010 Jiri Pospisil (jiripospisil.info)
 *
 * This software is licensed under the New BSD License. See
 * license.txt in the root directory of this software package
 * for more details.
 *
 */
package info.jiripospisil.pb4nb.utils.request;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public class PastebinRequest {

    private static final Logger log = Logger.getLogger(PastebinRequest.class.
            getName());
    private static final String URL = "http://pastebin.com/api_public.php";
    private static final String CHARSET = "UTF-8";
    private final URLConnection urlConnection;
    private OutputStreamWriter output;
    private BufferedReader input;

    public PastebinRequest() throws IOException {
        urlConnection = new URL(URL).openConnection();
        urlConnection.setUseCaches(false);
        urlConnection.setDoOutput(true);
    }

    public String execute(Post post) throws Exception {
        try {
            sendRequest(post);
            return evaluateResponse(getResponse());
        } finally {
            closeStream(input);
            closeStream(output);
        }
    }

    private void sendRequest(Post post) throws UnsupportedEncodingException,
            IOException {
        output = new OutputStreamWriter(
                urlConnection.getOutputStream(), CHARSET);
        output.write(post.toString());
        output.flush();
    }

    private String getResponse() throws IOException {
        input = new BufferedReader(new InputStreamReader(
                urlConnection.getInputStream()));
        return input.readLine();
    }

    private String evaluateResponse(String response) throws Exception {
        if (response.startsWith("ERROR")) {
            throw new Exception(response);
        }
        return response;
    }

    private void closeStream(Closeable cls) {
        try {
            cls.close();
        } catch (Exception ex) {
            log.severe("Failed to close a stream.");
        }
    }
}
