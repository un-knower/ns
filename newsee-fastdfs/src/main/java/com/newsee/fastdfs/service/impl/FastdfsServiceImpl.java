package com.newsee.fastdfs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.newsee.fastdfs.dao.NsSystemFileMapper;
import com.newsee.fastdfs.entity.NsSystemFile;
import com.newsee.fastdfs.service.IFastdfsService;

@Service
public class FastdfsServiceImpl implements IFastdfsService {

    @Autowired
    private NsSystemFileMapper fileMapper;
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    @Override
    public int addFile(NsSystemFile nsSystemFile) {
        int index = fileMapper.insertSelective(nsSystemFile);
        return index;
    }
    @Override
    public NsSystemFile detailById(Long fileId) {
        NsSystemFile nsSystemFile = fileMapper.selectByPrimaryKey(fileId);
        return nsSystemFile;
    }
    @Override
    public List<NsSystemFile> listFileByType(Long enterpriseId,Long organizationId,Integer type) {
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", enterpriseId);
        map.put("organizationId", organizationId);
        map.put("type", type);
        return fileMapper.listFileByType(map);
    }
    
	@Override
	public int addFiles(List<NsSystemFile> fileList) throws Exception {
		int res = 0;
		if (!CollectionUtils.isEmpty(fileList)) {
			for (NsSystemFile nsSystemFile : fileList) {
				res +=fileMapper.insertSelective(nsSystemFile);
			}
		}
		
		return res;
	}
	@Override
	public List<NsSystemFile> listFile(Long enterpriseId, String fileCode) {
		Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", enterpriseId);         
        map.put("fileCode", fileCode);
        return fileMapper.selectFile(map);
	}

	
	
}
