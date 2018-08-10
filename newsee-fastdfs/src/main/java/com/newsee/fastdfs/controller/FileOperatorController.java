package com.newsee.fastdfs.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.github.tobato.fastdfs.domain.StorePath;
import com.newsee.common.constant.Constants;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.vo.FileVo;
import com.newsee.fastdfs.entity.NsSystemFile;
import com.newsee.fastdfs.service.IFastdfsService;
import com.newsee.fastdfs.util.FastdfsUtil;
import com.newsee.redis.util.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * @ClassName FileOperatorController
 * @Description: 文件操作rest接口 
 * @author 胡乾亮
 * @date 2017年10月31日 下午5:21:30
 */

@RestController
@RequestMapping("/fastdfs")
@ResponseBody
@Api(tags = {"com.newsee.fastdfs.controller.FileOperatorController"}, description = "文件上传下载  REST API，包含自定义表单列表页面的所有操作方法。")
public class FileOperatorController {
    
    @Autowired
    FastdfsUtil fastdfsUtil;
    @Autowired
    private IFastdfsService fastdfsService;
    @Value("${file.server.url}")
    private String fileServerUrl;
    
    @Autowired
    RedisUtil redisUtil;
    
    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/uploadFile")
    public RestResult<List<FileVo>> uploadFile(MultipartHttpServletRequest request) throws Exception{
        System.out.println(request.getCharacterEncoding());
        request.setCharacterEncoding("UTF-8");
        MultipartFile file = request.getFile("file");
        BizException.isNull(file, "文件为null");
        RestResult<List<FileVo>> restResult = null;
        List<FileVo> resultList = new ArrayList<>();
        Long userId = LoginDataHelper.getUserId();
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
//        String fileUrl =  request.getServletContext().getRealPath("/")+file.getOriginalFilename();
        StorePath storePath = fastdfsUtil.uploadFile(file);
        if (storePath != null) {
            //返回上传路径
            String fileUrl = fileServerUrl + Constants.SEPARATOR_PATH + storePath.getPath();
            FileVo fileVo = new FileVo();
            fileVo.setFileName(file.getOriginalFilename());
            fileVo.setFileSize(file.getSize());
            fileVo.setFileUrl(fileUrl);
            resultList.add(fileVo);
            restResult = new RestResult<List<FileVo>>(resultList);
            //保存文件信息
            NsSystemFile nsSystemFile = new NsSystemFile();
            nsSystemFile.setCreateUserId(userId);
            nsSystemFile.setUpdateUserId(userId);
            nsSystemFile.setOrganizationId(organizationId);
            nsSystemFile.setEnterpriseId(enterpriseId);
            nsSystemFile.setFileName(URLDecoder.decode(file.getOriginalFilename(),"UTF-8"));
            nsSystemFile.setFileGroup(storePath.getGroup());
            nsSystemFile.setPath(storePath.getPath());
            nsSystemFile.setFileFullPath(fileUrl);
            fastdfsService.addFile(nsSystemFile);
        }else {
            restResult = new RestResult<>(ResultCodeEnum.SERVER_ERROR.CODE, "上传失败");
        }
        return restResult;
    }
    
    @ApiOperation(value = "上传文件(name='file')-不保存")
    @PostMapping(value = "/uploadFile-common")
    public RestResult<List<FileVo>> uploadCommonFile(MultipartFile[] file) {
        BizException.isNull(file, "文件为null");
        RestResult<List<FileVo>> restResult = null;
        List<FileVo> resultList = new ArrayList<>();
        FileVo fileVo = null;
        for (MultipartFile tempFile : file) {
        	StorePath storePath = fastdfsUtil.uploadFile(tempFile);
        	if (storePath != null) {
        		String path = storePath.getPath();
        		//返回上传路径
        		fileVo = new FileVo();
        		fileVo.setFileName(tempFile.getOriginalFilename());
        		fileVo.setFileSize(tempFile.getSize());
        		fileVo.setFileUrl(this.fileServerUrl + Constants.SEPARATOR_PATH + path);
        		fileVo.setPath(path);
        		resultList.add(fileVo);
        		//保存文件信息
        		NsSystemFile nsSystemFile = new NsSystemFile();
        		nsSystemFile.setFileName(tempFile.getOriginalFilename());
        		nsSystemFile.setFileGroup(storePath.getGroup());
        		nsSystemFile.setPath(path);        		 
        		//文件数据放入缓存，有效时间12小时
        		redisUtil.setStringValue(path, JSON.toJSONString(nsSystemFile), 12*3600);
        	}else {
        		BizException.fail(ResultCodeEnum.FAILURE, ResultCodeEnum.FAILURE.DESC);
        	}
		}
        restResult = new RestResult<List<FileVo>>(resultList);
        
        return restResult;
    }
    
    @ApiOperation(value = "保存文件")
    @RequestMapping(value = "/save-file", method= RequestMethod.POST)
    public RestResult<?> saveFile(@RequestBody FileVo fileVo) {
    	RestResult<?> restResult = null;
    	//在缓存中获取文件数据
    	if (!StringUtils.isEmpty(fileVo.getPath())) {
    		List<NsSystemFile> fileList = new ArrayList<>();
    		NsSystemFile nsSystemFile = null;
			Long userId = LoginDataHelper.getUserId();
			Long organizationId = LoginDataHelper.getCompanyLevelOrgId();			 
			String[] paths = fileVo.getPath().split(",");
			String[] fullPaths = fileVo.getFileUrl().split(",");
			for (int i = 0; i < paths.length; i++) {
				String obj = redisUtil.getStringValue(paths[i]);
				if (!StringUtils.isEmpty(obj)) {					
					nsSystemFile = JSON.parseObject(obj, NsSystemFile.class);
					nsSystemFile.setCreateUserId(userId);
					nsSystemFile.setUpdateUserId(userId);
					nsSystemFile.setOrganizationId(organizationId);
					nsSystemFile.setEnterpriseId(fileVo.getEnterpriseId());
					nsSystemFile.setPath(paths[i]);
					nsSystemFile.setFileCode(fileVo.getFileCode());
					nsSystemFile.setFileFullPath(fullPaths[i]);
					fileList.add(nsSystemFile);
					//移除缓存数据
		    		redisUtil.delete(paths[i]);
				} else {
					restResult = new RestResult<>(ResultCodeEnum.DATA_NOT_EXIST.CODE, ResultCodeEnum.DATA_NOT_EXIST.DESC);
					break;
				}
			}
			//保存文件数据
			try {
				fastdfsService.addFiles(fileList);
				restResult = new RestResult<>(ResultCodeEnum.SUCCESS.CODE, ResultCodeEnum.SUCCESS.DESC);
			} catch (Exception e) {
				BizException.fail(ResultCodeEnum.FAILURE, e.getMessage());
			}
		} else {
    		restResult = new RestResult<>(ResultCodeEnum.DATA_NOT_EXIST.CODE, ResultCodeEnum.DATA_NOT_EXIST.DESC);
    	}
        
    	return restResult;
    }
    
    @ApiOperation(value = "下载文件")
    @GetMapping(value = "/downloadFile")
    public RestResult<Integer> downloadFile(@RequestParam("fileId") Long fileId,HttpServletRequest request, HttpServletResponse response) throws IOException{
        NsSystemFile nsSystemFile = fastdfsService.detailById(fileId);
        byte[] fileByte = fastdfsUtil.downloadFile(nsSystemFile);
        // 清空response
        response.reset();
        // 设置response的Header
        String fileName = nsSystemFile.getFileName();
        //下载显示的文件名，解决中文名称乱码问题  
        response.setHeader("downloadFileName", URLEncoder.encode(fileName,"UTF-8"));
        response.setHeader("Access-Control-Expose-Headers", "downloadFileName, Content-Disposition");
        response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName,"UTF-8") + "filename=" + fileName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        OutputStream os = null;
//        FileOutputStream fileOutputStream = null;
        try {
//            fileOutputStream = new FileOutputStream("F://"+fileName); 
//            fileOutputStream.write(fileByte);
//            fileOutputStream.flush();
           os = response.getOutputStream();
           os.write(fileByte);
           os.flush();
        } catch (IOException e) {
           e.printStackTrace();
        } finally {
            os.close();
//            fileOutputStream.close();
        }
        return new RestResult<>();
    }
    @ApiOperation(value = "获取文件列表")
    @PostMapping(value = "/list-file")
    public RestResult<List<NsSystemFile>> listFileByType(@ApiParam("文件类型,10 房产模板 20 客户模板 30 其他") @RequestParam("type") Integer type) throws IOException{
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        List<NsSystemFile> nsSystemFile = fastdfsService.listFileByType(enterpriseId,organizationId,type);
        return new RestResult<List<NsSystemFile>>(nsSystemFile);
    }    
    
    @ApiOperation(value = "根据文件编码，获取文件")
    @GetMapping(value="/findFileByCode")
    public List<FileVo> findFileList(@RequestParam(name="fileCode",required=false) String fileCode, @RequestParam(name="enterpriseId") Long enterpriseId) {
    	if (StringUtils.isEmpty(fileCode)) {
    		return null;
    	}
    	List<NsSystemFile> list = fastdfsService.listFile(enterpriseId, fileCode);
    	List<FileVo> voList = null;
    	if (!CollectionUtils.isEmpty(list)) {
    		FileVo vo = null;
    		voList = new ArrayList<>(list.size());
    		for (NsSystemFile file : list) {
				vo = new FileVo();
				BeanUtils.copyProperties(file, vo);
				vo.setFileUrl(file.getFileFullPath());
    			voList.add(vo);
			}
    	}
    	
    	return voList;
    }
    
//    @ApiOperation(value = "生成二维码并上传")
//    @PostMapping(value = "/upload-file-for-qrcode")
//    public RestResult<String> uploadFileForQrcode(String content){
//        BizException.isNull(content, "文件为null");
//        RestResult<String> restResult = null;
//        //上传
//        CommonUtils.createQrCode(content, 300, "jpg");
//        StorePath storePath = fastdfsUtil.uploadFile(file);
//        if (storePath != null) {
//            System.out.println(storePath.getGroup());
//            System.out.println(storePath.getPath());
//
//            String fileUrl = fileServerUrl + Constants.SEPARATOR_PATH + storePath.getPath();
//            restResult = new RestResult<String>(fileUrl);
//        }else {
//            restResult = new RestResult<>(ResultCodeEnum.SERVER_ERROR.CODE, "上传失败");
//        }
//        return restResult;
//    }
}
