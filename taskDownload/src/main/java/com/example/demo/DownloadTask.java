package com.example.demo;

import me.tongfei.progressbar.ProgressBar;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadTask implements Runnable {
    private String enlace = "";
    private String ruta = "";
    JProgressBar progressBar;
    
    public DownloadTask(String Enlace, String Ruta){
	    this.enlace=Enlace;
	    this.ruta=Ruta;

    }

    @Override
    public void run() {
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(enlace).openStream());
             FileOutputStream fileOS = new FileOutputStream(ruta);
             )
        {
            double percentCompleted = 1;
            URL url = new URL(enlace);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            int file_size = urlConnection.getContentLength() /1000000;
            String Ruta2=ruta.substring(ruta.lastIndexOf('/') + 1);
            ProgressBar pb = new ProgressBar(Ruta2,file_size);

            byte data[] = new byte[1024];

            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {

                fileOS.write(data, 0, byteContent);
                File file = new File(ruta);
                double fileSize = FileUtils.sizeOf(file)/1000000;

                percentCompleted = (fileSize * 100 / file_size);

                //System.out.println("Descargando "+ fileSize +" Completado "+ percentCompleted +"% "+ ruta +" ......." );
                //pb.stepTo((long) fileSize);
                pb.close();
            }
            System.out.println("Descarga de "+ ruta +"TERMINADA");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
