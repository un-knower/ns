package com.newsee.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;


/**文件下载，删除*/
public class DownloadFile {
    private static final Logger LOGGER = Logger.getLogger(DownloadFile.class);
    
    /**默认保存目录*/
    private String defaultSaveDir;
    private Long fileLength;
    
    public DownloadFile( String saveFileDir) {
    	this.defaultSaveDir = saveFileDir;
    }
    
    /**
     * 
     * deleteFile:文件删除. <br/>
     * @author sunwei
     * @param filePath 
     * @return boolean
     * @since JDK 1.6
     * date 2016年7月22日下午3:45:34
     */
    public boolean deleteFile(String filePath) {
    	filePath = defaultSaveDir + filePath;
        boolean bool = false;
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            bool = file.delete();            
        }
        if(bool) {
            LOGGER.info("删除的文件是 "+filePath);
        } else {
            LOGGER.info("删除的文件不存在 ，"+filePath);
        }
        return bool;
    }
    
    /**
     * 
     * downLoad:文件下载. <br/>
     * 注意，调用此方法后，要关闭流.<br/>
     * @author sunwei
     * @param filePath 
     * @return InputStream
     * @since JDK 1.6
     * date 2016年7月22日下午3:43:43
     */
    public InputStream downLoad(String filePath) { 
        //读取要下载的文件，保存到文件输入流
        InputStream in = null;
        //通过文件名找出文件的所在目录
        String path = defaultSaveDir + filePath;
        //得到要下载的文件
        File file = new File(path);
        //如果文件不存在
        if(!file.exists()) {
            LOGGER.info("FileUpload >>> downLoad() >>> 您要下载的资源已被删除！！");
        } else {
        	this.fileLength = file.length();
            try {
                in = new BufferedInputStream(new FileInputStream(path));
            } catch (FileNotFoundException e) {
                LOGGER.error("FileUpload >>> downLoad() >>> 下载的资源文件异常!!",e);
            }
        }        
        return in;
    }

	public Long getFileLength() {
		return fileLength;
	}

	public void setFileLength(Long fileLength) {
		this.fileLength = fileLength;
	}
    
}
