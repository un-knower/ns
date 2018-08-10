package com.newsee.fastdfs.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.newsee.fastdfs.entity.NsSystemFile;


@Component
public class FastdfsUtil {
	
	@Autowired
	private FastFileStorageClient fastFileStorageClient;

	/**
	 * @Description: 上传文件
	 * @author: 胡乾亮
	 * @date: 2017年8月23日下午2:28:56
	 */
	public StorePath uploadFile(String fileUrl){
		StorePath storePath = null;
		try {
			File file = new File("F:\\"+fileUrl);
			InputStream inputStream = new FileInputStream(file);
			long fileSize = file.length();
			String fileName = file.getName();
			String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
			Set<MateData> metaDataSet = new HashSet<MateData>();
			metaDataSet.add(new MateData(fileName, fileExtName));
			storePath = fastFileStorageClient.uploadFile(inputStream, fileSize, fileExtName, metaDataSet);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return storePath;
	}
	
    public StorePath uploadFile(MultipartFile file){
         StorePath storePath = null;
         try {
             if (file != null) {
                 InputStream inputStream = file.getInputStream();
                 long fileSize = file.getSize();
                 String fileName = file.getOriginalFilename();
                 String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
                 Set<MateData> metaDataSet = new HashSet<MateData>();
                 metaDataSet.add(new MateData(fileName, fileExtName));
                 storePath = fastFileStorageClient.uploadFile(inputStream, fileSize, fileExtName, metaDataSet);                    
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
          return storePath;
     }
    
	/**
	 * @Description: 下载文件
	 * @author: 胡乾亮
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @date: 2017年8月24日上午8:47:56
	 */
	public byte[] downloadFile(NsSystemFile nsSystemFile) throws FileNotFoundException, IOException{
	    if (nsSystemFile != null) {
	        DownloadCallback<byte[]> callback = new DownloadCallback<byte[]>() {

	            @Override
	            public byte[] recv(InputStream ins) throws IOException {
	                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
	                byte[] buffer= new byte[1024];
	                int len = 0;
	                while((len=ins.read(buffer,0,1024))>0){
	                    swapStream.write(buffer, 0, len);
	                }
	                byte[] byteArray = swapStream.toByteArray();  
	                return byteArray;
	            }
	        };

//	        HttpHeaders headers = new HttpHeaders();
	        fastFileStorageClient.getMetadata(nsSystemFile.getFileGroup(), nsSystemFile.getPath());
	        byte[] b = fastFileStorageClient.downloadFile(nsSystemFile.getFileGroup(), nsSystemFile.getPath(), callback);
//	        String fileName = nsSystemFile.getFileName();
//	        System.out.println(fileName);
//	        headers.add("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8")));
//	        headers.setContentDispositionFormData("attachment",  new String(fileName.getBytes("UTF-8")));
//	        String extensionName = path.substring(path.indexOf(".")+1);
//	        if (Arrays.asList(extensionPic).contains(extensionName.toUpperCase())) {
//	            headers.setContentType(MediaType.app);
//	        }else if (Arrays.asList(extensionXls).contains(extensionName.toUpperCase())) {
//	            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//	            headers.add("Content-Type", "application/octet-stream;charset=UTF-8");
//	        }
//	         InputStream inputStream = new ByteArrayInputStream(b);
//	        IOUtils.write(b, new FileOutputStream("F:/" + new String(fileName.getBytes("UTF-8"))));
	        return b;
        }
	    return null;
	}
	
}
