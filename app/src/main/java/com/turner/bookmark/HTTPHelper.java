package com.turner.bookmark;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPHelper {

    public String readHTTP(String url) {
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder data = new StringBuilder();
            String line;

            do {
                line = reader.readLine();
                if (line != null) {
                    data.append(line);
                }
            }
            while (line != null);

            return data.toString();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    public void downloadCover(String url, String destination) {
        try {
            URL urlObj = new URL(url);
            InputStream input = urlObj.openStream();
            OutputStream output = new FileOutputStream(destination);

            byte[] b = new byte[2048];
            int length;

            while ((length = input.read(b)) != -1) {
                output.write(b, 0, length);
            }

            input.close();
            output.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
