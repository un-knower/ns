package com.newsee.common.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.IOFileUploadException;
import org.apache.commons.fileupload.FileUploadBase.InvalidContentTypeException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 
 * ClassName: FileUpload <br/>
 * Reason: 文件处理类. <br/>
 *
 * @author sunwei
 * @version
 * @since JDK 1.6
 */
public class FileUpload {
    private static final Logger LOGGER = Logger.getLogger(FileUpload.class);
    
    private HttpServletRequest request;
    
    private MultipartHttpServletRequest multipartHttpServletRequest;
    
    /**默认目录 fileupload*/
    public static final String FIRST_DIR = "fileupload";
    /**默认组织编码 80800*/
    public static final Long GROUPID = 80800L;
    /**系统默认模块 system_default_file*/
    public static final String DEFAULT_MODEL = "system_default_file";
    
    /** 不限制文件上传总大小的 Size Max 常量 */
    private static final long LIMIT_SIZE_MAX = (1024<<10)*5;
    /** 不限制文件上传单个文件大小的 File Size Max 常量 */
    private static final long LIMIT_FILE_SIZE_MAX = (1024<<10)*4;
    /** 默认的写文件阀值 */
    private static final int DEFAULT_SIZE_THRESHOLD = DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD;
    /** 默认文件目录  */
    private String defaultSaveDir;     
    
    /** 文件上传失败的原因（文件上传失败时使用） */
    private Throwable cause;
    
    private String savePath;
    private String tempSavePath;
    /** 文件上传的总文件大小限制 */
    private long sizeMax = LIMIT_SIZE_MAX;
    /** 文件上传的单个文件大小限制 */
    private long fileSizeMax = LIMIT_FILE_SIZE_MAX;
    /** 可接受的上传文件类型集合 */
    private String[] acceptTypes = {"TXT", "PDF", "DOC","JPEG", "JPG", "PNG", "XLSX", "XLS"};
//    private String[] imageTypes = {"JPEG", "JPG", "PNG"};
    /** 输入流 */
    private InputStream in;
    /** 输出流 */
    private FileOutputStream out;
    
    /** 普通参数域 */
    private Map<String, Object> paramsMap = new HashMap<String, Object>();
    /** 文件域 */
    private Map<String,Object> fileFieldMap = new HashMap<String, Object>(); 
    
    public FileUpload(HttpServletRequest request, String saveFileDir) {
        this.request = request;
        this.defaultSaveDir = saveFileDir;
    }
    
    public FileUpload(MultipartHttpServletRequest request, String saveFileDir) {
        this.multipartHttpServletRequest = request;
        this.defaultSaveDir = saveFileDir;
    }    
    /**
     * 采用默认，一个文件上传
     * 开启文件上传
     */
    public Map<String, String> upload(Boolean isBoot) {
        if (isBoot) {
            return this.uploadFile(FileUpload.FIRST_DIR, FileUpload.GROUPID, FileUpload.DEFAULT_MODEL);
        }else{
            return this.upload(FileUpload.FIRST_DIR, FileUpload.GROUPID, FileUpload.DEFAULT_MODEL);
        }
    	
    }
    
    public Map<String, String> uploadFile(String firstDir, Long groupId, String model){
        if (firstDir == null || "".equals(firstDir)) firstDir = FileUpload.FIRST_DIR;
        if (groupId == null || groupId == 0) groupId = FileUpload.GROUPID;
        if (model == null || "".equals(model)) model = FileUpload.DEFAULT_MODEL;        
        Map<String, String> resultMap = new HashMap<String, String>();
        this.resetFilePath(firstDir, groupId, model);
        //消息提示
        result = Result.SUCCESS;
        List<MultipartFile> fileList = null;
        MultiValueMap<String, MultipartFile> multiFileMap = multipartHttpServletRequest.getMultiFileMap();
        for (Iterator<Entry<String, List<MultipartFile>>> iterator = multiFileMap.entrySet().iterator(); iterator.hasNext();) {
        	Entry<String, List<MultipartFile>> entry = iterator.next();
        	fileList = entry.getValue();
		}
        if (!CollectionUtils.isEmpty(fileList)) {
        	for (MultipartFile file : fileList) {
                resultMap.put("fileSize", String.valueOf(file.getSize())); //文件大小
                // 获取文件名
                String fileName = file.getOriginalFilename();
                LOGGER.info("上传的文件名为：" + fileName);
                //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
                // 获取文件的后缀名
                String suffixName = fileName.substring(fileName.lastIndexOf(".")+1); 
                suffixName = suffixName == null ? "" : suffixName;
                Pattern p = Pattern.compile(suffixName.toUpperCase());  
                Matcher m = p.matcher(Arrays.toString(acceptTypes));                    
                if (!m.find()) {
                    result = Result.INVALID_FILE_TYPE;
                }else {
                    LOGGER.info("上传的后缀名为：" + suffixName);
                    resultMap.put("OldFileName", fileName);
                    //处理文件名，添加时间戳
                    String tempName = DateUtils.getUserDate("yyyyMMddhh");
                    tempName = tempName+"_"+System.currentTimeMillis();                         
                    fileName = tempName + "." +suffixName;
                    resultMap.put("fileName", fileName);
                    //得到文件保存目录
                    savePath = tempSavePath + File.separator + fileName; 
                    // 文件上传后的路径
                    String filePath = defaultSaveDir+tempSavePath;
                    File dest = new File(filePath + File.separator + fileName );
                    // 检测是否存在目录
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    try {
                        file.transferTo(dest);
                        resultMap.put("savePath", savePath);
                    } catch (Exception e) {
                        LOGGER.error("FileUpload >>> uploadFile() >>> 文件上传异常", e);
                        cause = e;
                        if (e instanceof FileSizeLimitExceededException)
                            result = Result.FILE_SIZE_EXCEEDED;
                        else if (e instanceof SizeLimitExceededException)
                            result = Result.SIZE_EXCEEDED;
                        else if (e instanceof InvalidContentTypeException)
                            result = Result.INVALID_CONTENT_TYPE;
                        else if (e instanceof IOFileUploadException)
                            result = Result.FILE_UPLOAD_IO_EXCEPTION;
                        else
                            result = Result.OTHER_PARSE_REQUEST_EXCEPTION;      
                    }
                }                       
			}
        }
        
        resultMap.put("message", result.name());
        return resultMap;
    }
    
    /**
     * 开启文件上传
     * @param firstDir 文件保存根目录 -- 可选
     * @param groupId 组织机构ID -- 可选
     * @param model 模块编码 -- 可选
     * @return
     */
    public Map<String, String> upload(String firstDir, Long groupId, String model) {
    	if (firstDir == null || "".equals(firstDir)) firstDir = FileUpload.FIRST_DIR;
    	if (groupId == null || groupId == 0) groupId = FileUpload.GROUPID;
    	if (model == null || "".equals(model)) model = FileUpload.DEFAULT_MODEL;    	
        Map<String, String> resultMap = new HashMap<String, String>();
        this.resetFilePath(firstDir, groupId, model);
        //消息提示
        result = Result.SUCCESS;
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            factory.setSizeThreshold(DEFAULT_SIZE_THRESHOLD);//如果不指定，那么缓冲区的大小默认是10KB
            //设置上传时生成的临时文件的保存目录
            factory.setRepository(new File(defaultSaveDir+tempSavePath));
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8"); 
            //设置上传单个文件的大小的最大值
            upload.setFileSizeMax(fileSizeMax);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和
            upload.setSizeMax(sizeMax);
            
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return null;
            }
            //监听文件上传进度
            upload.setProgressListener(new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    LOGGER.info("FileUpload >>> upload() >>> 文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);                     
                }
            });
            
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()) {                    
                    getContentParams(item);
                }else {//如果fileitem中封装的是上传文件
                	resultMap.put("fileSize", String.valueOf(item.getSize())); //文件大小
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println("====上传的文件===== " + filename);
                    if(CommonUtils.isObjectEmpty(filename)){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //得到上传文件的扩展名
                    String suffix = filename.substring(filename.lastIndexOf(".")+1); 
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("====上传的文件的扩展名是：==== "+suffix);
                    suffix = suffix == null ? "" : suffix;
                    Pattern p = Pattern.compile(suffix.toUpperCase());  
                    Matcher m = p.matcher(Arrays.toString(acceptTypes));                    
                    if (!m.find()) {
                        result = Result.INVALID_FILE_TYPE;
                    } else {
                        //获取item中的上传文件的输入流
                        in = item.getInputStream();
                        resultMap.put("OldFileName", filename);
                        //处理文件名，添加时间戳
                        String tempName = DateUtils.getUserDate("yyyyMMddhh"); //filename.substring(0, filename.lastIndexOf("."));
                        tempName = tempName+"_"+System.currentTimeMillis();                        	
                        filename = tempName + "." +suffix;
                        
                        resultMap.put("fileName", filename);
                        //得到文件保存目录
                        savePath = tempSavePath + File.separator + filename; 
                        //创建一个文件输出流
                        out = new FileOutputStream(defaultSaveDir+savePath);
                        //创建一个缓冲区
                        byte buffer[] = new byte[1024];
                        //判断输入流中的数据是否已经读完的标识
                        int len = 0;
                        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                        while((len=in.read(buffer)) > 0) {
                            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录当中
                            out.write(buffer, 0, len);
                        }                    
                        //删除处理文件上传时生成的临时文件
                        item.delete();
                        fileFieldMap.put(filename, savePath);
                        resultMap.put("savePath", savePath);
                    }                   
                }
            }
        } catch (Exception e) {
            LOGGER.error("FileUpload >>> upload() >>> 文件上传异常", e);
            cause = e;
            if (e instanceof FileSizeLimitExceededException)
                result = Result.FILE_SIZE_EXCEEDED;
            else if (e instanceof SizeLimitExceededException)
                result = Result.SIZE_EXCEEDED;
            else if (e instanceof InvalidContentTypeException)
                result = Result.INVALID_CONTENT_TYPE;
            else if (e instanceof IOFileUploadException)
                result = Result.FILE_UPLOAD_IO_EXCEPTION;
            else
                result = Result.OTHER_PARSE_REQUEST_EXCEPTION;            
        } finally {
            try {
              //关闭输入流
                if (in != null) {
                    in.close();
                }
              //关闭输出流
                if (out != null) {
                    out.close();
                }
                resultMap.put("message", result.name());
                if (cause != null) {
                    resultMap.put("exception", cause.getMessage());
					
				}
            } catch (IOException e) {
                LOGGER.error("FileUpload >>> upload() >>> 文件上传IO异常", e);
                cause = e;
                result = Result.OTHER_PARSE_REQUEST_EXCEPTION;   
            }
        }
                
        return resultMap;
    }
    
    /**
     * 开启文件上传
     * @param firstDir 文件保存根目录 -- 可选
     * @param groupId 组织机构ID -- 可选
     * @param model 模块编码 -- 可选
     * @return
     */
    public List<Map<String, String>> uploadMoreFile(String firstDir, Long groupId, String model) {
    	if (firstDir == null || "".equals(firstDir)) firstDir = FileUpload.FIRST_DIR;
    	if (groupId == null || groupId == 0) groupId = FileUpload.GROUPID;
    	if (model == null || "".equals(model)) model = FileUpload.DEFAULT_MODEL;  
    	
    	List<Map<String, String>> fileList = new ArrayList<Map<String,String>>();
        Map<String, String> resultMap = null;
        this.resetFilePath(firstDir, groupId, model);
        //消息提示
        result = Result.SUCCESS;
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            factory.setSizeThreshold(DEFAULT_SIZE_THRESHOLD);//如果不指定，那么缓冲区的大小默认是10KB
            //设置上传时生成的临时文件的保存目录
            factory.setRepository(new File(defaultSaveDir+tempSavePath));
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8"); 
            //设置上传单个文件的大小的最大值
            upload.setFileSizeMax(fileSizeMax);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和
            upload.setSizeMax(sizeMax);
            
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return null;
            }
            //监听文件上传进度
            upload.setProgressListener(new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    LOGGER.info("FileUpload >>> uploadMoreFile() >>> 文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);                     
                }
            });
            
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list) {
            	resultMap = new HashMap<String, String>();
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()) {                    
                    getContentParams(item);
                }else {//如果fileitem中封装的是上传文件
                	resultMap.put("fileSize", String.valueOf(item.getSize())); //文件大小
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println("====上传的文件===== " + filename);
                    if(CommonUtils.isObjectEmpty(filename)){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //得到上传文件的扩展名
                    String suffix = filename.substring(filename.lastIndexOf(".")+1); 
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("====上传的文件的扩展名是：==== "+suffix);
                    suffix = suffix == null ? "" : suffix;
                    Pattern p = Pattern.compile(suffix.toUpperCase());  
                    Matcher m = p.matcher(Arrays.toString(acceptTypes));                    
                    if (!m.find()) {
                        result = Result.INVALID_FILE_TYPE;
                    } else {
                        //获取item中的上传文件的输入流
                        in = item.getInputStream();
                        resultMap.put("OldFileName", filename);
                        //处理文件名，添加时间戳
                        String tempName = DateUtils.getUserDate("yyyyMMddhh"); //filename.substring(0, filename.lastIndexOf("."));
                        tempName = tempName+"_"+System.currentTimeMillis();                        	
                        filename = tempName + "." +suffix;
                        
                        resultMap.put("fileName", filename);
                        //得到文件保存目录
                        savePath = tempSavePath + File.separator + filename; 
                        //创建一个文件输出流
                        out = new FileOutputStream(defaultSaveDir+savePath);
                        //创建一个缓冲区
                        byte buffer[] = new byte[1024];
                        //判断输入流中的数据是否已经读完的标识
                        int len = 0;
                        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                        while((len=in.read(buffer)) > 0) {
                            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录当中
                            out.write(buffer, 0, len);
                        }                    
                        //删除处理文件上传时生成的临时文件
                        item.delete();
                        fileFieldMap.put(filename, savePath);
                        resultMap.put("savePath", savePath);
                    }                   
                }
                fileList.add(resultMap);
            }
        } catch (Exception e) {
            LOGGER.error("FileUpload >>> uploadMoreFile() >>> 文件上传异常", e);
            cause = e;
            if (e instanceof FileSizeLimitExceededException)
                result = Result.FILE_SIZE_EXCEEDED;
            else if (e instanceof SizeLimitExceededException)
                result = Result.SIZE_EXCEEDED;
            else if (e instanceof InvalidContentTypeException)
                result = Result.INVALID_CONTENT_TYPE;
            else if (e instanceof IOFileUploadException)
                result = Result.FILE_UPLOAD_IO_EXCEPTION;
            else
                result = Result.OTHER_PARSE_REQUEST_EXCEPTION;            
        } finally {
            try {
              //关闭输入流
                if (in != null) {
                    in.close();
                }
              //关闭输出流
                if (out != null) {
                    out.close();
                }                
                //resultMap.put("message", result.name());
                if (cause != null) {
                    //resultMap.put("exception", cause.getMessage());					
				}
            } catch (IOException e) {
                LOGGER.error("FileUpload >>> upload() >>> 文件上传IO异常", e);
                cause = e;
                result = Result.OTHER_PARSE_REQUEST_EXCEPTION;   
            }
        }
                
        return fileList;
    }
    
    /**
     * 
     * getContentParams:过滤一般表单参数. <br/>
     * @author sunwei
     * @param item
     * @throws UnsupportedEncodingException 
     * @since JDK 1.6
     */
    private void getContentParams(FileItem item) throws UnsupportedEncodingException {
        String name = item.getFieldName();
        //解决普通输入项的数据的中文乱码问题
        String value = item.getString("UTF-8");
        paramsMap.put(name, value);
        System.out.println("====普通数据项===== "+name + "=" + value);
    }
    
    /**
     * @Description: 将base64编码字符串转换为图片
     * @param imgStr base64编码字符串
     * @return
    */
    public Map<String, String> generateBase64Image(String imgStr) {
    	if (imgStr == null) 
    		return null;
    	Map<String, String> map = new HashMap<String, String>(4);
    	String tempPath = resetFilePath(FileUpload.FIRST_DIR, FileUpload.GROUPID, FileUpload.DEFAULT_MODEL);
    	String fileName = new StringBuilder(DateUtils.getUserDate("yyyyMMddhh"))
        		.append("_").append(System.currentTimeMillis()).append(".jpg").toString();
    	map.put("fileName", fileName);
        //得到文件保存目录
        String savePath = tempPath + File.separator + fileName; 
        map.put("savePath", savePath.replaceAll("\\\\", "/"));
        // 文件上传后的路径
        String filePath = defaultSaveDir+savePath;
    	Base64 decoder = new Base64();
    	try {
    		// 解密
    		byte[] b = decoder.decode(imgStr.getBytes("UTF-8"));
    		map.put("fileSize", String.valueOf(b.length));
    		// 处理数据
    		for (int i = 0; i < b.length; ++i) {
    			if (b[i] < 0) {
    				b[i] += 256;
    			}
    		}
    		OutputStream out = new FileOutputStream(filePath);
    		out.write(b);
    		out.flush();
    		out.close();
    		map.put("message", Result.SUCCESS.name());
    		return map;
    	} catch (Exception e) {
    		e.printStackTrace();
    		map.put("message", Result.FILE_UPLOAD_IO_EXCEPTION.name());
    		return map;
    	}
	} 
    
    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        return Base64.encodeBase64String(data);
    }

    /** 处理文件目录 */
    public String resetFilePath(String firstDir,long orgId,String model) {
        File file = new File(defaultSaveDir);
        fileExist(file);
        //创建子路径
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH)+1;
//        int day = now.get(Calendar.DAY_OF_MONTH);  
//        int hour = now.get(Calendar.HOUR_OF_DAY);
        StringBuilder builder = new StringBuilder().append(firstDir);
        tempSavePath = builder.toString();
        fileExist(new File(defaultSaveDir+tempSavePath));
        tempSavePath = builder.append(File.separator).append(orgId).toString();
        fileExist(new File(defaultSaveDir+tempSavePath));
        tempSavePath = builder.append(File.separator).append(model).toString();
        fileExist(new File(defaultSaveDir+tempSavePath));   
        tempSavePath = builder.append(File.separator).append(year).toString();
        fileExist(new File(defaultSaveDir+tempSavePath));
        tempSavePath = builder.append(File.separator).append(month).toString();
        fileExist(new File(defaultSaveDir+tempSavePath));
     
        return tempSavePath;
    }
    
    /** 创建文件目录 */
    private void fileExist(File file) {        
        if ( !file.exists() ) {
            //创建文件目录
            file.mkdir();            
        }        
    }
    
    public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public Map<String, Object> getFileFieldMap() {
		return fileFieldMap;
	}

	/** 文件上传结果枚举值 */
    public static enum Result {
        /** 成功 */
        SUCCESS,
        /** 失败：文件总大小超过限制 */
        SIZE_EXCEEDED,
        /** 失败：单个文件大小超过限制 */
        FILE_SIZE_EXCEEDED,
        /** 失败：请求表单类型不正确 */
        INVALID_CONTENT_TYPE,
        /** 失败：文件上传 IO 错误 */
        FILE_UPLOAD_IO_EXCEPTION,
        /** 失败：解析上传请求其他异常 */
        OTHER_PARSE_REQUEST_EXCEPTION,
        /** 失败：文件类型不正确 */
        INVALID_FILE_TYPE,
        /** 失败：文件写入失败 */
        WRITE_FILE_FAIL,
        /** 失败：文件的保存路径不正确 */
        INVALID_SAVE_PATH,
        /** 非表单数据 */
        INVALID_DATA;
    } 
    
    /**创建线程池*/
    private static ExecutorService executors = Executors.newFixedThreadPool(6);
    
    /**
     * 默认压缩图片
     * @param data
     * @param filePath
     * @return
     * @throws IOException 
     */
    public void scaleImagePath(final byte[] data, final String filePath) {
    	executors.execute(new Runnable() {
			@Override
			public void run() {
				String suffix = filePath.substring(filePath.lastIndexOf("."));
				String temp = filePath.substring(0, filePath.lastIndexOf("."));
				temp = filePath + "-px" + suffix;
				FileOutputStream fileOut = null;
				try {
					fileOut = new FileOutputStream(temp);
					fileOut.write(data);
					fileOut.flush();
					fileOut.close();
				} catch (IOException e) {
					 throw new RuntimeException(e);
				} finally {
					try {
						if (fileOut != null) {
							fileOut.close();
						}
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}
		});
    	executors.shutdown();
    }
    
    /**
	 * 等比例缩放图片
	 * @param input
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	public byte[] scaleImage(InputStream input, int width, int height) throws IOException {  
	    BufferedImage buffered_oldImage = ImageIO.read(input);  
	    return scaleImage(buffered_oldImage, width, height);
	}
	
	/**
	 * 等比例缩放图片
	 * @param data
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	public byte[] scaleImage(byte[] data, int width, int height) throws IOException {  
	    BufferedImage buffered_oldImage = ImageIO.read(new ByteArrayInputStream(data));  
	    return scaleImage(buffered_oldImage, width, height);
	}
	
	/**
	 * 等比例缩放图片
	 * @param file
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	public byte[] scaleImage(File file, int width, int height) throws IOException {  
	    BufferedImage buffered_oldImage = ImageIO.read(file);  
	    return scaleImage(buffered_oldImage, width, height);
	}
	
	private byte[] scaleImage(BufferedImage buffered_oldImage, int width, int height) throws IOException {  
	    int imageOldWidth = buffered_oldImage.getWidth();  
	    int imageOldHeight = buffered_oldImage.getHeight();  
	    double scale_x = (double) width / imageOldWidth;  
	    double scale_y = (double) height / imageOldHeight;  
	    double scale_xy = Math.min(scale_x, scale_y);  
	    int imageNewWidth = (int) (imageOldWidth * scale_xy);  
	    int imageNewHeight = (int) (imageOldHeight * scale_xy);  
	    BufferedImage buffered_newImage = new BufferedImage(imageNewWidth, imageNewHeight, BufferedImage.TYPE_INT_RGB);  
	    buffered_newImage.getGraphics().drawImage(buffered_oldImage.getScaledInstance(imageNewWidth, imageNewHeight, BufferedImage.SCALE_SMOOTH), 0, 0, null);  
	    buffered_newImage.getGraphics().dispose();  
	    ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();  
	    ImageIO.write(buffered_newImage, "jpeg", outPutStream);  
	    return outPutStream.toByteArray();  
	}
	
	/**
	 * 模拟表单http上传图片
	 * 
	 * @param urlStr
	 * @param textMap
	 * @param fileMap
	 * @param contentType
	 *            没有传入文件类型默认采用application/octet-stream
	 *            contentType非空采用filename匹配默认的图片类型
	 * @return 返回response数据
	 */
	@SuppressWarnings("rawtypes")
	public static String formUpload(String urlPath, Map<String, String> textMap, Map<String, String> fileMap,
			String contentType) {
		String res = "";
		HttpURLConnection conn = null;
		// boundary就是request头和上传文件内容的分隔符
		String BOUNDARY = "---------------------------123821742118716";
		try {
			URL url = new URL(urlPath);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// text
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}
			// file
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();

					// 没有传入文件类型，同时根据文件获取不到类型，默认采用application/octet-stream
					contentType = new MimetypesFileTypeMap().getContentType(file);
					// contentType非空采用filename匹配默认的图片类型
					if (!"".equals(contentType)) {
						if (filename.endsWith(".png")) {
							contentType = "image/png";
						} else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")
								|| filename.endsWith(".jpe")) {
							contentType = "image/jpeg";
						} else if (filename.endsWith(".gif")) {
							contentType = "image/gif";
						} else if (filename.endsWith(".ico")) {
							contentType = "image/image/x-icon";
						}
					}
					if (contentType == null || "".equals(contentType)) {
						contentType = "application/octet-stream";
					}
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
					out.write(strBuf.toString().getBytes("UTF-8"));
					DataInputStream in = new DataInputStream(new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");
			out.write(endData);
			out.flush();
			out.close();
			// 读取返回数据
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			LOGGER.error("发送POST请求出错。" + urlPath + "\n" + e.getMessage());
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		LOGGER.info("上传物业管理系统文件： "+res);
		return res;
	}
	
	private Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
    
}
