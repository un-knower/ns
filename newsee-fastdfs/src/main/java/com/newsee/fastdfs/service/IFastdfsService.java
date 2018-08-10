package com.newsee.fastdfs.service;

import java.util.List;

import com.newsee.fastdfs.entity.NsSystemFile;

public interface IFastdfsService {

    int addFile(NsSystemFile nsSystemFile);
    
    int addFiles(List<NsSystemFile> fileList) throws Exception;
    
    NsSystemFile detailById(Long fileId);
    
    List<NsSystemFile> listFileByType(Long enterpriseId,Long organizationId,Integer type);
    
    List<NsSystemFile> listFile(Long enterpriseId, String fileCode);
}
