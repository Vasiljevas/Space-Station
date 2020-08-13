package com.company;

import java.io.*;
import java.net.URL;

import static com.company.ReaderWriter.OutputText;


public class PhotoDownloaderRunnable implements Runnable{
    private BufferedWriter writer;

    public PhotoDownloaderRunnable(Carrier ship, BufferedWriter writer, double cost) {
        ship.coins -= cost;
        this.writer = writer;
    }
    @Override
    public void run() {
        try {
            long start_time = System.currentTimeMillis();
            //work
            URL url = new URL("https://i.imgur.com/Ka4mk2q.jpg");
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf)))
            {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            ReaderWriter rw = new ReaderWriter();
            rw.saveImage(out.toByteArray());
            //work finish
            long end_time = System.currentTimeMillis();

            OutputText("Image downloaded from somewhere very far away \n" +
                    "It took " + (end_time-start_time)+ " milliseconds to download the photo \n"
                    , writer);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("The process to download a Cat image is over");
        }

    }
}
