package com.newsee.soss.config;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

/**
 * 文件上传进度
 * @author Administrator
 *
 */
@Component
public class UploadProgressListener implements ProgressListener{
	public static  final String UPLOAD_PROGRESS_STATUS = "uploadProgressStatus";
	private HttpSession session;
	
	public void setSession(HttpSession session) {
        this.session=session;  
        ProgressEntity status = new ProgressEntity();  //未进行并发，多线程处理
        session.setAttribute(UPLOAD_PROGRESS_STATUS, status); 
    } 

	/**
	 * pBytesRead 到目前为止读取文件的比特数 pContentLength 文件总大小 pItems 目前正在读取第几个文件 
	 */
	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		ProgressEntity status = (ProgressEntity) session.getAttribute(UPLOAD_PROGRESS_STATUS);  
        status.setpBytesRead(pBytesRead);  
        status.setpContentLength(pContentLength);  
        status.setpItems(pItems);  
	}
	
	public class ProgressEntity {
		private long pBytesRead = 0L;   //到目前为止读取文件的比特数   
	    private long pContentLength = 0L;    //文件总大小   
	    private int pItems;                //目前正在读取第几个文件
	    
		public long getpBytesRead() {
			return pBytesRead;
		}
		public void setpBytesRead(long pBytesRead) {
			this.pBytesRead = pBytesRead;
		}
		public long getpContentLength() {
			return pContentLength;
		}
		public void setpContentLength(long pContentLength) {
			this.pContentLength = pContentLength;
		}
		public int getpItems() {
			return pItems;
		}
		public void setpItems(int pItems) {
			this.pItems = pItems;
		}
	    
	}

}
