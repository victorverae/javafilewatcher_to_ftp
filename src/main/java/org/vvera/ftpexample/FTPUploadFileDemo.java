/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vvera.ftpexample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
/**
 *
 * @author DESA2017_2
 */
public class FTPUploadFileDemo {
    
    
    
    
    public static void main(String[] args) {
        
    }
    
    
    public void syncFolders(){
        List<String> folderList = new ArrayList<String>();
        folderList.add("folder");
        folderList.add("folder/subfolder");
    }
    
    public static void uploadFile(String strFile){
        String server = "192.168.3.1";
        int port = 21;
        String user = "ftp";
        String pass = "ftp";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File("C:/temp/"+strFile);
            String firstRemoteFile = strFile;
            
            InputStream inputStream = null;
            
            try{
                inputStream = new FileInputStream(firstLocalFile);
            }catch(Exception e){
                boolean doneMakeDirectory = ftpClient.makeDirectory(strFile);
            }
 
            //
//            boolean doneMakeDirectory = ftpClient.makeDirectory("testing/pass");
            //
            
            System.out.println("Start uploading first file");
            boolean done = true;
            if(inputStream != null){
                done = ftpClient.storeFile(firstRemoteFile, inputStream);
                inputStream.close();
            }
            
            if (done) {
                System.out.println("The file is uploaded successfully.");
            }
 
            // APPROACH #2: uploads second file using an OutputStream
//            File secondLocalFile = new File("C:/temp/grayscale.png");
//            String secondRemoteFile = "test/grayscale.png";
//            inputStream = new FileInputStream(secondLocalFile);
// 
//            System.out.println("Start uploading second file");
//            OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
//            byte[] bytesIn = new byte[4096];
//            int read = 0;
// 
//            while ((read = inputStream.read(bytesIn)) != -1) {
//                outputStream.write(bytesIn, 0, read);
//            }
//            inputStream.close();
//            outputStream.close();
 
//            boolean completed = ftpClient.completePendingCommand();
//            if (completed) {
//                System.out.println("The second file is uploaded successfully.");
//            }
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
}
