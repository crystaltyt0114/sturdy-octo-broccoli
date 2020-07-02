package com.tytont.test2020.utils.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.ListUtils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MybatisPlusGenerator {

	public static void main(String[] args) {
		MybatisPlusGenerator g = new MybatisPlusGenerator();
		Configurations cfg = g.new Configurations();
		cfg.setBasePackage("com.tytont.test2020");
		cfg.setTables(new String[] { "tr_user" });
		cfg.setOperations(new String[] { "create", "update", "get", "delete", "list", "page" });
		cfg.setControllerSubPackage("controller.admin");
		cfg.gen();
	}

	public class Configurations {
		private String author = "";
		private String basePackage;
		private String[] tables;
		private boolean model = true;
		private boolean dao = true;
		private boolean service = true;
		private boolean serviceImpl = true;
		private boolean controller = true;
		private boolean mapperXml = true;
		private boolean query = true;
		private String modelSubPackage = "model";
		private String daoSubPackage = "dao";
		private String serviceSubPackage = "service";
		private String serviceImplSubPackage = "service.impl";
		private String controllerSubPackage = "controller";
		private String[] operations;

		private String path = "";
		{
			File file = new File("");
			path = file.getAbsolutePath();
		}

		// 判断是否需要Query类
		private boolean isQueryVo() {
			return ListUtils.intersection(Arrays.asList(operations), Arrays.asList("list", "page")).size() > 0;
		}

		public String getBasePackage() {
			return basePackage;
		}

		public void gen() {
			AutoGenerator gen = new AutoGenerator();

			// 设置数据库
			gen.setDataSource(new DataSourceConfig().setDbType(DbType.MYSQL).setDriverName(com.mysql.cj.jdbc.Driver.class.getName()).setPassword("Lead$2018#qzwb").setUrl("jdbc:mysql://121.204.151.185:17899/answer?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC").setUsername("root").setTypeConvert(new MySqlTypeConvert()));

			// 全局配置
			gen.setGlobalConfig(new GlobalConfig().setOutputDir(path + "\\src\\main\\java")// 输出目录
					.setActiveRecord(true) // 开启 activeRecord 模式
					.setFileOverride(true) // 是否覆盖文件
					.setEnableCache(false)// XML 二级缓存
					.setBaseResultMap(true)// XML ResultMap
					.setBaseColumnList(true)// XML columList
					.setOpen(false)// 生成后打开文件夹
					.setAuthor(author).setMapperName("%sMapper").setXmlName("%sMapper").setServiceName("%sService").setServiceImplName("%sServiceImpl").setControllerName("%sController").setEnableCache(true).setDateType(DateType.ONLY_DATE));

			// 策略配置
			List<TableFill> tableFillList = new ArrayList<>();
			tableFillList.add(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE));
			String[] prefixs = { "`" };
			gen.setStrategy(new StrategyConfig().setCapitalMode(true)// 全局大写命名
					.setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
					.setInclude(tables) // 需要生成的表
					.setRestControllerStyle(false).setTablePrefix(prefixs)// 此处可以修改为您的表前缀
					.setSuperMapperClass(basePackage + ".base.BaseDao").setSuperServiceClass(basePackage + ".base.BaseService").setSuperControllerClass(basePackage + ".controller.BaseController").setEntityLombokModel(true).setTableFillList(tableFillList).setEntityTableFieldAnnotationEnable(true).setEntityColumnConstant(true));

			// 包配置
			gen.setPackageInfo(new PackageConfig().setParent(basePackage).setController(controllerSubPackage).setService(serviceSubPackage).setServiceImpl(serviceImplSubPackage).setEntity(modelSubPackage).setMapper(daoSubPackage));

			// 自定义配置
			List<FileOutConfig> focList = new ArrayList<>();
			focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
				// 自定义输出文件目录
				@Override
				public String outputFile(TableInfo tableInfo) {
					return path + "/src/main/java/" + basePackage.replace(".", "/") + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
				}
			});
			if (query) {
				if (isQueryVo()) {
					focList.add(new FileOutConfig("/templates/query.java.vm") {
						@Override
						public String outputFile(TableInfo tableInfo) {
							return path + "/src/main/java/" + basePackage.replace(".", "/") + "/model/query/" + tableInfo.getEntityName() + "Query.java";
						}
					});
				}
			}
			gen.setCfg(new InjectionConfig() {
				@Override
				public void initMap() {
					Map<String, Object> map = new HashMap<>();
					map.put("operations", operations);
					map.put("isTableColumn", true);
					this.setMap(map);
				}
			}.setFileOutConfigList(focList));

			// 模板配置
			TemplateConfig templateConfig = new TemplateConfig();
			templateConfig.setXml(null).setEntity("/templates/entity.java").setEntityKt(null);
			if (!model) {
				templateConfig.setEntity(null);
			}
			if (!dao) {
				templateConfig.setMapper(null);
			}
			if (!service) {
				templateConfig.setService(null);
			}
			if (!serviceImpl) {
				templateConfig.setServiceImpl(null);
			}
			if (!controller) {
				templateConfig.setController(null);
			}
			gen.setTemplate(templateConfig);

			// 执行生成
			gen.execute();
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String[] getTables() {
			return tables;
		}

		public void setTables(String[] tables) {
			this.tables = tables;
		}

		public boolean isModel() {
			return model;
		}

		public void setModel(boolean model) {
			this.model = model;
		}

		public boolean isDao() {
			return dao;
		}

		public void setDao(boolean dao) {
			this.dao = dao;
		}

		public boolean isService() {
			return service;
		}

		public void setService(boolean service) {
			this.service = service;
		}

		public boolean isServiceImpl() {
			return serviceImpl;
		}

		public void setServiceImpl(boolean serviceImpl) {
			this.serviceImpl = serviceImpl;
		}

		public boolean isController() {
			return controller;
		}

		public void setController(boolean controller) {
			this.controller = controller;
		}

		public boolean isMapperXml() {
			return mapperXml;
		}

		public void setMapperXml(boolean mapperXml) {
			this.mapperXml = mapperXml;
		}

		public String getModelSubPackage() {
			return modelSubPackage;
		}

		public void setModelSubPackage(String modelSubPackage) {
			this.modelSubPackage = modelSubPackage;
		}

		public String getDaoSubPackage() {
			return daoSubPackage;
		}

		public void setDaoSubPackage(String daoSubPackage) {
			this.daoSubPackage = daoSubPackage;
		}

		public String getServiceSubPackage() {
			return serviceSubPackage;
		}

		public void setServiceSubPackage(String serviceSubPackage) {
			this.serviceSubPackage = serviceSubPackage;
		}

		public String getServiceImplSubPackage() {
			return serviceImplSubPackage;
		}

		public void setServiceImplSubPackage(String serviceImplSubPackage) {
			this.serviceImplSubPackage = serviceImplSubPackage;
		}

		public String getControllerSubPackage() {
			return controllerSubPackage;
		}

		public void setControllerSubPackage(String controllerSubPackage) {
			this.controllerSubPackage = controllerSubPackage;
		}

		public void setBasePackage(String basePackage) {
			this.basePackage = basePackage;
		}

		public String[] getOperations() {
			return operations;
		}

		public void setOperations(String[] operations) {
			this.operations = operations;
		}

		public boolean isQuery() {
			return query;
		}

		public void setQuery(boolean query) {
			this.query = query;
		}
	}
}
