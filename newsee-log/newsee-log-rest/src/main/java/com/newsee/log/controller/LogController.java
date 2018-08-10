package com.newsee.log.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.newsee.common.constant.MongoCollectionsConstants;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.log.entity.ImportLogEntity;
import com.newsee.log.vo.SearchConditionVo;

import io.swagger.annotations.ApiOperation;

/**
 * 处理mangodb中日志的查询和插入请求
 * @author xiaosisi add on 2017/08/17
 *
 */
@RestController
@RequestMapping("/log")
@ResponseBody
public class LogController {

    @Autowired
    private MongoTemplate mongoTemplate;
    
    /**
     * 根据数据id和menuid查询业务处理日志，展现在页面中
     * @param bussinessLog
     * @return List<BussinessLogEntity>业务日志列表
     * @author xiaosisi add on 2017/08/23
     */
    @ApiOperation("查询导入日志")
    @PostMapping("/list-import-log")
    public RestResult<List<ImportLogEntity>> listImportLog(@RequestBody SearchConditionVo searchConditionVo){
        List<Criteria> criteriaList = new ArrayList<>();
        criteriaList.add(new Criteria("userId").is(LoginDataHelper.getUserId()));
        if (StringUtils.hasLength(searchConditionVo.getPrecinctName())) {
            criteriaList.add(new Criteria("precinctName").regex(searchConditionVo.getPrecinctName()));
        }
        if (StringUtils.hasLength(searchConditionVo.getHouseName())) {
            criteriaList.add(new Criteria("houseName").regex(searchConditionVo.getHouseName()));
        }
        if (searchConditionVo.getStartTime() != null) {
            criteriaList.add(new Criteria("importDate").gt(searchConditionVo.getStartTime()));
        }
        if (searchConditionVo.getEndTime() != null) {
            criteriaList.add(new Criteria("importDate").lte(searchConditionVo.getEndTime()));
        }
        if (StringUtils.hasLength(searchConditionVo.getImportName())) {
            criteriaList.add(new Criteria("importName").regex(searchConditionVo.getImportName()));
        }
        Criteria[] searchCriteriaArray = new Criteria[criteriaList.size()];
        for (int i = 0; i < criteriaList.size(); i++) {
            searchCriteriaArray[i] = criteriaList.get(i);
        }
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (!CollectionUtils.isEmpty(criteriaList)) {
            criteria.andOperator(searchCriteriaArray);
            query.addCriteria(criteria);
        }
        if (!Objects.isNull(searchConditionVo.getPageSize())) {
            query.skip((searchConditionVo.getPageNum() - 1) * searchConditionVo.getPageSize());
            query.limit(searchConditionVo.getPageSize());
        }
        Long total = mongoTemplate.count(query, MongoCollectionsConstants.COL_IMPORT_LOG_NAME);
        List<ImportLogEntity> logs = null;
        if (!CommonUtils.isObjectEmpty(total)) {
            query.with(new Sort(Sort.Direction.DESC,"importDate"));
            logs = mongoTemplate.find(query, ImportLogEntity.class, MongoCollectionsConstants.COL_IMPORT_LOG_NAME);
        }
        RestResult<List<ImportLogEntity>> restResult = new RestResult<>(logs);
        restResult.setTotal(total);
        restResult.setPageNum(searchConditionVo.getPageNum());
        restResult.setPageSize(searchConditionVo.getPageSize());
        return restResult;
    }
}
