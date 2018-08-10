package com.newsee.generator;


import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;

import com.mysql.jdbc.StringUtils;
import com.newsee.generator.Generator.GeneratorModel;
import com.newsee.generator.jepf.JeCoreFuncinfo;
import com.newsee.generator.jepf.JeCoreMenu;
import com.newsee.generator.jepf.JeCoreResourcebutton;
import com.newsee.generator.jepf.JeCoreResourcecolumn;
import com.newsee.generator.jepf.JeCoreResourcefield;
import com.newsee.generator.jepf.JeCoreResourcetable;
import com.newsee.generator.jepf.JeCoreTablecolumn;
import com.newsee.generator.jepf.JeCoreTableindex;
import com.newsee.generator.jepf.JeCoreTablekey;
import com.newsee.generator.jepf.NsCoreDictionary;
import com.newsee.generator.jepf.NsCoreDictionarygroup;
import com.newsee.generator.jepf.NsCoreDictionaryitem;
import com.newsee.generator.provider.db.sql.model.Sql;
import com.newsee.generator.provider.db.table.TableFactory;
import com.newsee.generator.provider.db.table.model.Column;
import com.newsee.generator.provider.db.table.model.Table;
import com.newsee.generator.provider.java.model.JavaClass;
import com.newsee.generator.util.BeanHelper;
import com.newsee.generator.util.GLogger;
import com.newsee.generator.util.GeneratorException;
import com.newsee.generator.util.StringHelper;
import com.newsee.generator.util.typemapping.DatabaseTypeUtils;


public class GeneratorFacade {
	public Generator g = new Generator();
	public GeneratorFacade(){
		g.setOutRootDir(GeneratorProperties.getProperty("outRoot"));
	}
	
	public static void printAllTableNames() throws Exception {
		PrintUtils.printAllTableNames(TableFactory.getInstance().getAllTables());
	}
	
	public void deleteOutRootDir() throws IOException {
		g.deleteOutRootDir();
	}
	
	public void generateByMap(Map<String, Object> map,String templateRootDir) throws Exception {
		new ProcessUtils().processByMap(map, templateRootDir,false);
	}

	public void deleteByMap(Map<String, Object> map,String templateRootDir) throws Exception {
		new ProcessUtils().processByMap(map, templateRootDir,true);
	}
	
	public void generateByAllTable(String templateRootDir) throws Exception {
		new ProcessUtils().processByAllTable(templateRootDir,false);
	}

    public void generateByAllTable(String templateRootDir, String wildcard) throws Exception {
        new ProcessUtils().processByAllTable(templateRootDir,false, wildcard);
    }
	
	public void deleteByAllTable(String templateRootDir) throws Exception {
		new ProcessUtils().processByAllTable(templateRootDir,true);		
	}
	
    public void generateByTable(String tableName,String templateRootDir) throws Exception {
    	new ProcessUtils().processByTable(tableName,templateRootDir,false);
	}

    public void deleteByTable(String tableName,String templateRootDir) throws Exception {
    	new ProcessUtils().processByTable(tableName,templateRootDir,true);
	}
    
	public void generateByClass(@SuppressWarnings("rawtypes") Class clazz,String templateRootDir) throws Exception {
		new ProcessUtils().processByClass(clazz, templateRootDir,false);
	}

	public void deleteByClass(@SuppressWarnings("rawtypes") Class clazz,String templateRootDir) throws Exception {
		new ProcessUtils().processByClass(clazz, templateRootDir,true);
	}
	
	public void generateBySql(Sql sql,String templateRootDir) throws Exception {
		new ProcessUtils().processBySql(sql,templateRootDir,false);
	}

	public void deleteBySql(Sql sql,String templateRootDir) throws Exception {
		new ProcessUtils().processBySql(sql,templateRootDir,true);
	}
	
    private Generator getGenerator(String templateRootDir) {
        g.setTemplateRootDir(new File(templateRootDir).getAbsoluteFile());
        return g;
    }
    
    /** 生成器的上下文，存放的变量将可以在模板中引用 */
    public static class GeneratorContext {
        static ThreadLocal<Map<String, Object>> context = new ThreadLocal<Map<String, Object>>();
        public static void clear() {
            Map<String, Object> m = context.get();
            if(m != null) m.clear();
        }
        public static Map<String, Object> getContext() {
            Map<String, Object> map = context.get();
            if(map == null) {
                setContext(new HashMap<String, Object>());
            }
            return context.get();
        }
        public static void setContext(Map<String, Object> map) {
            context.set(map);
        }
        public static void put(String key,Object value) {
            getContext().put(key, value);
        }
    }
    
    public class ProcessUtils {
    	public void processByMap(Map<String, Object> params, String templateRootDir,boolean isDelete) throws Exception, FileNotFoundException {
			Generator g = getGenerator(templateRootDir);
			GeneratorModel m = GeneratorModelUtils.newFromMap(params);
			try {
				if(isDelete)
					g.deleteBy(m.templateModel, m.filePathModel);
				else
					g.generateBy(m.templateModel, m.filePathModel);
			}catch(GeneratorException ge) {
				PrintUtils.printExceptionsSumary(ge.getMessage(),getGenerator(templateRootDir).getOutRootDir(),ge.getExceptions());
			}
    	}
    	
    	public void processBySql(Sql sql,String templateRootDir,boolean isDelete) throws Exception {
    		Generator g = getGenerator(templateRootDir);
    		GeneratorModel m = GeneratorModelUtils.newFromSql(sql);
    		PrintUtils.printBeginProcess("sql:"+sql.getSourceSql(),isDelete);
    		try {
    			if(isDelete) {
    				g.deleteBy(m.templateModel, m.filePathModel);
    			}else {
    				g.generateBy(m.templateModel, m.filePathModel);
    			}
    		}catch(GeneratorException ge) {
    			PrintUtils.printExceptionsSumary(ge.getMessage(),getGenerator(templateRootDir).getOutRootDir(),ge.getExceptions());
    		}
    	}   
    	
    	public void processByClass(@SuppressWarnings("rawtypes") Class clazz, String templateRootDir,boolean isDelete) throws Exception, FileNotFoundException {
			Generator g = getGenerator(templateRootDir);
			GeneratorModel m = GeneratorModelUtils.newFromClass(clazz);
			PrintUtils.printBeginProcess("JavaClass:"+clazz.getSimpleName(),isDelete);
			try {
				if(isDelete)
					g.deleteBy(m.templateModel, m.filePathModel);
				else
					g.generateBy(m.templateModel, m.filePathModel);
			}catch(GeneratorException ge) {
				PrintUtils.printExceptionsSumary(ge.getMessage(),getGenerator(templateRootDir).getOutRootDir(),ge.getExceptions());
			}
    	}
    	
        public void processByTable(String tableName,String templateRootDir,boolean isDelete) throws Exception {
        	if("*".equals(tableName)) {
        		generateByAllTable(templateRootDir);
        		return;
        	} else if(null != tableName && tableName.contains("*")) {
                generateByAllTable(templateRootDir, tableName);
                return;
            }
    		Generator g = getGenerator(templateRootDir);
    		Table table = TableFactory.getInstance().getTable(tableName);
    		try {
    			processByTable(g,table,isDelete);
    		}catch(GeneratorException ge) {
    			PrintUtils.printExceptionsSumary(ge.getMessage(),getGenerator(templateRootDir).getOutRootDir(),ge.getExceptions());
    		}
    	}    
        
		public void processByAllTable(String templateRootDir,boolean isDelete) throws Exception {
			List<Table> tables = TableFactory.getInstance().getAllTables();
			List<Exception> exceptions = new ArrayList<Exception>();
			for(int i = 0; i < tables.size(); i++ ) {
				try {
					processByTable(getGenerator(templateRootDir),tables.get(i),isDelete);
				}catch(GeneratorException ge) {
					exceptions.addAll(ge.getExceptions());
				}
			}
			//==========add xiaosisi 2017/10/17=============
			//按照页面生成controller和service层
			String pages = GeneratorProperties.getProperty("pages");
			String rootFuncid = GeneratorProperties.getProperty("rootFuncid");
			String rootMenuid = GeneratorProperties.getProperty("rootMenuid");
			String subSystemName = GeneratorProperties.getProperty("subpackage");
			//String subSystemFuncid = UUID.randomUUID().toString().replaceAll("-", "");
			//String subSystemMenuId = UUID.randomUUID().toString().replaceAll("-", "");
			String subSystemFuncid ="newsee-" + subSystemName +"-root-funcid";
			String subSystemMenuId = "newsee-" + subSystemName +"-root-menuid";
			String resourceTableRootId = "newsee-" + subSystemName +"-root-tableid";
            ArrayList<String> pagesList = new ArrayList<String>();
            if(pages.indexOf(",")>0){
            	pagesList.addAll(Arrays.asList(pages.split(",")));
            }else{
            	pagesList.add(pages);
            }
            Integer index = 1;
            List<GeneratorModel> models = new ArrayList<GeneratorModel>();
            for(String page : pagesList){
            	GeneratorModel m = processByPage(page, getGenerator(templateRootDir), tables, isDelete, rootFuncid, subSystemFuncid, rootMenuid, subSystemMenuId, index, resourceTableRootId);
            	index = index ++;
            	models.add(m);
            }
			//重新组合models，用于生成sql文件
        	Map<String, Object> filePathModelSql = models.get(0).getFilePathModel();
        	Map<String, Object> templateModelSqltmp = new HashMap<String, Object>(); 
            for(GeneratorModel gm : models){
            	String pageName = gm.getTemplateModel().get("pageName").toString();
            	templateModelSqltmp.put(pageName, gm.getTemplateModel());
            }
            Map<String, Object> templateModelSql = new HashMap<String, Object>(); 
            templateModelSql.put("allpage", templateModelSqltmp);
            GeneratorModel sqlGm = new GeneratorModel(templateModelSql, filePathModelSql);
            processBySql(sqlGm, getGenerator(templateRootDir), isDelete);
			//==========add xiaosisi 2017/10/17=============
			PrintUtils.printExceptionsSumary("",getGenerator(templateRootDir).getOutRootDir(),exceptions);
		}
        //
        public void processByAllTable(String templateRootDir,boolean isDelete, String wildcard) throws Exception {
            List<Table> tables = TableFactory.getInstance().getAllTables();
            List<Exception> exceptions = new ArrayList<Exception>();
            for(int i = 0; i < tables.size(); i++ ) {
                try {
                    Table table = tables.get(i);
                    String sqlName = table.getSqlName();
                    if(null != sqlName && matchWildCard(sqlName, wildcard)){
                        processByTable(getGenerator(templateRootDir),table,isDelete);
                    } else {
                        GLogger.println("wildcard[" + wildcard + "] Not match: " + sqlName);
                    }
                }catch(GeneratorException ge) {
                    exceptions.addAll(ge.getExceptions());
                }
            }
            PrintUtils.printExceptionsSumary("",getGenerator(templateRootDir).getOutRootDir(),exceptions);
        }
        // 是否匹配通配符
        private boolean matchWildCard(String sqlName, String wildcard){
            Pattern regex = Pattern.compile("[^*]+|(\\*)");
            Matcher m = regex.matcher(wildcard);
            StringBuffer b= new StringBuffer();
            while (m.find()) {
                if(m.group(1) != null) m.appendReplacement(b, ".*");
                else m.appendReplacement(b, "\\\\Q" + m.group(0) + "\\\\E");
            }
            m.appendTail(b);
            String targetRegexStr = b.toString();
            // 使用 replaced
            return sqlName.matches(targetRegexStr);
        }
		
		public void processByTable(Generator g, Table table,boolean isDelete) throws Exception {
            // 屏蔽符合某些规则的表名
            // 不在底层进行拦截
            //
            String sqlName = table.getSqlName();
            String prefixs = GeneratorProperties.getProperty("skipTablePrefixes", "");
            for(String prefix : prefixs.split(",")) {
                if(null != prefix && !prefix.trim().isEmpty() && sqlName.startsWith(prefix)) {
                    GLogger.println("[skip]		matches prefix: " + prefix + "; skipTable: " + sqlName);
                    return ;
                }
            }
            //
	        GeneratorModel m = GeneratorModelUtils.newFromTable(table);
	        PrintUtils.printBeginProcess(table.getSqlName()+" => "+table.getClassName(),isDelete);
	        if(isDelete)
	        	g.deleteBy(m.templateModel,m.filePathModel);
	        else 
	        	g.generateBy(m.templateModel,m.filePathModel);
	    }   
		
		//===== add by xiaosisi on 2017/10/17======
		public GeneratorModel processByPage(String page, Generator g, List<Table> tables, boolean isDelete,
				String rootFuncid, String subSystemFuncid,
				String rootMenuid, String subSystemMenuid, Integer index, String resourceTableRootId) throws Exception {
			//组合按照页面生成服务和controller所需要的变量
	        GeneratorModel m = GeneratorModelUtils.newFromPage(page, tables, rootFuncid, subSystemFuncid, rootMenuid, subSystemMenuid, index, resourceTableRootId);
	        if(isDelete){
	        	g.deleteBy(m.templateModel,m.filePathModel);
	        }
	        else{
	        	g.generateByPage(m.templateModel, m.filePathModel);
	        } 
	        return m;
	    }
		
		public GeneratorModel processBySql( GeneratorModel m, Generator g,boolean isDelete) throws Exception {
			//组合按照页面生成服务和controller所需要的变量
	        if(isDelete){
	        	g.deleteBy(m.templateModel,m.filePathModel);
	        }
	        else{
	        	g.generateBySql(m.templateModel, m.filePathModel);
	        } 
	        return m;
	    }
		//===== add by xiaosisi on 2017/10/17======
    }
	
    @SuppressWarnings("all")
	public static class GeneratorModelUtils {
		
		public static GeneratorModel newFromTable(Table table) {
			Map templateModel = new HashMap();
			templateModel.put("table", table);
			setShareVars(templateModel);
			
			Map filePathModel = new HashMap();
			setShareVars(filePathModel);
			filePathModel.putAll(BeanHelper.describe(table));
			return new GeneratorModel(templateModel,filePathModel);
		}
		
		//======== add xiaosisi on 2017/10/17 ======== 
		public static GeneratorModel newFromPage(String page, List<Table> tables, String rootFuncid, String subSystemFuncid, String rootMenuid, String subSystemMenuid, Integer index, String resourceTableRootId) {
			Map templateModel = new HashMap();
			setShareVars(templateModel);
			//设置pageName
			templateModel.put("pageName", page);
			templateModel.put("tables", tables);
			Map filePathModel = new HashMap();
			setShareVars(filePathModel);
			filePathModel.put("pageName", page);
			//获取该页面操作的表名
			String tablesString = GeneratorProperties.getProperty(page+".table");
			List<String> tablesName = new ArrayList<String>();
			if(tablesString.indexOf(",") > 0){
				tablesName.addAll(Arrays.asList(tablesString.split(",")));
			}else{
				tablesName.add(tablesString);
			}
			List<String> tablesHumpName = new ArrayList<String>();
			//controller使用的表对象筛选
			List<Table> pageTables = new ArrayList<Table>();
			for(int i = 0; i< tablesName.size(); i++){
				tablesHumpName.add(StringHelper.underlineToHump(tablesName.get(i)));
				for(int j= 0; j < tables.size(); j++){
					if(tablesName.get(i).equals(tables.get(j).getSqlName())){
						pageTables.add(tables.get(j));
					}
				}
			}
			//根据页面使用的表生成表单字段和列头字段------------------add by xiaosisi 2018/01/15-----------------------↓↓↓↓↓
			//获取页面中文名称
			String pageCnName = GeneratorProperties.getProperty(page+".name");
			templateModel.put("pageCnName", pageCnName);
			String tableCode = GeneratorProperties.getProperty("subpackage")+"-"+ page.toLowerCase();
			templateModel.put("tableCode", tableCode);
			//根目录func信息和menu信息页面的func信息和menu信息
			templateModel.put("rootfuncinfo", generateRootFuncinfo(rootFuncid, subSystemFuncid));
			//String funcId = UUID.randomUUID().toString().replaceAll("-", "");
			String funcId = subSystemFuncid + "-"+ page.toLowerCase();
			templateModel.put("pagefuncinfo",generateFuncinfo(page, pageCnName, rootFuncid,subSystemFuncid,tableCode,index,funcId));
			templateModel.put("rootMenu", generateRootMenu(rootMenuid, subSystemMenuid));
			templateModel.put("pagemenu", generateMenu(page,pageCnName,rootMenuid,subSystemMenuid, index));
			//String funcId = GeneratorProperties.getProperty(page+".jepfFuncId");
			Map<String, Object> result = generatorCloums(pageTables, funcId);
			templateModel.put("jepfColums", result.get("jepfColums"));
			templateModel.put("jepfFields", result.get("jepfFields"));
			templateModel.put("voColumns", result.get("voColumns"));
			templateModel.put("dictGroup",result.get("dictGroup"));
			templateModel.put("dicts",result.get("dicts"));
			templateModel.put("dictItems",result.get("dictItems"));
			templateModel.put("systemColums", result.get("systemColums"));
			templateModel.put("systemFields", result.get("systemFields"));
			//处理资源表相关信息
			JeCoreResourcetable moduleResourceTable = resourceModuleTableHanlder(page, resourceTableRootId);
			templateModel.put("moduleResourceTable", moduleResourceTable);
		
			Map<String, Object> resource = resourceTableHanlder(tableCode,page , (List<Column>)result.get("voColumns"), resourceTableRootId);
			templateModel.put("resourceTable", resource.get("resourceTable"));
			templateModel.put("tableKey",resource.get("tableKey"));
			templateModel.put("tableIndex",resource.get("tableIndex"));
			templateModel.put("resourceTalbeColumns", resource.get("resourceTalbeColumns"));
			//根据页面使用的表生成表单字段和列头字段------------------add by xiaosisi 2018/01/15-----------------------↑↑↑↑↑
			templateModel.put("tablesName", tablesHumpName);
			templateModel.put("buttons", generateButtons(page, funcId));
			
			//是否有组织树
			boolean hasOrgTree = StringHelper.stringToBoolean(GeneratorProperties.getProperty(page+".hasOrgTree"));
			templateModel.put("hasOrgTree", hasOrgTree);
			//是否有房产树
			boolean hasHouseTree = StringHelper.stringToBoolean(GeneratorProperties.getProperty(page+".hasHouseTree"));
			templateModel.put("hasHouseTree", hasHouseTree);
			
			//获取页面的字段字段
			//获取controller前缀和service前缀
			String prifix = page.substring(0, page.length()-4);
			String ctName = prifix.substring(0, 1).toUpperCase() +  prifix.substring(1, prifix.length());
			templateModel.put("controllerName", ctName);
			filePathModel.put("controllerName", ctName);
			return new GeneratorModel(templateModel,filePathModel);
		}
		
		public static JeCoreFuncinfo generateRootFuncinfo(String rootFuncid,String subSystemFuncid){
			JeCoreFuncinfo funcinfo = new JeCoreFuncinfo();
			funcinfo.setFuncinfoFormlabelwidth(0);
			funcinfo.setFuncinfoFormlabelwidthEn(0);
			funcinfo.setFuncinfoFunccode("ns-"+GeneratorProperties.getProperty("subpackage"));
			funcinfo.setFuncinfoFuncname(GeneratorProperties.getProperty("subpackageCh"));
			funcinfo.setFuncinfoIconcls("JE_MODULE");
			funcinfo.setFuncinfoNodeinfotype("MODEL");
			funcinfo.setJeCoreFuncinfoId(subSystemFuncid);
			funcinfo.setSyAudflag("NOSTATUS");
			funcinfo.setSyCreateorg("ADMINISTRATOR");
			funcinfo.setSyCreateorgname("系统管理部");
			funcinfo.setSyCreateuser("admin");
			funcinfo.setSyCreateusername("超级管理员");
			funcinfo.setSyCreatetime("2018-02-23 15:00:00");
			funcinfo.setSyNodetype("GENERAL");
			funcinfo.setSyOrderindex(5);
			funcinfo.setSyParent(rootFuncid);
			funcinfo.setSyLayer(2);
			funcinfo.setSyParentpath("/ROOT/"+ rootFuncid);
			funcinfo.setSyPath("/ROOT/"+rootFuncid+"/"+subSystemFuncid);
			funcinfo.setSyStatus("1");
			funcinfo.setSyJecore("0");
			funcinfo.setSyJesys("0");
			funcinfo.setFuncinfoGridtimeout(0);
			return funcinfo;
		}
		
		public static List<JeCoreResourcebutton> generateButtons(String page, String funcid){
			List<JeCoreResourcebutton> buttons = new ArrayList<JeCoreResourcebutton>();
			//新增，编辑，删除，确认，取消按钮生成
			JeCoreResourcebutton addButton = generateCommonButton(page, "actionAddBtn", "新增", "ACTION", funcid);
			JeCoreResourcebutton editButton = generateCommonButton(page, "gridEditBtn", "编辑", "GRID", funcid);
			JeCoreResourcebutton delButton = generateCommonButton(page, "gridremoveBtn", "删除", "GRID", funcid);
			JeCoreResourcebutton confirmButton = generateCommonButton(page, "formConfirmBtn", "确定", "FORM", funcid);
			JeCoreResourcebutton cancelButton = generateCommonButton(page, "formCancelBtn", "取消", "FORM", funcid);
			buttons.add(addButton);
			buttons.add(editButton);
			buttons.add(delButton);
			buttons.add(confirmButton);
			buttons.add(cancelButton);
			return buttons;
		}
		
		public static JeCoreResourcebutton generateCommonButton(String page, String buttonCode, String buttonName, String buttonType,String funcid){
			String buttonId = page + "-" + buttonType + "-" + buttonCode;
			buttonId = buttonId.toLowerCase();
			JeCoreResourcebutton button = new JeCoreResourcebutton();
			button.setResourcebuttonFuncinfoId(funcid);
			button.setResourcebuttonBigiconcls("single");
			button.setSyOrderindex(0);
			button.setResourcebuttonCode(buttonCode);
			button.setResourcebuttonName(buttonName);
			button.setResourcebuttonType(buttonType);
			button.setJeCoreResourcebuttonId(buttonId);
			button.setSyStatus("1");
			return button;
		}
		
		public static JeCoreFuncinfo generateFuncinfo(String page,String pageName, String rootFuncid,String subSystemFuncid,String tableCode,Integer index, String funcid){
			JeCoreFuncinfo func = new JeCoreFuncinfo();
			func.setFuncinfoBigbutton("0");
			func.setFuncinfoCheckstatus("DEVELOPOUT");
			func.setFuncinfoCheckuser("超级管理员");
			func.setFuncinfoCheckuserid("JpgiKFpmxaG9szjInM0");
			func.setFuncinfoChildfilter("0");
			func.setFuncinfoChildrefresh("0");
			func.setFuncinfoChildshowtype("1");
			func.setFuncinfoColumnlazy("1");
			func.setFuncinfoCxclselmodel("0");
			func.setFuncinfoDdorder("0");
			func.setFuncinfoEnableformprint("0");
			func.setFuncinfoFieldlazy("0");
			func.setFuncinfoFormcols("2");
			func.setFuncinfoFormdefwidth("0");
			func.setFuncinfoFormlabelwidth(150);
			func.setFuncinfoFormlabelwidthEn(0);
			func.setFuncinfoFormlazy("0");
			func.setFuncinfoFormpaging("0");
			func.setFuncinfoFuncaction("/je/dynaAction");
			func.setFuncinfoFunccode(page);
			func.setFuncinfoFuncdic("0");
			func.setFuncinfoFuncname(pageName);
			func.setFuncinfoFunctype("func");
			func.setFuncinfoGridchildss("0");
			func.setFuncinfoGroupformopen("0");
			func.setFuncinfoHideformtbar("0");
			func.setFuncinfoHidegridtbar("0");
			func.setFuncinfoIconcls("JE_FUNC");
			func.setFuncinfoInserttype("FORM");
			func.setFuncinfoIsbookmark("0");
			func.setFuncinfoIscomplete("1");
			func.setFuncinfoListform("0");
			func.setFuncinfoMultiquery("0");
			func.setFuncinfoMultiselect("1");
			func.setFuncinfoNodeinfotype("FUNC");
			func.setFuncinfoOcform("0");
			func.setFuncinfoOnetoform("0");
			func.setFuncinfoOpenform("0");
			func.setFuncinfoOrdersql(" ORDER BY SY_CREATETIME DESC");
			func.setFuncinfoPagesize(30);
			func.setFuncinfoPkname(tableCode+"-id");
			func.setFuncinfoQuerywidth(150);
			func.setFuncinfoTableform("0");
			func.setFuncinfoTablename(tableCode);
			func.setFuncinfoTablestyle("0");
			func.setFuncinfoTreerefresh("0");
			func.setFuncinfoUsedatalog("0");
			func.setFuncinfoUsefiles("0");
			func.setFuncinfoUseguide("0");
			func.setFuncinfoUsewf("0");
			func.setFuncinfoVersion("0");
			func.setSyJecore("0");
			func.setSyJesys("0");
			func.setJeCoreFuncinfoId(funcid);
			func.setSyAudflag("NOSTATUS");
			func.setSyCreateorg("ADMINISTRATOR");
			func.setSyCreateorgname("系统管理部");
			func.setSyCreatetime("2018-02-23 15:00:00");
			func.setSyCreateuser("admin");
			func.setSyCreateusername("超级管理员");
			func.setSyLayer(3);
			func.setSyNodetype("LEAF");
			func.setSyOrderindex(index);
			func.setSyParent(subSystemFuncid);
			func.setSyParentpath("/ROOT/"+rootFuncid +"/"+subSystemFuncid);
			func.setSyPath("/ROOT/"+rootFuncid +"/"+subSystemFuncid+"/"+funcid);
			func.setSyStatus("1");
			func.setFuncinfoGridbuffered("0");
			func.setFuncinfoGridtimeout(0);
			func.setFuncinfoGridrowtipshow("0");
			func.setFuncinfoGridhides("cxcl");
			func.setFuncinfoVersionDev("0");
			func.setFuncinfoCheckuserDev("超级管理员");
			func.setFuncinfoCheckuseridDev("JpgiKFpmxaG9szjInM0");
			func.setFuncinfoCheckstatusDev("DEVELOPOUT");
			func.setFuncinfoSfxdb("0");
			func.setFuncinfoUsewflog("0");
			func.setFuncinfoChildheight("[{\"code\":\"default\",\"text\":\"\\u901a\\u7528\",\"value\":0}]");
			func.setFuncinfoUsetreegrid("0");
			return func;
		}
		public static JeCoreMenu generateRootMenu(String rootMenuid,String subSystemMenuid){
			JeCoreMenu menu = new JeCoreMenu();
			menu.setMenuBigbutton("0");
			menu.setMenuFunctype("grid");
			menu.setMenuMenuname(GeneratorProperties.getProperty("subpackageCh"));
			menu.setMenuMenusubname(GeneratorProperties.getProperty("subpackage"));
			menu.setMenuNodeinfotype("MENU");
			menu.setMenuQuickstart("0");
			menu.setJeCoreMenuId(subSystemMenuid);
			menu.setSyAudflag("NOSTATUS");
			menu.setSyCreateorg("ADMINISTRATOR");
			menu.setSyCreateorgname("系统管理部");
			menu.setSyCreatetime("2018-02-23 15:00:00");
			menu.setSyCreateuser("admin");
			menu.setSyCreateusername("超级管理员");
			menu.setSyNodetype("GENERAL");
			menu.setSyOrderindex(5);
			menu.setSyLayer(2);
			menu.setSyParent(rootMenuid);
			menu.setSyParentpath("/ROOT/"+rootMenuid);
			menu.setSyPath("/ROOT/"+rootMenuid+"/"+subSystemMenuid);
			menu.setSyStatus("1");
			return menu;
		}
		public static JeCoreMenu generateMenu(String page,String pageName,String rootMenuid,String subSystemMenuid, Integer index){
			//String menuId = UUID.randomUUID().toString().replaceAll("-", "");
			String menuId = subSystemMenuid + "-" + page.toLowerCase();
			JeCoreMenu menu = new JeCoreMenu();
			menu.setMenuBigbutton("0");
			menu.setMenuFunctype("grid");
			menu.setMenuMenuname(pageName);
			menu.setMenuMenusubname(page);
			menu.setMenuNodeinfo(page);
			menu.setMenuNodeinfotype("MT");
			menu.setMenuQuickstart("0");
			menu.setJeCoreMenuId(menuId);
			menu.setSyAudflag("NOSTATUS");
			menu.setSyCreateorg("ADMINISTRATOR");
			menu.setSyCreateorgname("系统管理部");
			menu.setSyCreatetime("2018-02-23 15:00:00");
			menu.setSyCreateuser("admin");
			menu.setSyCreateusername("超级管理员");
			menu.setSyLayer(3);
			menu.setSyNodetype("LEAF");
			menu.setSyOrderindex(index);
			menu.setSyParent(subSystemMenuid);
			menu.setSyParentpath("/ROOT/"+rootMenuid +"/"+subSystemMenuid);
			menu.setSyPath("/ROOT/"+rootMenuid +"/"+subSystemMenuid+"/"+menuId);
			menu.setSyStatus("1");
			return menu;
		}
		//======== add xiaosisi on 2017/10/17 ======== 
		/**
		 * 根据页面使用的表生成表单字段和列头字段
		 * @param tables
		 * @return
		 */
		public static Map<String, Object> generatorCloums(List<Table> tables,String funcId){
			Map<String, Object> result = new HashMap<String, Object>();
			//表单字段
			List<JeCoreResourcecolumn> jepfColumns = new ArrayList<JeCoreResourcecolumn>();
			//列表字段
			List<JeCoreResourcefield> jepfFileds = new ArrayList<JeCoreResourcefield>();
			//页面vo中的字段
			Map<String, Column> voColumns = new HashMap<String,Column>();
			//数据字典相关
			List<NsCoreDictionarygroup> dictGroup = new ArrayList<NsCoreDictionarygroup>();
			List<NsCoreDictionary> dict = new ArrayList<NsCoreDictionary>();
			List<NsCoreDictionaryitem> dictItems = new ArrayList<NsCoreDictionaryitem>();
			//循环表中列，获取是否列头和表头
			for(Table table : tables){
				System.out.println("==============================正在处理表【"+table.getSqlName()+"】中的列========================");
				for(Column column : table.getColumns()){
					System.out.println("==============================正在处理列:"+column.getColumnNameSource()+"========================");
					//获取列的注释，注释中包含label，列头相关属性，表单相关属性，字典相关属性
					String alias = column.getColumnAlias();
					//判断是否列头或者表头,
					if(StringUtils.isNullOrEmpty(alias)){
						continue;
					}
					List<String> infos = new ArrayList<String>();
					if(alias.indexOf(",") > -1){
						infos.addAll(Arrays.asList(alias.split(",")));
					}else{
						infos.add(alias);
					}
					//组装jepf column对象
					String labelName = infos.get(0);
					
					if(infos.size() >= 2){
						String columnInfo = infos.get(1);
						if(!StringUtils.isNullOrEmpty(columnInfo) && columnInfo.indexOf("|") >= 0){
							List<String> columnInfoList = Arrays.asList(columnInfo.split("\\|"));
							//判断是否列头，如果是列头，组装列头对象
							if("1".equals(columnInfoList.get(0))){
								voColumns.put(column.getColumnNameSource(), column);
								JeCoreResourcecolumn jepfColumn = new JeCoreResourcecolumn();
								//columnID
								//jepfColumn.setJeCoreResourcecolumnId(UUID.randomUUID().toString());
								jepfColumn.setJeCoreResourcecolumnId(funcId+"-column-"+column.getColumnNameSource().toLowerCase());
								//列头类型
								jepfColumn.setResourcecolumnXtype(changeCloumnXtype(columnInfoList.get(1)));
								//是否合计字段
								jepfColumn.setResourcecolumnAllowedit(columnInfoList.get(2));
								//宽度
								jepfColumn.setResourcecolumnWidth(columnInfoList.get(3));
								//顺序
								jepfColumn.setResourcecolumnIndex(columnInfoList.get(4));
								jepfColumn.setResourcecolumnOrder(columnInfoList.get(4));
								jepfColumn.setSyOrderindex(Integer.parseInt(columnInfoList.get(4)));
								jepfColumn.setResourcecolumnFuncinfoId(funcId);
								//jepfColumn.setResourcecolumnCode(column.getColumnName());
								jepfColumn.setResourcecolumnCode(column.getColumnNameSource());
								jepfColumn.setResourcecolumnName(labelName);
								//jepfColumn.setResourcecolumnNameEn(column.getColumnName());
								jepfColumn.setResourcecolumnNameEn(column.getColumnNameSource());
								jepfColumn.setResourcecolumnAlign("left");
								jepfColumn.setResourcecolumnHidden("0");
								jepfColumn.setResourcecolumnAllowblank("1");
								jepfColumn.setResourcecolumnCheckedcls("null");
								jepfColumn.setResourcecolumnColor1("null");
								jepfColumn.setResourcecolumnColor2("null");
								jepfColumn.setResourcecolumnColumnquerytype("null");
								jepfColumn.setResourcecolumnColumntip("");
								jepfColumn.setResourcecolumnConfiginfo("null");
								jepfColumn.setResourcecolumnConversion("null");
								jepfColumn.setResourcecolumnDateformat("null");
								jepfColumn.setResourcecolumnDdcode("null");
								jepfColumn.setResourcecolumnDescription(labelName);
								jepfColumn.setResourcecolumnEnableicon("1");
								jepfColumn.setResourcecolumnEnableupdate("1");
								jepfColumn.setResourcecolumnFieldorderindex(new BigDecimal("0"));
								jepfColumn.setResourcecolumnFkcode("null");
								jepfColumn.setResourcecolumnFlex("null");
								jepfColumn.setResourcecolumnFontcolor("null");
								jepfColumn.setResourcecolumnGroup("null");
								jepfColumn.setResourcecolumnHidetitlecls("null");
								jepfColumn.setResourcecolumnHighlighting("null");
								jepfColumn.setResourcecolumnHyperlink("null");
								jepfColumn.setResourcecolumnIfimpl("null");
								jepfColumn.setResourcecolumnIsdd("null");
								jepfColumn.setResourcecolumnIspk("0");
								jepfColumn.setResourcecolumnJslistener("null");
								jepfColumn.setResourcecolumnLocked("0");
								jepfColumn.setResourcecolumnKeyword("null");
								jepfColumn.setResourcecolumnLazyload("1");
								jepfColumn.setResourcecolumnLinkmethod("null");
								jepfColumn.setResourcecolumnMaxvalue(0);
								jepfColumn.setResourcecolumnMerge("null");
								jepfColumn.setResourcecolumnMinvalue(0);
								jepfColumn.setResourcecolumnMorecolumn("null");
								jepfColumn.setResourcecolumnMorecolumnname("null");
								jepfColumn.setResourcecolumnMultirows("0");
								jepfColumn.setResourcecolumnNewfuncid("null");
								jepfColumn.setResourcecolumnQueryindex(0);
								jepfColumn.setResourcecolumnQueryinfo("null");
								jepfColumn.setResourcecolumnQuerytype("null");
								jepfColumn.setResourcecolumnQuickquery("null");
								jepfColumn.setResourcecolumnQuickquerytype("null");
								jepfColumn.setResourcecolumnRbordercolor("null");
								jepfColumn.setResourcecolumnSelectonfocus("null");
								jepfColumn.setResourcecolumnStatistallmsg("null");
								jepfColumn.setResourcecolumnStatisticsmsg("null");
								jepfColumn.setResourcecolumnStatisticstype("null");
								jepfColumn.setResourcecolumnStep(0);
								jepfColumn.setResourcecolumnSummaryformat("null");
								jepfColumn.setResourcecolumnSysmode("null");
								jepfColumn.setResourcecolumnTactics("null");
								jepfColumn.setResourcecolumnTitlecolor("null");
								jepfColumn.setResourcecolumnUncheckedcls("null");
								jepfColumn.setResourcecolumnValue("null");
								jepfColumn.setResourcecolumnVtype("null");
								jepfColumn.setResourcecolumnWidthEn("null");
								jepfColumn.setSyAudflag("1");
								jepfColumn.setSyCreateorg("null");
								jepfColumn.setSyCreateorgname("null");
								jepfColumn.setSyCreatetime("null");
								jepfColumn.setSyCreateuser("null");						
								jepfColumn.setSyCreateusername("null");
								jepfColumn.setSyFlag("null");
								jepfColumn.setSyFormuploadfiles("null");
								jepfColumn.setSyModifyorg("null");
								jepfColumn.setSyModifyorgname("null");
								jepfColumn.setSyModifytime("null");
								jepfColumn.setSyModifyuser("null");
								jepfColumn.setSyModifyusername("null");
								jepfColumn.setSyPdid("null");
								jepfColumn.setSyPiid("null");
								jepfColumn.setSyPyjz("null");
								jepfColumn.setSyPyqc("null");
								jepfColumn.setSyStatus("1");
								jepfColumns.add(jepfColumn);
							}
						}
					}
					//组装jepf fileds对象
					String fieldType = "";
					if(infos.size() >= 3){
						String fieldInfo = infos.get(2);
						if(!StringUtils.isNullOrEmpty(fieldInfo) && fieldInfo.indexOf("|") >= 0){
							List<String> fieldInfoList = Arrays.asList(fieldInfo.split("\\|"));
							//判断是否列头，如果是列头，组装列头对象
							if("1".equals(fieldInfoList.get(0))){
								//表单字段默认为vo中的对象
								voColumns.put(column.getColumnNameSource(), column);
								JeCoreResourcefield field = new JeCoreResourcefield();
								//field.setJeCoreResourcefieldId(UUID.randomUUID().toString());
								field.setJeCoreResourcefieldId(funcId+"-field-"+column.getColumnNameSource().toLowerCase());
								field.setResourcefieldFuncinfoId(funcId);
								//类型 
								fieldType = fieldInfoList.get(1);
								field.setResourcefieldXtype(changeFiledXtype(fieldInfoList.get(1)));
								//辅助配置信息
								field.setResourcefieldOtherconfig("{\"secondXtype\":\""+changeSecondXtype(fieldInfoList.get(1))+"\"}");
								//校验信息
								field.setResourcefieldVtype(changeSecondVtype(fieldInfoList.get(1)));
								//是否为空 
								field.setResourcefieldAllowblank(fieldInfoList.get(2));
								//必填表达式 ,非空验证规则
								field.setResourcefieldAllowblankexp(fieldInfoList.get(3));
								//排序字段 
								field.setSyOrderindex(Integer.parseInt(fieldInfoList.get(4)));
								//必填出错提示信息
								field.setResourcefieldBinding(getEmptyText(fieldInfoList.get(1), labelName));
								//输入提示 -palceholder
								field.setResourcefieldEmptytext(getEmptyText(fieldInfoList.get(1), labelName));
								//是否隐藏 
								field.setResourcefieldHidden(fieldInfoList.get(5));
								//是否只读 
								field.setResourcefieldReadonly("0");
								//宽度 
								field.setResourcefieldWidth("350");
								//必填控制表达式方法 
								field.setResourcefieldAllowblankexpfn("null");
								//背景色
								field.setResourcefieldBgcolor("null");
								// 绑定表达式方法 
								field.setResourcefieldBindingfn("null");
								//编码 
								field.setResourcefieldCode(column.getColumnNameSource());
								//英文名 
								field.setResourcefieldNameEn(column.getColumnNameSource());
								//列数(分组框用)
								field.setResourcefieldCols(1);
								//所占列数 
								field.setResourcefieldColspan(1);
								// 配置信息 
								field.setResourcefieldConfiginfo("null");
								//描述
								field.setResourcefieldDescription("null");
								//是否可用 
								field.setResourcefieldDisabled("1");
								//可选可编辑 
								field.setResourcefieldEditable("0");
								//可选表达式 
								field.setResourcefieldEnableexp("null");
								//树形字典项可选表达式
								field.setResourcefieldEnableexpfn("null");
								//可选表达式提示语 
								field.setResourcefieldEnabletip("null");
								//字段样式表 
								field.setResourcefieldFieldcls("null");
								//字段颜色 
								field.setResourcefieldFieldcolor("null");
								//字段懒加载 
								field.setResourcefieldFieldlazy("1");
								//比例 
								field.setResourcefieldFlex("null");
								//分组框 
								field.setResourcefieldGroupname("");
								//高度 
								field.setResourcefieldHeight("32");
								//历史留痕 
								field.setResourcefieldHistory("null");
								//是否导入
								field.setResourcefieldIfimpl("null");
								//显隐控制表达式
								field.setResourcefieldInterpreter("null");
								//显隐控制表达式方法 
								field.setResourcefieldInterpreterfn("null");
								//是否主键
								field.setResourcefieldIspk("0");
								//监听事件 
								field.setResourcefieldJslistener("null");
								//标签颜色
								field.setResourcefieldLabelcolor("null");
								//定位
								field.setResourcefieldLocate("null");
								//最大值 
								field.setResourcefieldMaxvalue(0);
								//最小值 
								field.setResourcefieldMinvalue(0);
								//名称 
								field.setResourcefieldName(labelName);
						
								//产品扩展功能ID
								field.setResourcefieldNewfuncid("null");
								//只读表达式 
								field.setResourcefieldReadonlyexp("null");
								//只读控制表达式方法 
								field.setResourcefieldReadonlyexpfn("null");
								//正则表达式 
								field.setResourcefieldRegex("null");
								//正则提示信息 
								field.setResourcefieldRegextext("null");
								//禁用 
								field.setResourcefieldRemoved("0");
								//依附字段 
								field.setResourcefieldRowfield("null");
								//所占行数
								field.setResourcefieldRowspan(1);
								//步长 
								field.setResourcefieldStep(0);
								//系统模式 
								field.setResourcefieldSysmode("null");
								//高级后缀
								field.setResourcefieldUnitlisttpl("null");
								//字段后缀
								field.setResourcefieldUnittpl("null");
								//默认值 
								field.setResourcefieldValue("");
								//默认值函数 
								field.setResourcefieldValuefn("null");
								//验证配置
								field.setResourcefieldVtypeconfig("null");
								//whereSql
								field.setResourcefieldWheresql("");
								//审核标记 
								field.setSyAudflag("1");
								//登记者所在部门编码 
								field.setSyCreateorg("null");
								//登记者所在部门
								field.setSyCreateorgname("null");
								//登记时间
								field.setSyCreatetime("null");
								//登记人编码
								field.setSyCreateuser("null");
								//登记人 
								field.setSyCreateusername("null");
								//是否启用本条数据
								field.setSyFlag("1");
								//表单上传虚字段 
								field.setSyFormuploadfiles("null");								
								//修改人部门编码
								field.setSyModifyorg("null");
								//修改人部门
								field.setSyModifyorgname("null");
								//修改时间
								field.setSyModifytime("null");
								//修改人编码
								field.setSyModifyuser("null");
								//修改人
								field.setSyModifyusername("null");
								//流程定义ID
								field.setSyPdid("null");
								//流程实例ID
								field.setSyPiid("null");
								//拼音简写 
								field.setSyPyjz("null");
								//拼音全称 
								field.setSyPyqc("null");
								//数据状态 
								field.setSyStatus("1");
								// 自定义验证方法 
								field.setResourcefieldVtypefn("null");
								//标签位置 
								field.setResourcefieldLabelalign("null");
								jepfFileds.add(field);
							}
						}
					}
					//数据字典sql信息准备
					if(infos.size() >= 4 && ("select".equals(fieldType) || "radio".equals(fieldType) || "checkbox".equals(fieldType))){
						String dictInfo = infos.get(3);
						if(!StringUtils.isNullOrEmpty(dictInfo)){
							List<String> dictInfoList = new ArrayList<String>();
							if(dictInfo.indexOf("|") >= 0){
								dictInfoList = Arrays.asList(dictInfo.split("\\|"));
							}else{
								dictInfoList.add(dictInfo);
							}
							//String jecoreDictid = UUID.randomUUID().toString();
							String jecoreDictid = funcId + "-dict-" + column.getColumnNameSource().toLowerCase();
							if(dictInfoList.size() > 0){
								NsCoreDictionary coreDictionary = new NsCoreDictionary();
								coreDictionary.setEnterpriseId(0L);
								coreDictionary.setOrganizationId(0L);
								coreDictionary.setDictionaryGroupId(0L);
								coreDictionary.setJeCoreDictionaryId(jecoreDictid);
								coreDictionary.setDictionaryUseScope("0");
								coreDictionary.setDictionaryBelongsto("null");
								coreDictionary.setDictionaryBelongstoname("null");
								coreDictionary.setDictionaryClass("null");
								coreDictionary.setDictionaryClassname("null");
								coreDictionary.setDictionaryDdcode(column.getColumnNameSource());
								coreDictionary.setDictionaryDdname(labelName);
								coreDictionary.setDictionaryDdtype("null");
								coreDictionary.setDictionaryDictype(GeneratorProperties.getProperty("subpackage"));
								coreDictionary.setDictionaryFieldconfigs("null");
								coreDictionary.setDictionaryItemrootId("null");
								coreDictionary.setDictionaryMethod("null");
								coreDictionary.setDictionaryOrdersql("null");
								coreDictionary.setDictionarySql("null");
								coreDictionary.setDictionarySqlconfig("null");
								coreDictionary.setDictionarySqllbsm("null");
								coreDictionary.setDictionarySqlpzxxlb("null");
								coreDictionary.setDictionarySqlsxsm("null");
								coreDictionary.setDictionaryWheresql("null");
								coreDictionary.setSyJecore("null");
								coreDictionary.setSyJesys("null");
								coreDictionary.setSyAudflag("null");
								coreDictionary.setSyCreateorg("null");
								coreDictionary.setSyCreateorgname("null");
								coreDictionary.setSyCreatetime("null");
								coreDictionary.setSyCreateuser("null");
								coreDictionary.setSyCreateusername("null");
								coreDictionary.setSyFlag("null");
								coreDictionary.setSyFormuploadfiles("null");
								coreDictionary.setSyModifyorg("null");
								coreDictionary.setSyModifyorgname("null");
								coreDictionary.setSyModifytime("null");
								coreDictionary.setSyModifyuser("null");
								coreDictionary.setSyModifyusername("null");
								coreDictionary.setSyOrderindex(0);
								coreDictionary.setSyPdid("null");
								coreDictionary.setSyPiid("null");
								coreDictionary.setSyPyjz("null");
								coreDictionary.setSyPyqc("null");
								coreDictionary.setSyStatus("null");
								dict.add(coreDictionary);
							
							}
							//生成数据字典项
							int dictItemIndex = 0;
							for(String dictItemStr : dictInfoList){
								if(!StringUtils.isNullOrEmpty(dictItemStr) && dictItemStr.indexOf(":") > 0){
									List<String> dictItem = Arrays.asList(dictItemStr.split("\\:"));
									String itemValue = dictItem.get(0);
									String itemName = dictItem.get(1);
									NsCoreDictionaryitem item = new NsCoreDictionaryitem();
									item.setEnterpriseId(0L);
									item.setOrganizationId(0L);
									item.setJeCoreDictionaryitemId(jecoreDictid + "-item"+ dictItemIndex);
									item.setDictionaryitemDictionaryId(jecoreDictid);
									item.setDictionaryitemBackgroundcolor("null");
									item.setDictionaryitemClassify("null");
									item.setDictionaryitemFontcolor("null");
									item.setDictionaryitemIconcls("null");
									item.setDictionaryitemItemcode(itemValue);
									item.setDictionaryitemItemname(itemName);
									item.setDictionaryitemItemnameEn("null");
									item.setDictionaryitemNodeinfo("null");
									item.setDictionaryitemNodeinfotype("null");
									item.setDictionaryitemRefphoto("null");
									item.setDictionaryitemTreeiconcls("null");
									item.setSyAudflag("null");
									item.setSyCreateorg("null");
									item.setSyCreateorgname("null");
									item.setSyCreatetime("null");
									item.setSyCreateuser("null");
									item.setSyCreateusername("null");
									item.setSyFlag("0");
									item.setSyFormuploadfiles("null");
									item.setSyLayer(0);
									item.setSyModifyorg("null");
									item.setSyModifyorgname("null");
									item.setSyModifytime("null");
									item.setSyModifyuser("null");
									item.setSyModifyusername("null");
									item.setSyNodetype("null");
									item.setSyOrderindex(0);
									item.setSyParent("null");
									item.setSyParentpath("null");
									item.setSyPath("null");
									item.setSyPdid("null");
									item.setSyPiid("null");
									item.setSyPyjz("null");
									item.setSyPyqc("null");
									item.setSyStatus("null");
									item.setSyTreeorderindex("null");
									dictItems.add(item);
									dictItemIndex = dictItemIndex + 1;
								}
							}
						}
					}
				}
			}
			if(dictItems.size() > 0){
//				List<NsCoreDictionarygroup> dictGroup = new ArrayList<NsCoreDictionarygroup>();	
				NsCoreDictionarygroup group = new NsCoreDictionarygroup();
				group.setEnterpriseId(0L);
				group.setOrganizationId(0L);
				group.setDictionaryGroupName(GeneratorProperties.getProperty("subpackageCh"));
				group.setIsDeleted(0);
				group.setRemark("null");
				group.setCreateUserId(0L);
				group.setCreateTime(new Date());
				group.setUpdateUserId(0L);
				group.setUpdateTime(new Date());
				dictGroup.add(group);
			}
			
			result.put("jepfColums", jepfColumns);
			result.put("jepfFields", jepfFileds);
			//将jepfColumns和jepfFields处理成system库中需要的字段
			result.put("systemColums", changeToSystemCloumns(jepfColumns));
			result.put("systemFields", changeToSystemFields(jepfFileds));
			List<Column>  cloumsList = new ArrayList<Column>();
			if(voColumns.keySet().size() > 0){
				for(String key : voColumns.keySet()){
					cloumsList.add(voColumns.get(key));
				}
			}
			result.put("voColumns", cloumsList);
			result.put("dictGroup", dictGroup);
			result.put("dicts", dict);
			result.put("dictItems", dictItems);
			return result;
		}
		
		public static List<JeCoreResourcecolumn> changeToSystemCloumns(List<JeCoreResourcecolumn> jepfColumns){
			List<JeCoreResourcecolumn> systemColumns = new ArrayList<JeCoreResourcecolumn>();
			for(JeCoreResourcecolumn column : jepfColumns){
				JeCoreResourcecolumn systemColumn = new JeCoreResourcecolumn();
				BeanUtils.copyProperties(column, systemColumn);
				systemColumn.setResourcecolumnCode(StringHelper.underlineToHump(systemColumn.getResourcecolumnCode()));
				systemColumn.setResourcecolumnXtype(changeCloumnXtypeForSystem(systemColumn.getResourcecolumnXtype()));
				systemColumns.add(systemColumn);
			}
			return systemColumns;
		}
		public static List<JeCoreResourcefield> changeToSystemFields(List<JeCoreResourcefield> jepfFields){
			List<JeCoreResourcefield> systemFields = new ArrayList<JeCoreResourcefield>();
			for(JeCoreResourcefield field : jepfFields){
				JeCoreResourcefield systemField = new JeCoreResourcefield();
				BeanUtils.copyProperties(field, systemField);
				systemField.setResourcefieldCode(StringHelper.underlineToHump(systemField.getResourcefieldCode()));
				systemField.setResourcefieldGroupname(StringHelper.underlineToHump(systemField.getResourcefieldGroupname()));
				systemField.setResourcefieldInterpreter(StringUtils.isNullOrEmpty(systemField.getResourcefieldInterpreter())?"0":field.getResourcefieldInterpreter());
				systemField.setResourcefieldXtype(changeFiledXtypeForSystem(systemField.getResourcefieldXtype()));
				systemFields.add(systemField);
			}
			return systemFields;
		}
		
		/**
		 * 根据coloumn创建资源表
		 * @param list
		 * @return
		 */
		private static JeCoreResourcetable resourceModuleTableHanlder(String pageName, String resourceTableRootId){
			Map<String,Object> map = new HashMap<String, Object>();
			String tableCode = GeneratorProperties.getProperty("subpackage")+"-"+pageName.toLowerCase();
			//处理resourceTable
			JeCoreResourcetable resourceTable = new JeCoreResourcetable();
			resourceTable.setResourcetableIscreate("0");
			resourceTable.setResourcetableIsuseforeignkey("0");
			resourceTable.setResourcetableMoreroot("0");
			resourceTable.setResourcetableTablecode(GeneratorProperties.getProperty("subpackage"));
			resourceTable.setResourcetableTablename(GeneratorProperties.getProperty("subpackageCh"));
			resourceTable.setResourcetableType("MODULE");
			resourceTable.setSyDisabled("1");
			resourceTable.setSyJecore("0");
			resourceTable.setSyJesys("0");
			resourceTable.setJeCoreResourcetableId(resourceTableRootId);
			resourceTable.setSyAudflag("NOSTATUS");
			resourceTable.setSyLayer(1);
			resourceTable.setSyNodetype("GENERAL");
			resourceTable.setSyOrderindex(7);
			resourceTable.setSyParent("ROOT");
			resourceTable.setSyParentpath("ROOT");
			resourceTable.setSyPath("/ROOT/"+resourceTableRootId);
			resourceTable.setSyStatus("1");
			return resourceTable;
			
		}
		/**
		 * 根据coloumn创建资源表
		 * @param list
		 * @return
		 */
		private static Map<String,Object> resourceTableHanlder(String tableCode ,String pageName, List<Column> list, String resourceTableRootId){
			if(list == null || list.size() == 0){
				return null;
			}
			Map<String,Object> map = new HashMap<String, Object>();
			//String tableCode = GeneratorProperties.getProperty("subpackage")+"-"+pageName.toLowerCase();
			//处理resourceTable
			//String resourceTableKey = UUID.randomUUID().toString();
			String resourceTableKey = tableCode + "-key";
			JeCoreResourcetable resourceTable = new JeCoreResourcetable();
			resourceTable.setResourcetableIconcls("table_pt");
			resourceTable.setResourcetableIscreate("1");
			resourceTable.setResourcetableIsuseforeignkey("0");
			resourceTable.setResourcetableMoreroot("0");
			resourceTable.setResourcetablePkcode("ID");
			resourceTable.setResourcetableTablecode(tableCode);
			resourceTable.setResourcetableTablename(GeneratorProperties.getProperty(pageName + ".name"));
			resourceTable.setResourcetableType("PT");
			resourceTable.setSyDisabled("0");
			resourceTable.setSyJecore("0");
			resourceTable.setSyJesys("0");
			resourceTable.setJeCoreResourcetableId(resourceTableKey);
			resourceTable.setSyAudflag("NOSTATUS");
			resourceTable.setSyCreateorg("ADMINISTRATOR");
			resourceTable.setSyCreateorgname("系统管理部");
			resourceTable.setSyCreatetime("2018-01-24 14:00:00");
			resourceTable.setSyCreateuser("admin");
			resourceTable.setSyCreateusername("超级管理员");
			resourceTable.setSyLayer(2);
			resourceTable.setSyNodetype("LEAF");
			resourceTable.setSyOrderindex(0);
			resourceTable.setSyParent(resourceTableRootId);
			resourceTable.setSyPath("/ROOT/"+resourceTableRootId +"/"+ resourceTableKey);
			map.put("resourceTable", resourceTable);
			//resource table key 记录生成
			JeCoreTablekey tableKey = new JeCoreTablekey();
			tableKey.setTablekeyChecked("1");
			tableKey.setTablekeyClassify("SYS");
			tableKey.setTablekeyCode(tableCode+"-id-code");
			tableKey.setTablekeyColumncode(tableCode+"-id");
			tableKey.setTablekeyIscreate("0");
			tableKey.setTablekeyIsrestraint("1");
			tableKey.setTablekeyResourcetableId(resourceTableKey);
			tableKey.setTablekeyTablecode(tableCode);
			tableKey.setTablekeyType("Primary");
			tableKey.setJeCoreTablekeyId(UUID.randomUUID().toString());
			tableKey.setSyOrderindex(0);
			map.put("tableKey", tableKey);
			//创建tableindex表
			List<JeCoreTableindex> indexs = new ArrayList<JeCoreTableindex>();
			JeCoreTableindex syOrderIndex = new JeCoreTableindex();
			syOrderIndex.setTableindexClassify("SYS");
			syOrderIndex.setTableindexFieldcode("SY_ORDERINDEX");
			syOrderIndex.setTableindexFieldname("排序字段");
			syOrderIndex.setTableindexIscreate("1");
			syOrderIndex.setTableindexName("je-"+System.currentTimeMillis()+"-order");
			syOrderIndex.setTableindexResourcetableId(resourceTableKey);
			syOrderIndex.setTableindexTablecode(tableCode);
			syOrderIndex.setTableindexUnique("0");
			syOrderIndex.setJeCoreTableindexId(UUID.randomUUID().toString());
			syOrderIndex.setSyOrderindex(1);
			indexs.add(syOrderIndex);
			JeCoreTableindex idIndex = new JeCoreTableindex();
			idIndex.setTableindexClassify("SYS");
			idIndex.setTableindexFieldcode(tableCode+"-id");
			idIndex.setTableindexFieldname("主键id");
			idIndex.setTableindexIscreate("1");
			idIndex.setTableindexName("je-"+System.currentTimeMillis()+"-id");
			idIndex.setTableindexResourcetableId(resourceTableKey);
			idIndex.setTableindexTablecode(tableCode);
			idIndex.setTableindexUnique("1");
			idIndex.setJeCoreTableindexId(UUID.randomUUID().toString());
			idIndex.setSyOrderindex(0);
			indexs.add(idIndex);
			map.put("tableIndex", indexs);
			List<JeCoreTablecolumn> resourceTalbeColumns = new ArrayList<JeCoreTablecolumn>();
			//生成tablecolumn
			for(int i = 0; i < list.size(); i++){
				Column col = list.get(i);
				String cloumnName = col.getColumnAlias();
				if(cloumnName.indexOf(",")>=0){
					cloumnName = cloumnName.split(",")[0];
				}
				JeCoreTablecolumn tableColumn = new JeCoreTablecolumn();
				tableColumn.setTablecolumnClassify("PRO");
				tableColumn.setTablecolumnCode(col.getColumnNameSource());
				tableColumn.setTablecolumnIscreate("1");
				tableColumn.setTablecolumnIsnull("1");
				tableColumn.setTablecolumnName(cloumnName);
				tableColumn.setTablecolumnNameEn(col.getColumnNameSource());
				tableColumn.setTablecolumnOldcode(col.getColumnNameSource());
				tableColumn.setTablecolumnOldunique("0");
				tableColumn.setTablecolumnResourcetableId(resourceTableKey);
				tableColumn.setTablecolumnTablecode(tableCode);
				tableColumn.setTablecolumnTreetype("NORMAL");
				if(col.getSqlTypeName().toUpperCase().equals("DATETIME")){
					tableColumn.setTablecolumnType(col.getSqlTypeName());
				}else{
					tableColumn.setTablecolumnType(col.getSqlTypeName()+col.getSize());
				}
				tableColumn.setTablecolumnUnique("0");
				tableColumn.setJeCoreTablecolumnId(UUID.randomUUID().toString());
				tableColumn.setSyAudflag("NOSTATUS");
				tableColumn.setSyOrderindex(i+1);
				tableColumn.setSyStatus("1");
				resourceTalbeColumns.add(tableColumn);
			}
			map.put("resourceTalbeColumns", resourceTalbeColumns);
			return map;
			
		}
		/**
		 * 将jepf中的xtype装换成saas中的xType
		 * @param sourceXtype
		 * @return
		 */
		public static String changeFiledXtypeForSystem(String sourceXtype){
			if(StringUtils.isNullOrEmpty(sourceXtype)){
				return "";
			}
			switch (sourceXtype){
				//文本框
				case "textfield":
					return "input";
				//数值框
				case "numberfield":
					return "input";
				//单选框
				case "rgroup":
					return "radio";
				//复选框
				case "cgroup":
					return "checkbox";
				//下拉框
				case "cbbfield":
					return "select";
				//文本域
				case "textarea":
					return "input";
				//html编辑器
				case "ckeditor":
					return "ckeditor";
				//编号
				case "textcode":
					return "textcode";
				//附件
				case "uxfilefield":
					return "uxfilefield";
				//多附件
				case "uxfilesfield":
					return "uxfilesfield";
				//日期
				case "datefield":
					return "datepicker";
				//日期时间
				case "datetimefield":
					return "timepicker";
				//日期月份
				case "datemonthfield":
					return "monthpicker";
				//日期期间
				case "rangedatefield":
					return "datepicker";
				//树形选择
				case "treessfield":
					return "treessfield";
				//树形选择大
				case "treessareafield":
					return "treessareafield";
				//查询选择
				case "gridssfield":
					return "gridssfield";
				//查询选择大
				case "gridssareafield":
					return "gridssareafield";
				//智能查询
				case "searchfield":
					return "searchfield";
				//人员构造器
				case "queryuserfield":
					return "queryuserfield";
				//人员构造器（大）
				case "queryuserareafield":
					return "queryuserareafield";
				//下拉框集合
				case "cbblistfield":
					return "cbblistfield";
				//多数据选项集合
				case "multiitem":
					return "cascader";
				//子功能集合
				case "childfuncfield":
					return "childfuncfield";
				//颜色选择器
				case "colorfield":
					return "upload";
				//流程图字段
				case "graphfield":
					return "graphfield";
				//展示字段
				case "displayfield":
					return "text";
				//评星（头像）
				case "starfield":
					return "avatar";
				//进度条
				case "barfield":
					return "separator";
				//子功能
				case "child":
					return "baseTable";
				//分组框
				case "fieldset":
					return "group";
				default:
					return "";
			}
		}
		/**
		 * 将clounm列转换成saas需要的类型
		 * @param sourceXtype
		 * @return
		 */
		public static String changeCloumnXtype(String sourceXtype){
			if(StringUtils.isNullOrEmpty(sourceXtype)){
				return "";
			}
			switch(sourceXtype){
				case "text":
					return "uxcolumn";
				case "number":
					return "rownumberer";
				case "date":
					return "actioncolumn";
				case "select":
					return "uxcheckcolumn";
				default:
					return "";
			}
		}
		
		/**
		 * 将clounm列转换成saas需要的类型
		 * @param sourceXtype
		 * @return
		 */
		public static String changeCloumnXtypeForSystem(String sourceXtype){
			if(StringUtils.isNullOrEmpty(sourceXtype)){
				return "";
			}
			switch(sourceXtype){
				case "uxcolumn":
					return "text";
				case "rownumberer":
					return "number";
				case "actioncolumn":
					return "date";
				case "uxcheckcolumn":
					return "select";
				default:
					return "";
			}
		}
		/**
		 * 将jepf中的xtype装换成saas中的xType
		 * @param sourceXtype
		 * @return
		 */
		public static String getEmptyText(String sourceXtype, String columnName){
			if(StringUtils.isNullOrEmpty(sourceXtype)){
				return "";
			}
			switch (sourceXtype){
				//文本框
				case "text":
					return "请输入" + columnName;
				//数值框
				case "numbertext":
					return "请输入" + columnName;
					//文本域
				case "textarea":
					return "请输入" + columnName;
				//单选框
				case "radio":
					return "";
				//复选框
				case "checkbox":
					return "";
				//下拉框
				case "select":
					return "请选择" + columnName;
				//日期
				case "datepicker":
					return "请选择" + columnName;
				//日期时间
				case "timepicker":
					return "请选择" + columnName;
				//日期月份
				case "monthpicker":
					return "请选择" + columnName;
				//日期期间
				case "datepicker-range":
					return "请选择" + columnName;
				//多数据选项集合，省市区
				case "cascader":
					return "请选择" + columnName;
				//上传
				case "upload":
					return "";
				//展示字段
				case "label":
					return "";
				//评星（头像）
				case "avatar":
					return "";
				//分割线
				case "separator":
					return "";
				//子功能
				case "child":
					return "";
				//分组框
				case "group":
					return "";
				default:
					return sourceXtype;
			}
		}
		/**
		 * 将jepf中的xtype装换成saas中的xType
		 * @param sourceXtype
		 * @return
		 */
		public static String changeFiledXtype(String sourceXtype){
			if(StringUtils.isNullOrEmpty(sourceXtype)){
				return "";
			}
			switch (sourceXtype){
				//文本框
				case "text":
					return "textfield";
				//数值框
				case "numbertext":
					return "numberfield";
					//文本域
				case "textarea":
					return "textarea";
				//单选框
				case "radio":
					return "rgroup";
				//复选框
				case "checkbox":
					return "cgroup";
				//下拉框
				case "select":
					return "cbbfield";
				//日期
				case "datepicker":
					return "datefield";
				//日期时间
				case "timepicker":
					return "datetimefield";
				//日期月份
				case "monthpicker":
					return "datemonthfield";
				//日期期间
				case "datepicker-range":
					return "rangedatefield";
				//多数据选项集合，省市区
				case "cascader":
					return "multiitem";
				//上传
				case "upload":
					return "colorfield";
				//展示字段
				case "label":
					return "displayfield";
				//评星（头像）
				case "avatar":
					return "starfield";
				//分割线
				case "separator":
					return "barfield";
				//子功能
				case "child":
					return "child";
				//分组框
				case "group":
					return "fieldset";
				default:
					return sourceXtype;
			}
		}
		
		/**
		 * 将jepf中的xtype装换成saas中的xType
		 * @param sourceXtype
		 * @return
		 */
		public static String changeSecondXtype(String sourceXtype){
			if(StringUtils.isNullOrEmpty(sourceXtype)){
				return "";
			}
			switch (sourceXtype){
				//文本框 string
				case "text":
					return "text";
				//数值框  string
				case "numbertext":
					return "text";
				//文本域 string
				case "textarea":
					return "textarea";
				//单选框 string
				case "radio":
					return "normal";
				//复选框 array
				case "checkbox":
					return "normal";
				//下拉框 string
				case "select":
					return "normal";
				//日期 date
				case "datepicker":
					return "datetime";
				//日期时间 date
				case "timepicker":
					return "datetime";
				//日期月份 date
				case "monthpicker":
					return "datetime";
				//日期期间 date
				case "datepicker-range":
					return "datetime";
				//多数据选项集合，省市区 array
				case "cascader":
					return "normal";
				//上传 null
				case "upload":
					return "picture-card";
				//展示字段 span
				case "label":
					return "text";
				//评星（头像） null
				case "avatar":
					return "round";
				//分割线 null
				case "separator":
					return "more";
				//子功能 object
				case "child":
					return "text";
				//分组框 
				default:
					return sourceXtype;
			}
		}
		
		/**
		 * 将jepf中的xtype装换成saas中的xType
		 * @param sourceXtype
		 * @return
		 */
		public static String changeSecondVtype(String sourceXtype){
			if(StringUtils.isNullOrEmpty(sourceXtype)){
				return "";
			}
			switch (sourceXtype){
				//文本框 string
				case "text":
					return "string";
				//数值框  string
				case "numbertext":
					return "string";
				//文本域 string
				case "textarea":
					return "string";
				//单选框 string
				case "radio":
					return "string";
				//复选框 array
				case "checkbox":
					return "array";
				//下拉框 string
				case "select":
					return "string";
				//日期 date
				case "datepicker":
					return "date";
				//日期时间 date
				case "timepicker":
					return "date";
				//日期月份 date
				case "monthpicker":
					return "date";
				//日期期间 date
				case "datepicker-range":
					return "date";
				//多数据选项集合，省市区 array
				case "cascader":
					return "array";
				//上传 null
				case "upload":
					return null;
				//展示字段 span
				case "label":
					return "span";
				//评星（头像） null
				case "avatar":
					return null;
				//分割线 null
				case "separator":
					return null;
				//子功能 object
				case "child":
					return "object";
				//分组框 
				default:
					return sourceXtype;
			}
		}
		public static GeneratorModel newFromSql(Sql sql) throws Exception {
			Map templateModel = new HashMap();
			templateModel.put("sql", sql);
			setShareVars(templateModel);
			
			Map filePathModel = new HashMap();
			setShareVars(filePathModel);
			filePathModel.putAll(BeanHelper.describe(sql));
			return new GeneratorModel(templateModel,filePathModel);
		}

		public static GeneratorModel newFromClass(Class clazz) {
			Map templateModel = new HashMap();
			templateModel.put("clazz", new JavaClass(clazz));
			setShareVars(templateModel);
			
			Map filePathModel = new HashMap();
			setShareVars(filePathModel);
			filePathModel.putAll(BeanHelper.describe(new JavaClass(clazz)));
			return new GeneratorModel(templateModel,filePathModel);
		}
		
		public static GeneratorModel newFromMap(Map params) {
			Map templateModel = new HashMap();
			templateModel.putAll(params);
			setShareVars(templateModel);
			
			Map filePathModel = new HashMap();
			setShareVars(filePathModel);
			filePathModel.putAll(params);
			return new GeneratorModel(templateModel,filePathModel);
		}
		
		public static void setShareVars(Map templateModel) {
			templateModel.putAll(GeneratorProperties.getProperties());
			templateModel.putAll(System.getProperties());
			templateModel.put("env", System.getenv());
			templateModel.put("now", new Date());
			templateModel.put("databaseType", getDatabaseType("databaseType"));
			templateModel.putAll(GeneratorContext.getContext());
		}

		private static String getDatabaseType(String key) {
			return GeneratorProperties.getProperty(key,DatabaseTypeUtils.getDatabaseTypeByJdbcDriver(GeneratorProperties.getProperty("jdbc.driver")));
		}

	}
	
	private static class PrintUtils {
		
		private static void printExceptionsSumary(String msg,String outRoot,List<Exception> exceptions) throws FileNotFoundException {
			File errorFile = new File(outRoot,"generator_error.log");
			if(exceptions != null && exceptions.size() > 0) {
				System.err.println("[Generate Error Summary] : "+msg);
				PrintStream output = new PrintStream(new FileOutputStream(errorFile));
				for(int i = 0; i < exceptions.size(); i++) {
					Exception e = exceptions.get(i);
                    System.err.println("[GENERATE ERROR]:"+e);
					if(i == 0) e.printStackTrace();
					e.printStackTrace(output);
				}
				output.close();
				System.err.println("***************************************************************");
				System.err.println("* "+"* 输出目录已经生成generator_error.log用于查看错误 ");
				System.err.println("***************************************************************");
			}
		}
		
		private static void printBeginProcess(String displayText,boolean isDatele) {
			GLogger.println("***************************************************************");
			GLogger.println("* BEGIN " + (isDatele ? " delete by " : " generate by ")+ displayText);
			GLogger.println("***************************************************************");
		}
		
		public static void printAllTableNames(List<Table> tables) throws Exception {
			GLogger.println("\n----All TableNames BEGIN----");
			for(int i = 0; i < tables.size(); i++ ) {
				String sqlName = tables.get(i).getSqlName();
				GLogger.println("g.generateTable(\""+sqlName+"\");");
			}
			GLogger.println("----All TableNames END----");
		}
	}

}
