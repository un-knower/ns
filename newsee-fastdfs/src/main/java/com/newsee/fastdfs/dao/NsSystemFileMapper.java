package com.newsee.fastdfs.dao;

import java.util.List;
import java.util.Map;

import com.newsee.fastdfs.entity.NsSystemFile;

public interface NsSystemFileMapper {
    int deleteByPrimaryKey(Long fileId);

    int insert(NsSystemFile record);

    int insertSelective(NsSystemFile record);

    NsSystemFile selectByPrimaryKey(Long fileId);

    int updateByPrimaryKeySelective(NsSystemFile record);

    int updateByPrimaryKey(NsSystemFile record);
    
    List<NsSystemFile> listFileByType(Map<String, Object> map);
    
    /**
     * 获取文件信息
     * @param enterpriseId 必须
     * @param organizationId
     * @param type 文件类型,0 房产模板 1 客户模板 2 其他 3 soss
     * @param fileCode
     * @return
     */
    List<NsSystemFile> selectFile(Map<String, Object> map);
}