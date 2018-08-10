package com.newsee.generator;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.newsee.generator.util.*;
/**
 * 代码生成器核心引擎
 * 主要提供以下两个方法供外部使用
 * <pre>
 * generateBy() 用于生成文件
 * deleteBy() 用于删除生成的文件
 * </pre>
 * @author xiaosisi
 */
public class Generator {
	private static final String GENERATOR_INSERT_LOCATION = "generator-insert-location";
	private List<File> templateRootDirs = new ArrayList<File>();
	private String outRootDir="generated-sources";
	private boolean ignoreTemplateGenerateException = true;
	private String removeExtensions = System.getProperty("generator.removeExtensions",".ftl,.vm");
	private boolean isCopyBinaryFile = true;
	
	private String includes = System.getProperty("generator.includes"); // 需要处理的模板，使用逗号分隔符,示例值: java_src/**,java_test/**
	private String excludes = System.getProperty("generator.excludes"); // 不需要处理的模板，使用逗号分隔符,示例值: java_src/**,java_test/**
	private String sourceEncoding =  System.getProperty("generator.sourceEncoding","UTF-8");
	private String outputEncoding =  System.getProperty("generator.outputEncoding","UTF-8");
	
	public Generator() {
	}
	
	public void setTemplateRootDir(File templateRootDir) {
		setTemplateRootDirs(new File[]{templateRootDir});
	}

	public void setTemplateRootDirs(File[] templateRootDirs) {
		this.templateRootDirs = Arrays.asList(templateRootDirs);
	}
	
	public void addTemplateRootDir(File f) {
		templateRootDirs.add(f);
	}
	
	public boolean isIgnoreTemplateGenerateException() {
        return ignoreTemplateGenerateException;
    }
	
	public void setIgnoreTemplateGenerateException(boolean ignoreTemplateGenerateException) {
        this.ignoreTemplateGenerateException = ignoreTemplateGenerateException;
    }

    public boolean isCopyBinaryFile() {
		return isCopyBinaryFile;
	}

	public void setCopyBinaryFile(boolean isCopyBinaryFile) {
		this.isCopyBinaryFile = isCopyBinaryFile;
	}

	public String getSourceEncoding() {
		return sourceEncoding;
	}

	public void setSourceEncoding(String sourceEncoding) {
		if(sourceEncoding == null) throw new IllegalArgumentException("sourceEncoding must be not null");
		this.sourceEncoding = sourceEncoding;
	}

	public String getOutputEncoding() {
		return outputEncoding;
	}

	public void setOutputEncoding(String outputEncoding) {
		if(outputEncoding == null) throw new IllegalArgumentException("outputEncoding must be not null");
		this.outputEncoding = outputEncoding;
	}
	
	public void setIncludes(String includes) {
		this.includes = includes;
	}
	/** 设置不处理的模板路径,可以使用ant类似的值,使用逗号分隔，示例值：  **\*.ignore */
	public void setExcludes(String excludes) {
		this.excludes = excludes;
	}

	public void setOutRootDir(String v) {
		if(v == null) throw new IllegalArgumentException("outRootDir must be not null");
		this.outRootDir = v;
	}
	
	public String getOutRootDir() {
		return outRootDir;
	}

	public void setRemoveExtensions(String removeExtensions) {
		this.removeExtensions = removeExtensions;
	}

    public void deleteOutRootDir() throws IOException {
        if(StringHelper.isBlank(getOutRootDir())) throw new IllegalStateException("'outRootDir' property must be not null.");
        GLogger.println("[delete dir]    "+getOutRootDir());
        FileHelper.deleteDirectory(new File(getOutRootDir()));
    }
	   
    /**
     * 生成文件
     * @param templateModel 生成器模板可以引用的变量
     * @param filePathModel 文件路径可以引用的变量
     * @throws Exception
     */
	public Generator generateBy(Map<String, Object> templateModel, Map<String, Object> filePathModel) throws Exception {
		processTemplateRootDirs(templateModel, filePathModel,false);
		return this;
	}
	
	//==== add by xiaosisi on 2017/10/17 ======
    /**
     * 生成文件
     * @param templateModel 生成器模板可以引用的变量
     * @param filePathModel 文件路径可以引用的变量
     * @throws Exception
     */
	public Generator generateByPage(Map<String, Object> templateModel,Map<String, Object> filePathModel) throws Exception {
		processTemplateRootDirsByPage(templateModel, filePathModel,false);
		return this;
	}
	 /**
     * 生成文件
     * @param templateModel 生成器模板可以引用的变量
     * @param filePathModel 文件路径可以引用的变量
     * @throws Exception
     */
	public Generator generateBySql(Map<String, Object> templateModel,Map<String, Object> filePathModel) throws Exception {
		processTemplateRootDirsBySql(templateModel, filePathModel,false);
		return this;
	}
	//==== add by xiaosisi on 2017/10/17 ======
	
	/**
	 * 删除生成的文件
	 * @param templateModel 生成器模板可以引用的变量
	 * @param filePathModel 文件路径可以引用的变量
	 * @return
	 * @throws Exception
	 */
    public Generator deleteBy(Map<String, Object> templateModel,Map<String, Object> filePathModel) throws Exception {
    	processTemplateRootDirs(templateModel, filePathModel,true);
    	return this;
    }	
	
	private void processTemplateRootDirs(Map<String, Object> templateModel,Map<String, Object> filePathModel,boolean isDelete) throws Exception {
	    if(StringHelper.isBlank(getOutRootDir())) throw new IllegalStateException("'outRootDir' property must be not null.");
		if(templateRootDirs.size() == 0) throw new IllegalStateException("'templateRootDirs' cannot empty");
		GeneratorException ge = new GeneratorException("generator occer error, Generator BeanInfo:"+BeanHelper.describe(this));
		for(int i = 0; i < this.templateRootDirs.size(); i++) {
			File templateRootDir = (File)templateRootDirs.get(i);
			//===========↓↓↓↓↓add by xiaosisi on 2017/10/17↓↓↓↓↓==================
			//controller 和 service 层按照页面为单位生成
			if(templateRootDir.getPath().indexOf("controller") >= 0 ||
					templateRootDir.getPath().indexOf("service") >= 0 ||
					templateRootDir.getPath().indexOf("vo") >= 0 ||
					templateRootDir.getPath().indexOf("sql") >= 0 ||
					templateRootDir.getPath().indexOf("webapp") >= 0){
				continue;
			}
			//===========↑↑↑↑↑↑add by xiaosisi on 2017/10/17==================
			List<Exception> exceptions = scanTemplatesAndProcess(templateRootDir,templateModel,filePathModel,isDelete);
			ge.addAll(exceptions); 
		}
		if(!ge.exceptions.isEmpty()) throw ge;
	}
	//==== add by xiaosisi on 2017/10/17 ======
	/**
	 * 根据每个页面生成相应文件
	 * @param templateModel
	 * @param filePathModel
	 * @param isDelete
	 * @throws Exception
	 */
	private void processTemplateRootDirsByPage(Map<String, Object> templateModel,Map<String, Object> filePathModel,boolean isDelete) throws Exception {
	    if(StringHelper.isBlank(getOutRootDir())) throw new IllegalStateException("'outRootDir' property must be not null.");
		if(templateRootDirs.size() == 0) throw new IllegalStateException("'templateRootDirs' cannot empty");
		GeneratorException ge = new GeneratorException("generator occer error, Generator BeanInfo:"+BeanHelper.describe(this));
		for(int i = 0; i < this.templateRootDirs.size(); i++) {
			File templateRootDir = (File)templateRootDirs.get(i);
			//===========↓↓↓↓↓add by xiaosisi on 2017/10/17↓↓↓↓↓==================
			//controller 和 service 层按照页面为单位生成
			if(templateRootDir.getPath().indexOf("dao") >= 0 || 
					templateRootDir.getPath().indexOf("mappers") >= 0 ||
					templateRootDir.getPath().indexOf("entity") >= 0 ||
					templateRootDir.getPath().indexOf("sql") >=0){
				continue;
			}
			//===========↑↑↑↑↑↑add by xiaosisi on 2017/10/17==================
			List<Exception> exceptions = scanTemplatesAndProcessPage(templateRootDir,templateModel,filePathModel,isDelete);
			ge.addAll(exceptions); 
		}
		if(!ge.exceptions.isEmpty()) throw ge;
	}
	
	/**
	 * 根据所有数据生成一个sql
	 * @param templateModel
	 * @param filePathModel
	 * @param isDelete
	 * @throws Exception
	 */
	private void processTemplateRootDirsBySql(Map<String, Object> templateModel,Map<String, Object> filePathModel,boolean isDelete) throws Exception {
	    if(StringHelper.isBlank(getOutRootDir())) throw new IllegalStateException("'outRootDir' property must be not null.");
		if(templateRootDirs.size() == 0) throw new IllegalStateException("'templateRootDirs' cannot empty");
		GeneratorException ge = new GeneratorException("generator occer error, Generator BeanInfo:"+BeanHelper.describe(this));
		for(int i = 0; i < this.templateRootDirs.size(); i++) {
			File templateRootDir = (File)templateRootDirs.get(i);
			List<Exception> exceptions = scanTemplatesAndProcessSql(templateRootDir,templateModel,filePathModel,isDelete);
			ge.addAll(exceptions); 
		}
		if(!ge.exceptions.isEmpty()) throw ge;
	}
	//==== add by xiaosisi on 2017/10/17 ======
	private List<Exception> scanTemplatesAndProcess(File templateRootDir, Map<String, Object> templateModel,Map<String, Object> filePathModel,boolean isDelete) throws Exception {
		if(templateRootDir == null) throw new IllegalStateException("'templateRootDir' must be not null");
		GLogger.println("-------------------load template from templateRootDir = '"+templateRootDir.getAbsolutePath()+"' outRootDir:"+new File(outRootDir).getAbsolutePath());
		
		List<File> srcFiles = FileHelper.searchAllNotIgnoreFile(templateRootDir);
		
		List<Exception> exceptions = new ArrayList<Exception>();
		for(int i = 0; i < srcFiles.size(); i++) {
			File srcFile = srcFiles.get(i);
			//===========↓↓↓↓↓add by xiaosisi on 2017/10/17↓↓↓↓↓==================
			//controller 和 service 层按照页面为单位生成
			if(srcFile.getPath().indexOf("controller") >= 0 || srcFile.getPath().indexOf("service") >= 0 || srcFile.getPath().indexOf("webapp") >= 0 || srcFile.getPath().indexOf("vo") >= 0 || srcFile.getPath().indexOf("sql") >= 0){
				continue;
			}
			//===========↑↑↑↑↑↑add by xiaosisi on 2017/10/17==================
			try {
			    if(isDelete){
			        new TemplateProcessor().executeDelete(templateRootDir, templateModel,filePathModel, srcFile);
			    }else {
			        new TemplateProcessor().executeGenerate(templateRootDir, templateModel,filePathModel, srcFile);
			    }
			}catch(Exception e) {
				if (ignoreTemplateGenerateException) {
			        GLogger.warn("iggnore generate error,template is:" + srcFile+" cause:"+e);
			        exceptions.add(e);
			    } else {
					throw e;
			    }
			}
		}
		return exceptions;
	}
	private List<Exception> scanTemplatesAndProcessPage(File templateRootDir, Map<String, Object> templateModel,Map<String, Object> filePathModel,boolean isDelete) throws Exception {
		if(templateRootDir == null) throw new IllegalStateException("'templateRootDir' must be not null");
		GLogger.println("-------------------load template from templateRootDir = '"+templateRootDir.getAbsolutePath()+"' outRootDir:"+new File(outRootDir).getAbsolutePath());
		
		List<File> srcFiles = FileHelper.searchAllNotIgnoreFile(templateRootDir);
		
		List<Exception> exceptions = new ArrayList<Exception>();
		for(int i = 0; i < srcFiles.size(); i++) {
			File srcFile = srcFiles.get(i);
			//===========↓↓↓↓↓add by xiaosisi on 2017/10/17↓↓↓↓↓==================
			//controller 和 service 层按照页面为单位生成
			if(srcFile.getPath().indexOf("dao") > 0 || srcFile.getPath().indexOf("mappers") > 0 || srcFile.getPath().indexOf("entity") > 0 || srcFile.getPath().indexOf("sql") > 0){
				continue;
			}
			//===========↑↑↑↑↑↑add by xiaosisi on 2017/10/17==================
			try {
			    if(isDelete){
			        new TemplateProcessor().executeDelete(templateRootDir, templateModel,filePathModel, srcFile);
			    }else {
			        new TemplateProcessor().executeGenerate(templateRootDir, templateModel,filePathModel, srcFile);
			    }
			}catch(Exception e) {
				if (ignoreTemplateGenerateException) {
			        GLogger.warn("iggnore generate error,template is:" + srcFile+" cause:"+e);
			        exceptions.add(e);
			    } else {
					throw e;
			    }
			}
		}
		return exceptions;
	}
	
	private List<Exception> scanTemplatesAndProcessSql(File templateRootDir, Map<String, Object> templateModel,Map<String, Object> filePathModel,boolean isDelete) throws Exception {
		if(templateRootDir == null) throw new IllegalStateException("'templateRootDir' must be not null");
		GLogger.println("-------------------load template from templateRootDir = '"+templateRootDir.getAbsolutePath()+"' outRootDir:"+new File(outRootDir).getAbsolutePath());
		
		List<File> srcFiles = FileHelper.searchAllNotIgnoreFile(templateRootDir);
		
		List<Exception> exceptions = new ArrayList<Exception>();
		for(int i = 0; i < srcFiles.size(); i++) {
			File srcFile = srcFiles.get(i);
			//===========↓↓↓↓↓add by xiaosisi on 2017/10/17↓↓↓↓↓==================
			//controller 和 service 层按照页面为单位生成
			if(srcFile.getPath().indexOf("sql") >= 0){
				try {
				    if(isDelete){
				        new TemplateProcessor().executeDelete(templateRootDir, templateModel,filePathModel, srcFile);
				    }else {
				        new TemplateProcessor().executeGenerate(templateRootDir, templateModel,filePathModel, srcFile);
				    }
				}catch(Exception e) {
					if (ignoreTemplateGenerateException) {
				        GLogger.warn("iggnore generate error,template is:" + srcFile+" cause:"+e);
				        exceptions.add(e);
				    } else {
						throw e;
				    }
				}
			}
			//===========↑↑↑↑↑↑add by xiaosisi on 2017/10/17==================
			
		}
		return exceptions;
	}
	
	private class TemplateProcessor {
		private GeneratorControl gg = new GeneratorControl();
		private void executeGenerate(File templateRootDir,Map<String, Object> templateModel, Map<String, Object> filePathModel ,File srcFile) throws SQLException, IOException,TemplateException {
			String templateFile = FileHelper.getRelativePath(templateRootDir, srcFile);
			if(GeneratorHelper.isIgnoreTemplateProcess(srcFile, templateFile,includes,excludes)) {
				return;
			}
			
			if(isCopyBinaryFile && FileHelper.isBinaryFile(srcFile)) {
				String outputFilepath = proceeForOutputFilepath(filePathModel, templateFile);
				GLogger.println("[copy binary file by extention] from:"+srcFile+" => "+new File(getOutRootDir(),outputFilepath));
				IOHelper.copyAndClose(new FileInputStream(srcFile), new FileOutputStream(new File(getOutRootDir(),outputFilepath)));
				return;
			}
			
            String outputFilepath = null;
            try {
            	
                outputFilepath = proceeForOutputFilepath(filePathModel,templateFile);
                
                initGeneratorControlProperties(srcFile);
                processTemplateForGeneratorControl(templateModel, templateFile);
                
                if(gg.isIgnoreOutput()) {
                    GLogger.println("[not generate] by gg.isIgnoreOutput()=true on template:"+templateFile);
                    return;
                }
                
                if(outputFilepath != null ) {
                    generateNewFileOrInsertIntoFile(templateFile,outputFilepath, templateModel);
                }
			}catch(Exception e) {
			    throw new RuntimeException("generate oucur error,templateFile is:" + templateFile+" => "+ outputFilepath+" cause:"+e, e);
			}
		}

	    private void executeDelete(File templateRootDir,Map<String, Object> templateModel, Map<String, Object> filePathModel ,File srcFile) throws SQLException, IOException,TemplateException {
	        String templateFile = FileHelper.getRelativePath(templateRootDir, srcFile);
            if(GeneratorHelper.isIgnoreTemplateProcess(srcFile, templateFile,includes,excludes)) {
                return;
            }
	        initGeneratorControlProperties(srcFile);
	        gg.deleteGeneratedFile = true;
	        processTemplateForGeneratorControl(templateModel, templateFile);
	        String outputFilepath = proceeForOutputFilepath(filePathModel, templateFile);
	        GLogger.println("[delete file] file:"+new File(gg.getOutRoot(),outputFilepath).getAbsolutePath());
	        new File(gg.getOutRoot(),outputFilepath).delete();
	    }
	    
		private void initGeneratorControlProperties(File srcFile) throws SQLException {
			gg.setSourceFile(srcFile.getAbsolutePath());
			gg.setSourceFileName(srcFile.getName());
			gg.setSourceDir(srcFile.getParent());
			gg.setOutRoot(getOutRootDir());
			gg.setOutputEncoding(outputEncoding);
			gg.setSourceEncoding(sourceEncoding);
			gg.setMergeLocation(GENERATOR_INSERT_LOCATION);
			
		}
	
		private void processTemplateForGeneratorControl(Map<String, Object> templateModel,String templateFile) throws IOException, TemplateException {
			templateModel.put("gg", gg);
			Template template = getFreeMarkerTemplate(templateFile);
			template.process(templateModel, IOHelper.NULL_WRITER);
		}
		
		/** 处理文件路径的变量变成输出路径 */
		private String proceeForOutputFilepath(Map<String, Object> filePathModel,String templateFile) throws IOException {
			String outputFilePath = templateFile;
			
			//TODO 删除兼容性的@testExpression
			int testExpressionIndex = -1;
			if((testExpressionIndex = templateFile.indexOf('@')) != -1) {
				outputFilePath = templateFile.substring(0, testExpressionIndex);
				String testExpressionKey = templateFile.substring(testExpressionIndex+1);
				Object expressionValue = filePathModel.get(testExpressionKey);
				if(expressionValue == null) {
					System.err.println("[not-generate] WARN: test expression is null by key:["+testExpressionKey+"] on template:["+templateFile+"]");
						return null;
				}
				if(!"true".equals(String.valueOf(expressionValue))) {
					GLogger.println("[not-generate]\t test expression '@"+testExpressionKey+"' is false,template:"+templateFile);
						return null;
				}
			}
			
			for(String removeExtension : removeExtensions.split(",")) {
				if(outputFilePath.endsWith(removeExtension)) {
					outputFilePath = outputFilePath.substring(0,outputFilePath.length() - removeExtension.length());
					break;
				}
			}
			Configuration conf = GeneratorHelper.newFreeMarkerConfiguration(templateRootDirs, sourceEncoding,"/filepath/processor/");
			return FreemarkerHelper.processTemplateString(outputFilePath,filePathModel,conf);
		}
	
		private Template getFreeMarkerTemplate(String templateName) throws IOException {
			return GeneratorHelper.newFreeMarkerConfiguration(templateRootDirs, sourceEncoding,templateName).getTemplate(templateName);
		}
	
		private void generateNewFileOrInsertIntoFile( String templateFile,String outputFilepath, Map<String, Object> templateModel) throws Exception {
			Template template = getFreeMarkerTemplate(templateFile);
			template.setOutputEncoding(gg.getOutputEncoding());
			
			File absoluteOutputFilePath = FileHelper.mkdir(gg.getOutRoot(),outputFilepath);
			if(absoluteOutputFilePath.exists()) {
				StringWriter newFileContentCollector = new StringWriter();
				if(GeneratorHelper.isFoundInsertLocation(gg,template, templateModel, absoluteOutputFilePath, newFileContentCollector)) {
					GLogger.println("[insert]\t generate content into:"+outputFilepath);
					IOHelper.saveFile(absoluteOutputFilePath, newFileContentCollector.toString(),gg.getOutputEncoding());
					return;
				}
			}
			// renfufei
            // 此处决定是否覆盖
			if(absoluteOutputFilePath.exists() && !gg.isOverride()) {
				GLogger.println("[not generate]\t by gg.isOverride()=false and outputFile exist:"+outputFilepath);
				return;
			}
			
			GLogger.println("[generate]\t template:"+templateFile+" ==> "+outputFilepath);
			FreemarkerHelper.processTemplate(template, templateModel, absoluteOutputFilePath,gg.getOutputEncoding());
		}
	}

	static class GeneratorHelper {
		public static boolean isIgnoreTemplateProcess(File srcFile,String templateFile,String includes,String excludes) {
			if(srcFile.isDirectory() || srcFile.isHidden())
				return true;
			if(templateFile.trim().equals(""))
				return true;
			if(srcFile.getName().toLowerCase().endsWith(".include")){
				GLogger.println("[skip]\t\t endsWith '.include' template:"+templateFile);
				return true;
			}
			templateFile = templateFile.replace('\\', '/');
			for(String exclude : StringHelper.tokenizeToStringArray(excludes,",")) {
				if(new AntPathMatcher().match(exclude.replace('\\', '/'), templateFile)) return true;
			}
			if(includes == null) return false;
			for(String include : StringHelper.tokenizeToStringArray(includes,",")) {
				if(new AntPathMatcher().match(include.replace('\\', '/'), templateFile)) return false;
			}
			return true;
		}		
		
		private static boolean isFoundInsertLocation(GeneratorControl gg,Template template, Map<String, Object> model, File outputFile, StringWriter newFileContent) throws IOException, TemplateException {
			LineNumberReader reader = new LineNumberReader(new FileReader(outputFile));
			String line = null;
			boolean isFoundInsertLocation = false;
			
			//FIXME 持续性的重复生成会导致out of memory
			PrintWriter writer = new PrintWriter(newFileContent);
			while((line = reader.readLine()) != null) {
				writer.println(line);
				// only insert once
				if(!isFoundInsertLocation && line.indexOf(gg.getMergeLocation()) >= 0) {
					template.process(model,writer);
					writer.println();
					isFoundInsertLocation = true;
				}
			}
			
			writer.close();
			reader.close();
			return isFoundInsertLocation;
		}	
		public static Configuration newFreeMarkerConfiguration(List<File> templateRootDirs,String defaultEncoding,String templateName) throws IOException {
		    @SuppressWarnings("deprecation")
			Configuration conf = new Configuration();
			
			FileTemplateLoader[] templateLoaders = new FileTemplateLoader[templateRootDirs.size()];
			for(int i = 0; i < templateRootDirs.size(); i++) {
				templateLoaders[i] = new FileTemplateLoader((File)templateRootDirs.get(i));
			}
			MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(templateLoaders);
			
			conf.setTemplateLoader(multiTemplateLoader);
			conf.setNumberFormat("###############");
			conf.setBooleanFormat("true,false");
			conf.setDefaultEncoding(defaultEncoding);
			
//			String autoIncludes = new File(new File(templateName).getParent(),"macro.include").getPath();
//			List<String> availableAutoInclude = FreemarkerHelper.getAvailableAutoInclude(conf, Arrays.asList("macro.include",autoIncludes));
//			conf.setAutoIncludes(availableAutoInclude);
//			GLogger.info("[set Freemarker.autoIncludes]"+availableAutoInclude+" for templateName:"+templateName);
			
			List<String> autoIncludes = getParentPaths(templateName,"macro.include");
			List<String> availableAutoInclude = FreemarkerHelper.getAvailableAutoInclude(conf,autoIncludes);
			conf.setAutoIncludes(availableAutoInclude);
			GLogger.debug("set Freemarker.autoIncludes:"+availableAutoInclude+" for templateName:"+templateName+" autoIncludes:"+autoIncludes);
			return conf;
		}

		public static List<String> getParentPaths(String templateName,String suffix) {
			String array[] = StringHelper.tokenizeToStringArray(templateName, "\\/");
			List<String> list = new ArrayList<String>();
			list.add(suffix);
			list.add(File.separator+suffix);
			String path = "";
			for(int i = 0; i < array.length; i++){
				path = path + File.separator + array[i];
				list.add(path + File.separator+suffix);
			}
			return list;
		}
	}

	public static class GeneratorModel implements java.io.Serializable{
		private static final long serialVersionUID = 1L;
		public Map<String, Object> filePathModel;
		public Map<String, Object> templateModel;
		public GeneratorModel(Map<String, Object> templateModel, Map<String, Object> filePathModel) {
			this.templateModel = templateModel;
			this.filePathModel = filePathModel;
		}
		public Map<String, Object> getTemplateModel(){
			return this.templateModel;
		}
		public Map<String, Object> getFilePathModel(){
			return this.filePathModel;
		}
	}
}
