/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vvera.ftpexample;

import java.io.IOException;
import java.nio.file.*;
/**
 *
 * @author DESA2017_2
 */

public class DirectoryWatcherExample {
    public static void main(String[] args) {
        try{
            WatchService watchService
              = FileSystems.getDefault().newWatchService();

            //Path path = Paths.get(System.getProperty("user.home"));
            Path path = Paths.get("C:/temp");

            path.register(
              watchService, 
                StandardWatchEventKinds.ENTRY_CREATE, 
                  StandardWatchEventKinds.ENTRY_DELETE, 
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.OVERFLOW);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println(
                      "Event kind:" + event.kind() 
                        + ". File affected: " + event.context() + "."
                        );
//                    if(event.kind().toString().equals("ENTRY_CREATE")){
                        FTPUploadFileDemo.uploadFile(event.context().toString());
//                    }
                }
                key.reset();
            }
        }catch(IOException ex){
            
        }catch(InterruptedException ex){
            
        }
    }
}
