<#assign controllerNameLower = controllerName?uncap_first>
<template>
	<div class="win" id="${pageName}">
		<div class="wm">
			<#if hasHouseTree == true>
			<div class="tree">
        		<ns-house-tree @handle-click="getData" @tree-item-click="treeItemClick"
                       :searchConditions='searchConditions'></ns-house-tree>
      		</div>
			</#if>
			<#if hasOrgTree == true>
			<div class="tree">
        		<ns-organize-tree title="缁勭粐鏋舵瀯" draggable showFunction :searchConditions='searchConditions'
                          @handle-click="getData" @tree-item-click="treeItemClick"></ns-organize-tree>
      		</div>
			</#if>
			<div class="table-body">
				<!--搜索部分-->
				 <ns-search :searchInput="searchInput" :funcId="funcId" :thlist="thlist" :searchData="searchData"
                   :searchConditions='searchConditions' :conditions="conditions" @query="getData"></ns-search>
				<!--功能按钮--======↓↓↓↓↓=====每个业务自己完成=====↓↓↓↓↓=====-->
				<div class="btns">
          			<ns-button
            			:roleInfo="{code: 'actionAdd${controllerName}Btn', name: '新增', nameEn: '', areaType: 'ACTION', index: '',btnType:'single'}"
            			type="primary" @click.native="add${controllerName}()"></ns-button>
		        </div>
				<!--功能按钮--======↑↑↑↑↑=====每个业务自己完成=====↑↑↑↑↑=====-->
				<!--表格部分-->
				<ns-grid :isThFirstShow="isThFirstShow" :searchConditions='searchConditions' :errorType="errorType"
                 :gridData="gridData" :thlist="thlist"
                 :funcId="funcId" :searchData="searchData" :conditions="conditions" :loading="loading"
                 @fnclick="gridfnclick"
                 @selection-change="selectionChange" @query="getData" :total="total">
        		</ns-grid>
				<!--表单部分-->
				<ns-dialog class="ns-dialog-col2" @close="dialogClose('${pageName}Form')" top="7%" :title="dialogTit" :visible.sync="dialogVisible" :modal-append-to-body="false">
          			<!--auto form-->
          			<ns-auto-form autoFormID="${pageName}Form"
                        :request-url="requestUrl"
                        :submit-url="submitUrl"
                        :query="{id:selectedGridNodeObj.id}"
                        cue-type="only-error"
                        @afterRequest="afterRequest">
                	</ns-auto-form>
          			<div slot="footer">
            			<ns-auto-form-operation :buttonInfo="buttonInfo" autoFormID="${pageName}Form"></ns-auto-form-operation>
          			</div>
        		</ns-dialog>
			</div>
		</div>
	</div>
</template>
<script>
	import {gridDataFetch, gridDataDetail, gridDataSubmit, gridDataDelete, gridDataDeleteBatch} from '../../api/${subpackage}/${pageName}'
	import ns from '../../utils/nsQuery/nsQuery'
	import * as store from '@/utils/nsQuery/nsStore'
	import EventHub from '@/utils/eventHub/eventHub.js'
	export default {
		name: '${pageName}',
		data() {
			return {
				funcId: store.funcId.get(), //funcId
        		//表单按钮信息
        		buttonInfo: [
          			{funcType: 'submit', style: 'primary', code: 'formConfirmBtn', name: '确定', areaType: 'FORM', btnType: 'single', event: this.autoFormSubmit},
          			{funcType: 'custom', style: '', code: 'formCancelBtn', name: '取消', areaType: 'FORM', btnType: 'single', event: this.autoFormCancel},
        		],
       			multipleSelection: [],
        		total: 0,
        		//搜索条件
        		searchConditions: {},
        		searchData: {}, //搜索的内容
        		//搜索输入框
        		searchInput:{name: '${controllerNameLower}Name', value: '', placeholder: '请输入${pageCnName}名称'},
        		//更多操作
        		dropData: [{label: '删除',command: 'delete${controllerName}'}],
        		//table部分
        		gridData: {data: []}, //列表数据
        		loading: {value: true},//数据加载loading
        		errorType: {value: ''},//列表数据提示类型
        		conditions: {conditionsData: [], conditionsMessage: []},//搜索条件存放处
        		isThFirstShow: {isbool: false},
        		thlist: {thlistDefault: []}, //表头
				<#if hasOrgTree == true>
        		selectedOrganization: {},//组织树选中的节点
				</#if>
        		selectedGridNodeObj: {},//选中的表格数据
       			permissionButtons: [],
        		//dialog-动态表单
        		dialogTit: '',
        		dialogVisible: {visible: false},
        		formData: {},
        		requestUrl: '',
        		submitUrl: '',
        		firstQueryFlag:0
        	}
		},
		//初始化获取数据
    	created() {
    		store.vm.set("${pageName}", this);
      		this.searchConditions = this.search.conditions;
    	},
		methods: {
			//提交按钮事件操作
      		autoFormSubmit(formName) {
        		this.autoForm.validate(formName).then(
          			params => {
            			console.log('提交的数据：');
            			console.log(JSON.stringify(params.formData.modelData, null, 4));
            			//submit request
            			this.autoForm.submit(this.submitUrl, params.formData.modelData, () => {
              			this.$message({message: '保存成功', type: 'success'});
              			this.$set(this.dialogVisible, 'visible', false);
              			this.getData();
            		})
          		}).catch((err) => {
            		console.log('表单验证失败');
          		})
      		},
       		// 取消按钮事件操作 )
      		autoFormCancel(formName) {
        		this.autoForm.resetForm(formName).then(
          			params => {
            		this.$set(this.dialogVisible, 'visible', false);
          		})
      		},
      		//自动表单请求获取数据之后 操作
      		afterRequest(vm, data) {
        		
      		},
      		//dialog相关操作
      		dialogClose(formName) { //dialog关闭清空表单内容
        		store.formController.delete(formName);
      		},
			//新增${pageCnName}
			add${controllerName}(){
				const _this = this;
				<#if hasOrgTree == true>
        		if (!_this.selectedOrganization.organizationId || _this.selectedOrganization.organizationId == 0) {
          			_this.$message({message: '请先选择组织',type: 'warning'});
        		} else {
          			_this.dialogTit = '新增${pageCnName}';
          			_this.requestUrl = '/${subpackage}/${controllerNameLower}/init-form';
          			_this.submitUrl = '/${subpackage}/${controllerNameLower}/add-${controllerNameLower}';
          			store.formController.set('${pageName}Form', {show: false});
          			store.formController.set('${pageName}Form', {show: true});
          			_this.$set(_this.dialogVisible, 'visible', true);
        		}
        		</#if>
        		<#if hasOrgTree == false>
        		_this.dialogTit = '新增${pageCnName}';
          		_this.requestUrl = '/${subpackage}/${controllerNameLower}/init-form';
          		_this.submitUrl = '/${subpackage}/${controllerNameLower}/add-${controllerNameLower}';
          		store.formController.set('${pageName}Form', {show: false});
          		store.formController.set('${pageName}Form', {show: true});
          		_this.$set(_this.dialogVisible, 'visible', true);
        		</#if>
      		},
			//表格操作
			gridfnclick(params, item) { //更多功能 params:操作 item:内容
				//=======================每个业务再加工处理============================
				const _this = this;
				_this.selectedGridNodeObj = item.row;
				switch(params.value){
					case 'gridEditBtn'://编辑
						_this.dialogTit = '编辑${pageCnName}';
						_this.requestUrl = '/${subpackage}/${controllerNameLower}/detail-${controllerNameLower}';
						_this.submitUrl = '/${subpackage}/${controllerNameLower}/edit-${controllerNameLower}';
						store.formController.set('${pageName}Form', {'show': true,'formOperateType':1});
						_this.$set(_this.dialogVisible, 'visible', true);
						break;
					case 'gridRemoveBtn'://删除
						_this.$confirm('确定删除?', {confirmButtonText: '确定', cancelButtonText: '取消', customClass:"el-message-box-oppositeBtns", type: 'warning'}).then(() => {
				        	gridDataDelete({
								id:_this.selectedGridNodeObj.id
							}).then(function () {
								_this.$message({
									message: '删除成功',
									type: 'success'
								});
								_this.getData();
							}).catch(function () {
								_this.$message({
									message: '删除失败，请联系管理员及时处理',
									type: 'error'
								});
							});
				        }).catch(() => {
				          this.$message({
				            type: 'info',
				            message: '删除已取消'
				          });
				        });
						break;
					default:
						break;
				};
				//=======================每个业务再加工处理============================
			},
			//grid勾选checkbox回调
      		selectionChange(selection) {
        		let arry = [];
        		for (let i = 0; i < selection.length; i++) {
          			arry.push(selection[i].houseId)
        		}
        		this.multipleSelection = arry;
     		},
     		//选择组织节点回调
      		treeItemClick(org) {
      			this.selectedOrganization = org;
      		},
			getData(condition) { //表数据查询
				const _this = this;
				this.loading.value = true;
        		if (condition) {
          			this.searchConditions.filterList = condition;
        		}
        		this.searchConditions.mainSearch = this.searchInput.value;
				gridDataFetch(_this.searchConditions).then(r => {
		          	_this.gridData.data = _this.search.makeData(_this.isThFirstShow.isbool, r, _this.thlist.thlistDefault);
		          	_this.errorType.value = _this.search.errorSelect(_this.searchConditions.filterList, r, _this.thlist.thlistDefault)
		         	_this.loading.value = false;
		        }).catch(r => {
		          	_this.gridData.data = [];
		         	 _this.errorType.value = 'error';
		          	_this.loading.value = false;
		        })
			},
			getDataFetch(){
				gridDataFetch(this.searchConditions).then(r => {
          		this.total = r.resultData.total; //总数据条数
          		this.gridData.data = this.search.makeData(this.isThFirstShow.isbool, r, this.thlist.thlistDefault);
          		this.errorType.value = this.search.errorSelect(this.searchConditions.filterList, r, this.thlist.thlistDefault)
          		this.loading.value = false;
          		EventHub.$emit('showGridButton', 'from-employeeList-management');
        		}).catch(r => {
          			this.gridData.data = [];
          			this.errorType.value = 'error';
          			this.loading.value = false;
        		})
			},
			//======↓↓↓↓↓↓=====更多功能,每个业务自己处理======↓↓↓↓↓======
			handleCommand(command) {
				const _this = this;
				if(command === 'delete${controllerName}') { //删除
				}
			}
			//======↑↑↑↑↑=====更多功能,每个业务自己处理======↑↑↑↑↑======
		}
		
	}
</script>
<#if hasHouseTree == false && hasOrgTree == false>
<style rel="stylesheet/less" lang="scss">
	#${pageName}.win {
    	.wm {
      		.table-body {
        	margin-left: 0;
      		}      
   	 	}
  	}
</style>
</#if>
